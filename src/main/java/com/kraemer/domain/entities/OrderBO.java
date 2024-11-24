package com.kraemer.domain.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.enums.EnumOrderStatus;
import com.kraemer.domain.entities.vo.CreatedAtVO;
import com.kraemer.domain.utils.ListUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class OrderBO {

    private Long id;

    private CreatedAtVO createdAt;

    private ClientBO clientBO;

    private EnumOrderStatus enumStatus;

    private BigDecimal totalValue;

    private List<OrderItemBO> itemsBO;

    public OrderBO(Long id, CreatedAtVO createdAt, ClientBO clientBO, EnumOrderStatus enumStatus,
            BigDecimal totalValue, List<OrderItemBO> itemsBO) {
        this.id = id;
        this.createdAt = createdAt;
        this.clientBO = clientBO;
        this.enumStatus = enumStatus;
        this.totalValue = totalValue;
        this.itemsBO = itemsBO;

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

    private void validate() {

        if (this.clientBO == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "cliente");
        }

        if (enumStatus == null) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "enumStatus");
        }

        if (ListUtil.isNullOrEmpty(itemsBO)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "itemsBO do pedido");
        }

    }

    public void handleCreate() {
        sumTotalValue();

        verifyInactiveEntities();
    }

    public void sumTotalValue() {
        this.totalValue = itemsBO.stream()
                .map(OrderItemBO::getUnitPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void verifyInactiveEntities() {
        if (!isClientActive()) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "Cliente inativo");
        }

        String inactiveProductsMessage = getInactiveProductsMessage();
        String inactiveSuppliersMessage = getInactiveSuppliersMessage();

        if (!inactiveProductsMessage.isEmpty() || !inactiveSuppliersMessage.isEmpty()) {
            throw new InventoryAppException(
                    EnumErrorCode.CAMPO_INVALIDO,
                    inactiveProductsMessage + inactiveSuppliersMessage);
        }
    }

    private boolean isClientActive() {
        return this.clientBO.isActive();
    }

    private String getInactiveProductsMessage() {
        return itemsBO.stream()
                .filter(item -> !item.getProduct().getActive())
                .map(item -> "- " + item.getProduct().getName() + "\n")
                .collect(Collectors.joining("", "Produtos inativos:\n", ""));
    }

    private String getInactiveSuppliersMessage() {
        return itemsBO.stream()
                .filter(item -> !item.getProduct().getSupplier().isActive())
                .map(item -> "- " + item.getProduct().getName() +
                        " (fornecedor: " + item.getProduct().getSupplier().getName() + ")\n")
                .collect(Collectors.joining("", "Produtos com fornecedores inativos:\n", ""));
    }



    public void handleUpdate(ClientBO clientBO,
            EnumOrderStatus enumStatus,
            List<OrderItemBO> itemsBO) {

        if (clientBO != null) {
            this.clientBO = clientBO;
        }

        if (enumStatus != null) {
            this.enumStatus = enumStatus;
        }

        if (ListUtil.isNotNullOrEmpty(itemsBO)) {
            this.itemsBO = itemsBO;
        }

        sumTotalValue();

    }

}
