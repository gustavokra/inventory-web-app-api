package com.kraemer.infra.database.sqlite.repositories;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.kraemer.infra.database.sqlite.model.SqliteOrder;
import com.kraemer.infra.database.sqlite.model.SqliteOrderItem;
import com.kraemer.infra.database.sqlite.model.SqliteTransaction;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteOrderRepository implements IOrderRepository {

    public OrderBO create(OrderBO bo) {
        var entity = SqliteOrderMapper.toEntity(bo);

        validarItems(entity);



        entity.persist();

        return SqliteOrderMapper.toDomain(entity);
    }

    private void validarItems(SqliteOrder entidade) {
        for (SqliteOrderItem item : entidade.getItems()) {
            item.setOrder(entidade);

            if (NumericUtil.isLessOrEquals(item.getProduct().getQuantity() - item.getQuantity(), -1)) {
                throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "Quantidade de produtos excede estoque, ");
            }

            item.getProduct().setQuantity(item.getProduct().getQuantity() - item.getQuantity());
        }
    }

    public OrderBO merge(OrderBO bo) {
        var entity = SqliteOrderMapper.toEntity(bo);

        for (SqliteOrderItem item : entity.getItems()) {
            item.setOrder(entity);
        }

        if (entity.getStatus().equals(EnumOrderStatus.COMPLETED)) {
            entity.getItems().stream().forEach(item -> {
                var transaction = new SqliteTransaction();
                transaction.setOrder(entity);
                transaction.setProduct(item.getProduct());
                transaction.setTransactionType(EnumTransactionType.INPUT);
                transaction
                        .setValue(item.getProduct().getPrice().multiply(NumericUtil.toBigDecimal(item.getQuantity())));
                transaction.setCreatedAt(LocalDateTime.now());
                transaction.persist();
            });
        }

        SqliteOrder.getEntityManager().merge(entity);

        return bo;
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
        var params = new ArrayList<>();
        var query = new StringBuilder();

        int paramIndex = 1;

        if (queryFieldsVO == null) {
            return ListUtil.stream(SqliteOrder.listAll(Sort.ascending("createdAt")))
                    .map(value -> SqliteOrderMapper.toDomain((SqliteOrder) value))
                    .toList();
        }

        for (var val : queryFieldsVO) {
            String formattedCondition;
            if (val.getFieldValue() != null) {
                formattedCondition = val.getFieldName() + " = ?" + paramIndex++;
                params.add(val.getFieldValue());
            } else {
                formattedCondition = val.getFieldName() + " IS NULL";
            }

            if (query.length() > 0) {
                query.append(" AND ");
            }
            query.append(formattedCondition);
        }

        return ListUtil.stream(SqliteOrder.list(query.toString(), params.toArray()))
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

}