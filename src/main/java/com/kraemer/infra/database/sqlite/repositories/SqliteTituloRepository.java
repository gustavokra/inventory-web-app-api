package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.TituloBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.repositories.ITituloRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteTituloMapper;
import com.kraemer.infra.database.sqlite.model.SqliteTitulo;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteTituloRepository implements ITituloRepository {
    
    public TituloBO criar(TituloBO bo) {
        var panacheTitulo = SqliteTituloMapper.toEntity(bo);

        panacheTitulo.persist();

        return SqliteTituloMapper.toDomain(panacheTitulo);
    }

    public TituloBO atualizar(TituloBO bo) {
        var entity = SqliteTituloMapper.toEntity(bo);

        SqliteTitulo.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public TituloBO encontrarPrimeiroPor(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(encontrarTodosPor(queryFieldInfoVO));
    }

    @Override
    public List<TituloBO> encontrarTodos() {
        return ListUtil.stream(SqliteTitulo.listAll(Sort.ascending("nome")))
                .map(value -> SqliteTituloMapper.toDomain((SqliteTitulo) value))
                .toList();
    }

    @Override
    public List<TituloBO> encontrarTodosPor(List<QueryFieldInfoVO> queryFieldsVO) {
        var params = new ArrayList<>();
        var query = new StringBuilder();

        int paramIndex = 1;

        if (queryFieldsVO == null) {
            return ListUtil.stream(SqliteTitulo.listAll(Sort.ascending("nome")))
                    .map(value -> SqliteTituloMapper.toDomain((SqliteTitulo) value))
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

        return ListUtil.stream(SqliteTitulo.list(query.toString(), params.toArray()))
                .map(value -> SqliteTituloMapper.toDomain((SqliteTitulo) value))
                .toList();
    }

    @Override
    public EnumDBImpl getType() {
        return EnumDBImpl.SQLITE;
    }

    @Override
    public boolean excluir(Long id) {
        return SqliteTitulo.deleteById(id);
    }

}
