package com.kraemer.domain.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.enums.EnumOrderStatus;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.utils.NumericUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class OrderBO {

    private Long id;

    private CreatedAtVO createdAt;

    private ClientBO clientBO;

    private EnumOrderStatus enumStatus;

    private BigDecimal totalValue;

    private List<OrderItemBO> itemsBO;

    private List<TituloBO> titulos;

    public OrderBO(Long id, CreatedAtVO createdAt, ClientBO clientBO, EnumOrderStatus enumStatus,
            BigDecimal totalValue, List<OrderItemBO> itemsBO, List<TituloBO> titulos) {
        this.id = id;
        this.createdAt = createdAt;
        this.clientBO = clientBO;
        this.enumStatus = enumStatus;
        this.totalValue = totalValue;
        this.itemsBO = itemsBO;
        this.titulos = titulos;
        
        validate();
    }

    public Long getId() {
        return id;
    }

    public CreatedAtVO getCreatedAt() {
        return createdAt;
    }

    public ClientBO getClient() {
        return clientBO;
    }

    public EnumOrderStatus getEnumStatus() {
        return enumStatus;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    public List<OrderItemBO> getItemsBO() {
        return itemsBO;
    }

    public ClientBO getClientBO() {
        return clientBO;
    }

    private void validate() {

        if (this.clientBO == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Cliente");
        }

        if (enumStatus == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "enumStatus");
        }

        if (ListUtil.isNullOrEmpty(itemsBO)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Items do pedido");
        }

        if (ListUtil.isNullOrEmpty(titulos)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "Titulos");
        }
    }

    public void handleCreate() {
        sumTotalValue();

        verifyInactiveEntities();
    }

    public void sumTotalValue() {
        this.totalValue = itemsBO.stream()
        .map(item ->  item.getUnitPrice().multiply(NumericUtil.toBigDecimal(item.getQuantity())))
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void verifyInactiveEntities() {
        StringBuilder errorMessage = new StringBuilder();
    
        if (!isClientActive()) {
            errorMessage.append("Cliente inativo: ")
                        .append(this.clientBO.getName())
                        .append("\n");
        }
    
        String inactiveProductsMessage = getInactiveProductsMessage();
        if (!inactiveProductsMessage.isEmpty()) {
            errorMessage.append("Produtos inativos:\n")
                        .append(inactiveProductsMessage);
        }
    
        if (errorMessage.length() > 0) {
            throw new InventoryAppException(
                    EnumErrorCode.ERRO_AO_CRIAR,
                    "Pedido. " + errorMessage.toString().trim()
            );
        }
    }
    
    private boolean isClientActive() {
        return this.clientBO.isActive();
    }
    
    private String getInactiveProductsMessage() {
        return itemsBO.stream()
                .filter(item -> !item.getProduct().getActive())
                .map(item -> "- Produto: " + item.getProduct().getName() + "\n")
                .collect(Collectors.joining());
    }
    
    public void handleUpdate(ClientBO clientBO,
            EnumOrderStatus enumStatus,
            List<OrderItemBO> itemsBO,
            List<TituloBO> titulos) {

        if (clientBO != null) {
            this.clientBO = clientBO;
        }

        if (enumStatus != null) {
            this.enumStatus = enumStatus;
        }

        if (ListUtil.isNotNullOrEmpty(itemsBO)) {
            this.itemsBO = itemsBO;
        }

        if (ListUtil.isNotNullOrEmpty(titulos)) {
            this.titulos = titulos;
        }

        sumTotalValue();

    }

    public List<TituloBO> getTitulos() {
        return titulos;
    }

}
