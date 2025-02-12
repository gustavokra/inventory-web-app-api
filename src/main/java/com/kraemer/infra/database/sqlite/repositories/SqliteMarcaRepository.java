package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.MarcaBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.repositories.IMarcaRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteMarcaMapper;
import com.kraemer.infra.database.sqlite.model.SqliteMarca;
import com.kraemer.infra.database.sqlite.model.SqliteOrder;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteMarcaRepository implements IMarcaRepository {

    public MarcaBO create(MarcaBO bo) {
        var entity = SqliteMarcaMapper.toEntity(bo);

        entity.persist();

        return SqliteMarcaMapper.toDomain(entity);
    }

    public MarcaBO merge(MarcaBO bo) {
        var entity = SqliteMarcaMapper.toEntity(bo);

        SqliteOrder.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public MarcaBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(findAllBy(queryFieldInfoVO));
    }

    @Override
    public List<MarcaBO> findAllBy(List<QueryFieldInfoVO> queryFieldsInfoVO) {
             var params = new ArrayList<>();
        var query = new StringBuilder();

        int paramIndex = 1;

        if (queryFieldsInfoVO == null) {
            return ListUtil.stream(SqliteMarca.listAll())
                    .map(value -> SqliteMarcaMapper.toDomain((SqliteMarca) value))
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

        return ListUtil.stream(SqliteMarca.list(query.toString(), params.toArray()))
                .map(value -> SqliteMarcaMapper.toDomain((SqliteMarca) value))
                .toList();
    }

    @Override
    public List<MarcaBO> findAll() {
        return  ListUtil.stream(SqliteMarca.listAll())
        .map(value -> SqliteMarcaMapper.toDomain((SqliteMarca) value))
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
