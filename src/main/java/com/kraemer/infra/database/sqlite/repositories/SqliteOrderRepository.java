package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.OrderBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.repositories.IOrderRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteOrderMapper;
import com.kraemer.infra.database.sqlite.model.SqliteOrder;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteOrderRepository implements IOrderRepository {

    public OrderBO create(OrderBO bo) {
        var panacheOrder = SqliteOrderMapper.toEntity(bo);

        panacheOrder.persist();

        return SqliteOrderMapper.toDomain(panacheOrder);
    }

    public OrderBO merge(OrderBO bo) {
        var entity = SqliteOrderMapper.toEntity(bo);

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
