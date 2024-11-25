package com.kraemer.domain.entities;

import java.math.BigDecimal;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.NumericUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class OrderItemBO {
    
    private Long id;

    private OrderBO orderBO;

    private ProductBO productBO;

    private Integer quantity;

    private BigDecimal unitPrice;

    public OrderItemBO(Long id, OrderBO orderBO, ProductBO productBO, Integer quantity, BigDecimal unitPrice) {
        this.id = id;
        this.orderBO = orderBO;
        this.productBO = productBO;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        validate();
    }

    public Long getId() {
        return id;
    }

    public OrderBO getOrder() {
        return orderBO;
    }

    public ProductBO getProduct() {
        return productBO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    private void validate() {

        if(this.productBO == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "produto");
        }

        if(this.quantity == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "quantidade");   
        }

        if(!NumericUtil.isGreaterThanZero(this.quantity)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "quantidade");
        }

        if(this.unitPrice == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "preço unitário");
        }

        if(!NumericUtil.isGreaterThanZero(this.unitPrice)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "preço unitário");
        }
    }

}
