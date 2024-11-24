package com.kraemer.domain.entities.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.kraemer.domain.entities.enums.EnumOrderStatus;

public class OrderDTO {

    private Long id;

    private LocalDateTime createdAt;

    private ClientDTO clientDTO;

    private EnumOrderStatus status;

    private BigDecimal totalValue;

    private List<OrderItemDTO> itemsDTO;

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
        return clientDTO;
    }

    public void setClient(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
    }

    public EnumOrderStatus getEnumStatus() {
        return status;
    }

    public void setEnumStatus(EnumOrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public List<OrderItemDTO> getItemsDTO() {
        return itemsDTO;
    }

    public void setItemsDTO(List<OrderItemDTO> itemsDTO) {
        this.itemsDTO = itemsDTO;
    }

}
