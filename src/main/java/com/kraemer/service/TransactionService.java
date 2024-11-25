package com.kraemer.service;

import java.util.List;

import com.kraemer.domain.entities.dto.TransactionDTO;
import com.kraemer.domain.entities.mappers.TransactionMapper;
import com.kraemer.infra.database.sqlite.mappers.SqliteTransactionMapper;
import com.kraemer.infra.database.sqlite.model.SqliteTransaction;

import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TransactionService {

    public List<TransactionDTO> findAll() {
        List<SqliteTransaction> transactions = SqliteTransaction.findAll(Sort.ascending("createdAt")).list();
       
        return transactions.stream().map(transaction -> {
            return TransactionMapper.toDTO(SqliteTransactionMapper.toDomain(transaction)) ;
        }).toList();
    }

}
