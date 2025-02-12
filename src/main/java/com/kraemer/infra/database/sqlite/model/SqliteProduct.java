package com.kraemer.infra.database.sqlite.model;

import java.math.BigDecimal;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class SqliteProduct extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 150)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "marca_id")
    private SqliteMarca marca;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "grupo_id")
    private SqliteGrupo grupo;

    @Column(scale = 2)
    private BigDecimal price;

    @Column(scale = 0)
    private Integer quantity;

    private String image;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "supplier_id")
    private SqliteSupplier supplier;

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

    public SqliteSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(SqliteSupplier supplier) {
        this.supplier = supplier;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public SqliteMarca getMarca() {
        return marca;
    }

    public void setMarca(SqliteMarca marca) {
        this.marca = marca;
    }

    public SqliteGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(SqliteGrupo grupo) {
        this.grupo = grupo;
    }

}
