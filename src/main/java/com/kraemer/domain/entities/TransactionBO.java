package com.kraemer.domain.entities;

import java.math.BigDecimal;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.enums.EnumTransactionType;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class TransactionBO {
    
    private Long id;

    private CreatedAtVO createdAt;

    private EnumTransactionType transactionType;

    private BigDecimal value;

    private ProductBO product;

    private OrderBO order;

    public TransactionBO(Long id, CreatedAtVO createdAt, EnumTransactionType transactionType, BigDecimal value,
            ProductBO product, OrderBO order) {
        this.id = id;
        this.createdAt = createdAt;
        this.transactionType = transactionType;
        this.value = value;
        this.product = product;
        this.order = order;

        validate();
    }

    public Long getId() {
        return id;
    }

    public CreatedAtVO getCreatedAt() {
        return createdAt;
    }

    public EnumTransactionType getTransactionType() {
        return transactionType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public ProductBO getProduct() {
        return product;
    }

    public OrderBO getOrder() {
        return order;
    }

    public void validate() {
        if(this.transactionType == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "tipo da transação");
        }
    
        if(this.value == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "valor");
        }
    
        if(this.product == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "produto");
        }

        if(this.order == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "pedido");
        }
    }

}