package com.kraemer.domain.entities;

import java.util.Objects;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.CpfCnpjUtil;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class SupplierBO {
    
    private Long id;

    private String name;

    private String document;

    private String contact;

    private String address;

    private Boolean active;

    public SupplierBO(Long id, String name, String document, String contact, String address, Boolean active) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.contact = contact;
        this.address = address;
        this.active = active;

        validate();
    }

    public SupplierBO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getContact() {
        return contact;
    }

    public String getAddress() {
        return address;
    }

    public Boolean isActive() {
        return active;
    }

    private void validate() {

        if(StringUtil.isNullOrEmpty(this.getName())) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "nome");
        }

        validateDocument();

        // if(StringUtil.isNullOrEmpty(this.getContact())) {
        //     throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "contato");
        // }

        if(Objects.isNull(this.isActive())) {
            this.active = true;
        }
    
    }

    private void validateDocument() {
        if(StringUtil.isNotNullOrEmpty(this.getDocument()) && !CpfCnpjUtil.isValidCpfOrCnpj(this.document)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "CNPJ");
        }
    }

    public void handleUpdateInfo(String name, String document, String contact, String address, Boolean active) {
        if(StringUtil.isNotNullOrEmpty(name)) {
            this.name = name;
        }

        if(StringUtil.isNotNullOrEmpty(document)) {
            this.document = document;
            validateDocument();
        }

        if(StringUtil.isNotNullOrEmpty(contact)) {
            this.contact = contact;
        }

        if(StringUtil.isNotNullOrEmpty(address)) {
            this.address = address;
        }

        if(!Objects.isNull(active)) {
            this.active = active;
        }
    }


}