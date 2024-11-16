package com.kraemer.infra.database.sqlite.mappers;

import java.util.Set;
import java.util.stream.Collectors;

import com.kraemer.domain.entities.enums.EnumRole;
import com.kraemer.infra.database.sqlite.model.SqliteRole;

public class SqliteRoleMapper {
    
    public static Set<EnumRole> toEnum(Set<SqliteRole> entities) {
        return entities.stream()
            .map(ent -> EnumRole.parseByKey(ent.getDescription()))
            .collect(Collectors.toSet());
    }

    public static Set<SqliteRole> toEntity(Set<EnumRole> enumRoles) {
        return enumRoles.stream()
            .map(enumRole -> new SqliteRole(enumRole.getKey()))
            .collect(Collectors.toSet());
    }

}
