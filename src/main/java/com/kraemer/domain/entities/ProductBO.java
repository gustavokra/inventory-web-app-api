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

    public ProductBO(Long id, String name, String description, BigDecimal price, Integer quantity, String image,
            SupplierBO supplier) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.supplier = supplier;

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

    private void validate() {

        if(NumericUtil.isNullOrZero(this.price)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "preço");
        }

        if(NumericUtil.isLessOrEqualsZero(this.quantity)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "quantidade");
        }

        if(StringUtil.isNullOrEmpty(this.image)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "imagem");
        }

        if(Objects.isNull(this.supplier)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "fornecedor");
        }

        if(Objects.isNull(this.supplier.getId())) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "id do fornecedor");
        }
    }

}
