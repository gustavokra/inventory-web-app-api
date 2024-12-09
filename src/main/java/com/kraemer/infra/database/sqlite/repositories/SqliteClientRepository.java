package com.kraemer.infra.database.sqlite.repositories;

import java.util.ArrayList;
import java.util.List;

import com.kraemer.domain.entities.ClientBO;
import com.kraemer.domain.entities.enums.EnumDBImpl;
import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.repositories.IClientRepository;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;
import com.kraemer.domain.vo.QueryFieldInfoVO;
import com.kraemer.infra.database.sqlite.mappers.SqliteClientMapper;
import com.kraemer.infra.database.sqlite.model.SqliteClient;
import com.kraemer.infra.database.sqlite.model.SqliteTransaction;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SqliteClientRepository implements IClientRepository {
    
    public ClientBO create(ClientBO bo) {
        var panacheClient = SqliteClientMapper.toEntity(bo);

        panacheClient.persist();

        return SqliteClientMapper.toDomain(panacheClient);
    }

    public ClientBO merge(ClientBO bo) {
        var entity = SqliteClientMapper.toEntity(bo);

        SqliteClient.getEntityManager().merge(entity);

        return bo;
    }

    @Override
    public ClientBO findFirstBy(List<QueryFieldInfoVO> queryFieldInfoVO) {
        return ListUtil.first(findAllBy(queryFieldInfoVO));
    }

    @Override
    public List<ClientBO> findAll() {
        return ListUtil.stream(SqliteClient.listAll(Sort.ascending("name", "document")))
            .map(value -> SqliteClientMapper.toDomain((SqliteClient) value))
            .toList();
    }

    @Override
    public List<ClientBO> findAllBy(List<QueryFieldInfoVO> queryFieldsVO) {
        var params = new ArrayList<>();
        var query = new StringBuilder();

        int paramIndex = 1;

        if(queryFieldsVO == null) {
            return ListUtil.stream(SqliteClient.listAll(Sort.ascending("name", "document")))
            .map(value -> SqliteClientMapper.toDomain((SqliteClient) value))
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

        return ListUtil.stream(SqliteClient.list(query.toString(), params.toArray()))
                .map(value -> SqliteClientMapper.toDomain((SqliteClient) value))
                .toList();
    }

    @Override
    public EnumDBImpl getType() {
        return EnumDBImpl.SQLITE;
    }

    @Override
    public boolean delete(Long id) {
        // List<SqliteTransaction> transactions = SqliteTransaction.find("WHERE CLIENT_ID = " + id).list();
        // if(ListUtil.isNullOrEmpty(transactions)) {
        //     throw new InventoryAppException(EnumErrorCode.ERRO_AO_EXCLUIR, "cliente", "id", id + " existem transações com esse cliente");
        // }
        return SqliteClient.deleteById(id);
    }

}
