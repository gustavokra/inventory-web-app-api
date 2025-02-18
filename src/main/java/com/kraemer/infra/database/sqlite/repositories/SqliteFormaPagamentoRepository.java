package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.FormaPagamentoBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.repositories.IFormaPagamentoRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteFormaPagamentoMapper;
import com.kraemer.infra.database.sqlite.model.SqliteFormaPagamento;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteFormaPagamentoRepository implements IFormaPagamentoRepository{
    
    public FormaPagamentoBO criar(FormaPagamentoBO bo) {
        var panacheFormaPagamento = SqliteFormaPagamentoMapper.toEntity(bo);

        panacheFormaPagamento.persist();

        return SqliteFormaPagamentoMapper.toDomain(panacheFormaPagamento);
    }

    public FormaPagamentoBO atualizar(FormaPagamentoBO bo) {
        var entity = SqliteFormaPagamentoMapper.toEntity(bo);

        SqliteFormaPagamento.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public FormaPagamentoBO encontrarPrimeiroPor(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(encontrarTodosPor(queryFieldInfoVO));
    }

    @Override
    public List<FormaPagamentoBO> encontrarTodos() {
        return ListUtil.stream(SqliteFormaPagamento.listAll(Sort.ascending("nome")))
                .map(value -> SqliteFormaPagamentoMapper.toDomain((SqliteFormaPagamento) value))
                .toList();
    }

    @Override
    public List<FormaPagamentoBO> encontrarTodosPor(List<QueryFieldInfoVO> queryFieldsVO) {
        var params = new ArrayList<>();
        var query = new StringBuilder();

        int paramIndex = 1;

        if (queryFieldsVO == null) {
            return ListUtil.stream(SqliteFormaPagamento.listAll(Sort.ascending("nome")))
                    .map(value -> SqliteFormaPagamentoMapper.toDomain((SqliteFormaPagamento) value))
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

        return ListUtil.stream(SqliteFormaPagamento.list(query.toString(), params.toArray()))
                .map(value -> SqliteFormaPagamentoMapper.toDomain((SqliteFormaPagamento) value))
                .toList();
    }

    @Override
    public EnumDBImpl getType() {
        return EnumDBImpl.SQLITE;
    }

    @Override
    public boolean excluir(Long id) {
        return SqliteFormaPagamento.deleteById(id);
    }

}
