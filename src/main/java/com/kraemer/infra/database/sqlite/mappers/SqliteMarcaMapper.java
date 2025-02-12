package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.MarcaBO;
import com.kraemer.infra.database.sqlite.model.SqliteMarca;

public class SqliteMarcaMapper {
    
    public static SqliteMarca toEntity(MarcaBO domain) {
        SqliteMarca entity = new SqliteMarca();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());

        return entity;
    }

    public static MarcaBO toDomain(SqliteMarca entity) {
        return new MarcaBO(entity.getId(), entity.getNome());
    }
}
