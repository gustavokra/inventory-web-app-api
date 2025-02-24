package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.OperacaoCaixaBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.repositories.IOperacaoCaixaRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteOperacaoCaixaMapper;
import com.kraemer.infra.database.sqlite.model.SqliteOperacaoCaixa;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteOperacaoCaixaRepository implements IOperacaoCaixaRepository {

    @Override
    public OperacaoCaixaBO criar(OperacaoCaixaBO bo) {
        var entity = SqliteOperacaoCaixaMapper.toEntity(bo);
        entity.persist();
        return SqliteOperacaoCaixaMapper.toDomain(entity);
    }

    @Override
    public OperacaoCaixaBO atualizar(OperacaoCaixaBO bo) {
        var entity = SqliteOperacaoCaixaMapper.toEntity(bo);
        SqliteOperacaoCaixa.getEntityManager().merge(entity);
        return bo;
    }

    @Override
    public List<OperacaoCaixaBO> encontrarTodos() {
        return ListUtil.stream(SqliteOperacaoCaixa.listAll(Sort.descending("dataAbertura")))
            .map(value -> SqliteOperacaoCaixaMapper.toDomain((SqliteOperacaoCaixa) value))
            .toList();
    }

    @Override
    public OperacaoCaixaBO encontrarPrimeiroPor(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(encontrarTodosPor(queryFieldInfoVO));
    }

    @Override
    public List<OperacaoCaixaBO> encontrarTodosPor(List<QueryFieldInfoVO> queryFieldsVO) {
        if (queryFieldsVO == null) {
            return encontrarTodos();
        }

        var params = new ArrayList<>();
        var query = new StringBuilder();
        int paramIndex = 1;

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

        return ListUtil.stream(SqliteOperacaoCaixa.list(query.toString(), params.toArray()))
                .map(value -> SqliteOperacaoCaixaMapper.toDomain((SqliteOperacaoCaixa) value))
                .toList();
    }

    @Override
    public boolean excluir(Long id) {
        return SqliteOperacaoCaixa.deleteById(id);
    }

    @Override
    public EnumDBImpl getType() {
        return EnumDBImpl.SQLITE;
    }
} 