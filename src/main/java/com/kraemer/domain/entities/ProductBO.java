package com.kraemer.domain.entities;

import java.math.BigDecimal;
import java.util.Objects;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.NumericUtil;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InvetoryAppException;

public class ProductBO {

    private Long id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private String image;

    private SupplierBO supplier;

    private Boolean active;

    public ProductBO(Long id, String name, String description, BigDecimal price, Integer quantity, String image,
            SupplierBO supplier, Boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.supplier = supplier;
        this.active = active;

        validate();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getImage() {
        return image;
    }

    public SupplierBO getSupplier() {
        return supplier;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    private void validate() {

        if (NumericUtil.isNullOrZero(this.price)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "preço");
        }

        if (NumericUtil.isLessOrEqualsZero(this.quantity)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "quantidade");
        }

        if (Objects.isNull(this.supplier)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "fornecedor");
        }

        if (Objects.isNull(this.supplier.getId())) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id do fornecedor");
        }
    }

    public void handleUpdate(String name, String description, BigDecimal price, Integer quantity, String image,
            Boolean active) {
        if (StringUtil.isNotNullOrEmpty(name)) {
            this.name = name;
        }

        if (StringUtil.isNotNullOrEmpty(description)) {
            this.description = description;
        }

        if (!NumericUtil.isNullOrZero(quantity)) {
            if (!NumericUtil.isGreaterThanZero(price)) {
                throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "preço");
            }
        }

        if (!NumericUtil.isNullOrZero(quantity)) {
            if (!NumericUtil.isLessOrEqualsZero(price)) {
                this.price = price;
            }
        }

        if (NumericUtil.isGreater(quantity, -1)) {
            this.quantity = quantity;
        }

        if (StringUtil.isNotNullOrEmpty(image)) {
            this.image = image;
        }

        if (active != null) {
            this.active = active;
        }

    }

}
