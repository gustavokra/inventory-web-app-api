package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.GrupoBO;
import com.kraemer.infra.database.sqlite.model.SqliteGrupo;

public class SqliteGrupoMapper {

    public static SqliteGrupo toEntity(GrupoBO domain) {
        SqliteGrupo entity = new SqliteGrupo();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());

        return entity;
    }

    public static GrupoBO toDomain(SqliteGrupo entity) {
        return new GrupoBO(entity.getId(), entity.getNome());
    }

}
