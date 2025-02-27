package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.GrupoBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.repositories.IGrupoRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteGrupoMapper;
import com.kraemer.infra.database.sqlite.model.SqliteGrupo;
import com.kraemer.infra.database.sqlite.model.SqliteOrder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteGrupoRepository implements IGrupoRepository {
    
    public GrupoBO create(GrupoBO bo) {
        var entity = SqliteGrupoMapper.toEntity(bo);

        SqliteGrupo grupoJaExiste = SqliteGrupo.find("nome", bo.getNome()).firstResult();

        if(grupoJaExiste != null) {
            return SqliteGrupoMapper.toDomain(grupoJaExiste);
        }

        entity.persist();

        return SqliteGrupoMapper.toDomain(entity);
    }

    public GrupoBO merge(GrupoBO bo) {
        var entity = SqliteGrupoMapper.toEntity(bo);

        SqliteOrder.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public GrupoBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(findAllBy(queryFieldInfoVO));
    }

    @Override
    public List<GrupoBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
             var params = new ArrayList<>();
        var query = new StringBuilder();

        int paramIndex = 1;

        if (queryFieldsInfoVO == null) {
            return ListUtil.stream(SqliteGrupo.listAll())
                    .map(value -> SqliteGrupoMapper.toDomain((SqliteGrupo) value))
                    .toList();
        }

        for (var val : queryFieldsInfoVO) {
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

        return ListUtil.stream(SqliteGrupo.list(query.toString(), params.toArray()))
                .map(value -> SqliteGrupoMapper.toDomain((SqliteGrupo) value))
                .toList();
    }

    @Override
    public List<GrupoBO> findAll() {
        return  ListUtil.stream(SqliteGrupo.listAll())
        .map(value -> SqliteGrupoMapper.toDomain((SqliteGrupo) value))
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
