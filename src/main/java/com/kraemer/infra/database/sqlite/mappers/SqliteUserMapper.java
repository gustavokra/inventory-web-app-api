package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.infra.database.sqlite.model.SqliteUser;

public class SqliteUserMapper {
    
    public static UserBO toDomain(SqliteUser entity) {
        return new UserBO(entity.getName(),
        entity.getEmail(),
        entity.getPassword(),
        null);
    }

    public static SqliteUser toEntity(UserBO bo) {
        SqliteUser entity = new SqliteUser();
        entity.setName(bo.getName());
        entity.setEmail(bo.getEmail());
        entity.setPassword(bo.getPassword());
        return entity;
    }

}
