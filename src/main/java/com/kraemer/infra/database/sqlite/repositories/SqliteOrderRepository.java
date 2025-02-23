package com.kraemer.infra.database.sqlite.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

import com.kraemer.domain.entities.OrderBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.enums.EnumOrderStatus;
import com.kraemer.domain.entities.enums.EnumTransactionType;
import com.kraemer.domain.entities.repositories.IOrderRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.utils.NumericUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteOrderMapper;
import com.kraemer.infra.database.sqlite.mappers.SqliteTituloMapper;
import com.kraemer.infra.database.sqlite.model.SqliteOrder;
import com.kraemer.infra.database.sqlite.model.SqliteOrderItem;
import com.kraemer.infra.database.sqlite.model.SqliteTransaction;
import com.kraemer.infra.database.sqlite.model.SqliteTitulo;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteOrderRepository implements IOrderRepository {

    public OrderBO create(OrderBO pedidoBO) {
        validarPedido(pedidoBO);
        
        var pedido = SqliteOrderMapper.toEntity(pedidoBO);
        validarEAtualizarEstoque(pedido);
        pedido.persist();
        
        criarTitulosPagamento(pedidoBO, pedido);

        return SqliteOrderMapper.toDomain(pedido);
    }

    public OrderBO merge(OrderBO pedidoBO) {
        validarPedido(pedidoBO);
        
        var pedido = SqliteOrderMapper.toEntity(pedidoBO);
        vincularItensPedido(pedido);

        if (pedido.getStatus().equals(EnumOrderStatus.COMPLETED)) {
            criarTransacoesPedidoConcluido(pedido);
        }

        atualizarTitulosPagamento(pedidoBO, pedido);
        SqliteOrder.getEntityManager().merge(pedido);
        
        return pedidoBO;
    }

    @Override
    public OrderBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(findAllBy(queryFieldInfoVO));
    }

    @Override
    public List<OrderBO> findAll() {
        return ListUtil.stream(SqliteOrder.listAll(Sort.ascending("createdAt")))
                .map(value -> SqliteOrderMapper.toDomain((SqliteOrder) value))
                .toList();
    }

    @Override
    public List<OrderBO> findAllBy(List<QueryFieldInfoVO> queryFieldsVO) {
        if (queryFieldsVO == null) {
            return findAll();
        }

        var parametrosConsulta = new ArrayList<>();
        var queryBuilder = construirQueryBusca(queryFieldsVO, parametrosConsulta);

        return ListUtil.stream(SqliteOrder.list(queryBuilder.toString(), parametrosConsulta.toArray()))
                .map(value -> SqliteOrderMapper.toDomain((SqliteOrder) value))
                .toList();
    }

    @Override
    public EnumDBImpl getType() {
        return EnumDBImpl.SQLITE;
    }

    @Override
    public boolean delete(Long id) {
        return SqliteOrder.deleteById(id);
    }

    private void validarPedido(OrderBO pedidoBO) {
        if (pedidoBO.getTitulos() == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Títulos");
        }
    }

    private void criarTitulosPagamento(OrderBO pedidoBO, SqliteOrder pedido) {
        var titulos = pedidoBO.getTitulos().stream()
            .map(SqliteTituloMapper::toEntity)
            .toList();

        if (titulos.isEmpty()) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_CRIAR, "Títulos");
        }

        titulos.forEach(titulo -> {
            titulo.setPedido(pedido);
            titulo.persist();
        });
    }

    private void atualizarTitulosPagamento(OrderBO pedidoBO, SqliteOrder pedido) {
        var titulosAtualizados = pedidoBO.getTitulos().stream()
            .map(SqliteTituloMapper::toEntity)
            .toList();

        if (titulosAtualizados.isEmpty()) {
            throw new InventoryAppException(EnumErrorCode.ERRO_AO_EDITAR, "Títulos");
        }

        // First merge the order to ensure it exists in the database
        SqliteOrder.getEntityManager().merge(pedido);

        // Then handle the titles
        titulosAtualizados.forEach(titulo -> {
            titulo.setPedido(pedido);
            SqliteTitulo.getEntityManager().merge(titulo);
        });

        // Finally, remove any titles that are no longer present
        var titulosIds = titulosAtualizados.stream()
            .map(SqliteTitulo::getId)
            .toList();
        SqliteTitulo.delete("pedido.id = ?1 AND id NOT IN ?2", pedido.getId(), titulosIds);
    }

    private void validarEAtualizarEstoque(SqliteOrder pedido) {
        for (SqliteOrderItem item : pedido.getItems()) {
            item.setOrder(pedido);
            validarQuantidadeEstoque(item);
            atualizarQuantidadeEstoque(item);
        }
    }

    private void validarQuantidadeEstoque(SqliteOrderItem item) {
        if (NumericUtil.isLessOrEquals(item.getProduct().getQuantity() - item.getQuantity(), -1)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "Quantidade de produtos excede estoque");
        }
    }

    private void atualizarQuantidadeEstoque(SqliteOrderItem item) {
        item.getProduct().setQuantity(item.getProduct().getQuantity() - item.getQuantity());
    }

    private void vincularItensPedido(SqliteOrder pedido) {
        pedido.getItems().forEach(item -> item.setOrder(pedido));
    }

    private void criarTransacoesPedidoConcluido(SqliteOrder pedido) {
        pedido.getItems().forEach(item -> {
            var transacao = criarTransacao(pedido, item);
            transacao.persist();
        });
    }

    private SqliteTransaction criarTransacao(SqliteOrder pedido, SqliteOrderItem item) {
        var transacao = new SqliteTransaction();
        transacao.setOrder(pedido);
        transacao.setProduct(item.getProduct());
        transacao.setTransactionType(EnumTransactionType.INPUT);
        transacao.setValue(calcularValorTransacao(item));
        transacao.setCreatedAt(LocalDateTime.now());
        return transacao;
    }

    private BigDecimal calcularValorTransacao(SqliteOrderItem item) {
        return item.getProduct().getPrice().multiply(NumericUtil.toBigDecimal(item.getQuantity()));
    }

    private StringBuilder construirQueryBusca(List<QueryFieldInfoVO> queryFieldsVO, ArrayList<Object> parametrosConsulta) {
        var queryBuilder = new StringBuilder();
        int paramIndex = 1;

        for (var campo : queryFieldsVO) {
            if (queryBuilder.length() > 0) {
                queryBuilder.append(" AND ");
            }

            if (campo.getFieldValue() != null) {
                queryBuilder.append(campo.getFieldName()).append(" = ?").append(paramIndex++);
                parametrosConsulta.add(campo.getFieldValue());
            } else {
                queryBuilder.append(campo.getFieldName()).append(" IS NULL");
            }
        }

        return queryBuilder;
    }
}