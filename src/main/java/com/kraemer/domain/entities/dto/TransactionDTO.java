package com.kraemer.domain.entities.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kraemer.domain.entities.OrderBO;
import com.kraemer.domain.entities.ProductBO;
import com.kraemer.domain.entities.enums.EnumTransactionType;

public class TransactionDTO {

    private Long id;

    private LocalDateTime createdAt;

    private EnumTransactionType transactionType;

    private BigDecimal value;

    private ProductBO product;

    private OrderBO order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public EnumTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(EnumTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public ProductBO getProduct() {
        return product;
    }

    public void setProduct(ProductBO product) {
        this.product = product;
    }

    public OrderBO getOrder() {
        return order;
    }

    public void setOrder(OrderBO order) {
        this.order = order;
    }

}
