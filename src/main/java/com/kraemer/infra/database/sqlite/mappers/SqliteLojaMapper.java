package com.kraemer.infra.database.sqlite.mappers;

import com.kraemer.domain.entities.LojaBO;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.infra.database.sqlite.model.SqliteLoja;

public class SqliteLojaMapper {

    public static LojaBO toDomain(SqliteLoja sqliteLoja) {
        return new LojaBO(sqliteLoja.getId(), sqliteLoja.getNome(), new CreatedAtVO(sqliteLoja.getDataCriacao()));
    }
    
    public static SqliteLoja toEntity(LojaBO domain) {
        SqliteLoja entity = new SqliteLoja();
        entity.setId(domain.getId());
        entity.setNome(domain.getNome());
        entity.setDataCriacao(domain.getDataCriacao().getValue());
        
        return entity;
    }
}
