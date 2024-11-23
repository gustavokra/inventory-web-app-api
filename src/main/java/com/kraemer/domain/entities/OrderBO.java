package com.kraemer.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.entities.enums.EnumOrderStatus;
import com.kraemer.domain.utils.exception.InvetoryAppException;

public class OrderBO {

    private Long id;

    private LocalDateTime date;

    private ClientBO client;

    private EnumOrderStatus status;

    private BigDecimal totalValue;

    public OrderBO(Long id, LocalDateTime date, ClientBO client, EnumOrderStatus status, BigDecimal totalValue) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.status = status;
        this.totalValue = totalValue;

        validate();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public ClientBO getClient() {
        return client;
    }

    public EnumOrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalValue() {
        return totalValue;
    }

    private void validate() {
        if (this.date == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "data criação");
        }

        if (this.client == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "cliente");
        }

        if (status == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "status");
        }

        if (totalValue == null) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "valor total");
        }
    }

}
