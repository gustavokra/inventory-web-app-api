package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.UserBO;
import com.kraemer.infra.database.sqlite.model.SqliteUser;

public class SqliteUserMapper {
    
    public static UserBO toDomain(SqliteUser entity) {
        return new UserBO(entity.getId(),
        entity.getName(),
        entity.getEmail(),
        entity.getPassword(),
        null,
        entity.getIdLoja(),
        SqliteRoleMapper.toEnum(entity.getRoles())
        );
    }

    public static SqliteUser toEntity(UserBO bo) {
        SqliteUser entity = new SqliteUser();
        entity.setName(bo.getName());
        entity.setEmail(bo.getEmail());
        entity.setPassword(bo.getPassword());
        entity.setRoles(SqliteRoleMapper.toEntity(bo.getRoles()));
        entity.setIdLoja(bo.getIdLoja());
        return entity;
    }

}
