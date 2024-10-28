package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.repositories.IUserRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteUserMapper;
import com.kraemer.infra.database.sqlite.model.SqliteUser;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteRepository implements IUserRepository {

    @Override
    public UserBO create(UserBO bo) {
        var panacheUser = SqliteUserMapper.toEntity(bo);

        panacheUser.persist();

        return SqliteUserMapper.toDomain(panacheUser);
    }

    @Override
    public UserBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(findAllBy(queryFieldInfoVO));
    }

    @Override
    public List<UserBO> findAllBy(List<QueryFieldInfoVO> queryFieldsVO) {
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
    
        return ListUtil.stream(SqliteUser.list(query.toString(), params.toArray()))
                .map(value -> SqliteUserMapper.toDomain((SqliteUser) value))
                .collect(Collectors.toList());
    }

    @Override
    public EnumDBImpl getType() {
        return EnumDBImpl.SQLITE;
    }

}
