package com.kraemer.domain.entities.dto;

import java.math.BigDecimal;


public class OrderItemDTO {

    private Long id;

    private OrderDTO orderDTO;

    private ProductDTO productDTO;

    private Integer quantity;

    private BigDecimal unitPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDTO getOrder() {
        return orderDTO;
    }

    public void setOrder(OrderDTO orderDTO) {
        this.orderDTO = orderDTO;
    }

    public ProductDTO getProduct() {
        return productDTO;
    }

    public void setProduct(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}
