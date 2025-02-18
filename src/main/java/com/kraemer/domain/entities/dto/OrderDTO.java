package com.kraemer.domain.entities.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.kraemer.domain.entities.enums.EnumOrderStatus;

public class OrderDTO {

    private Long id;

    private LocalDateTime createdAt;

    private ClientDTO client;

    private EnumOrderStatus enumStatus;

    private BigDecimal totalValue;

    private List<OrderItemDTO> items;

    private List<FormaPagamentoDTO> formasPagamento;

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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public EnumOrderStatus getEnumStatus() {
        return enumStatus;
    }

    public void setEnumStatus(EnumOrderStatus enumStatus) {
        this.enumStatus = enumStatus;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    public List<FormaPagamentoDTO> getFormasPagamento() {
        return formasPagamento;
    }

    public void setFormasPagamento(List<FormaPagamentoDTO> formasPagamento) {
        this.formasPagamento = formasPagamento;
    }

}
