package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.SupplierBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.repositories.ISupplierRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteSupplierMapper;
import com.kraemer.infra.database.sqlite.model.SqliteSupplier;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteSupplierRepository implements ISupplierRepository {

    public SupplierBO create(SupplierBO bo) {
        var panacheSupplier = SqliteSupplierMapper.toEntity(bo);

        panacheSupplier.persist();

        return SqliteSupplierMapper.toDomain(panacheSupplier);
    }

    public SupplierBO merge(SupplierBO bo) {
        var entity = SqliteSupplierMapper.toEntity(bo);

        SqliteSupplier.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public SupplierBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(findAllBy(queryFieldInfoVO));
    }

    @Override
    public List<SupplierBO> findAll() {
        return ListUtil.stream(SqliteSupplier.listAll(Sort.ascending("name", "document")))
        .map(value -> SqliteSupplierMapper.toDomain((SqliteSupplier) value))
        .toList();
    }

    @Override
    public List<SupplierBO> findAllBy(List<QueryFieldInfoVO> queryFieldsVO) {
        var params = new ArrayList<>();
        var query = new StringBuilder();

        int paramIndex = 1;

        if (queryFieldsVO == null) {
            return ListUtil.stream(SqliteSupplier.listAll(Sort.ascending("name", "document")))
                    .map(value -> SqliteSupplierMapper.toDomain((SqliteSupplier) value))
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

        return ListUtil.stream(SqliteSupplier.list(query.toString(), params.toArray()))
                .map(value -> SqliteSupplierMapper.toDomain((SqliteSupplier) value))
                .toList();
    }

    @Override
    public EnumDBImpl getType() {
        return EnumDBImpl.SQLITE;
    }

    @Override
    public boolean delete(Long id) {
        return SqliteSupplier.deleteById(id);
    }
}
