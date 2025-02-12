package com.kraemer.domain.entities.dto;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private MarcaDTO marcaDTO;

    private GrupoDTO grupoDTO;

    private BigDecimal price;

    private Integer quantity;

    private String image;

    private SupplierDTO supplier;

    private Boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public SupplierDTO getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierDTO supplier) {
        this.supplier = supplier;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public MarcaDTO getMarcaDTO() {
        return marcaDTO;
    }

    public void setMarcaDTO(MarcaDTO marcaDTO) {
        this.marcaDTO = marcaDTO;
    }

    public GrupoDTO getGrupoDTO() {
        return grupoDTO;
    }

    public void setGrupoDTO(GrupoDTO grupoDTO) {
        this.grupoDTO = grupoDTO;
    }

}
