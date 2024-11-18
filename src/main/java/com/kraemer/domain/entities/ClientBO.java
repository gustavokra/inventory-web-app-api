package com.kraemer.domain.entities;

import java.util.Objects;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.CpfCnpjUtil;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InvetoryAppException;

public class ClientBO {
    
    private Long id;

    private String name;

    private String document;

    private String contact;

    private String address;

    private Boolean active;

    public ClientBO(Long id, String name, String document, String contact, String address, Boolean active) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.contact = contact;
        this.address = address;
        this.active = active;

        validate();
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

        validateDocument();
        
        if(StringUtil.isNullOrEmpty(this.getName())) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "nome");
        }

        if(StringUtil.isNullOrEmpty(this.getContact())) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "contato");
        }

        if(Objects.isNull(this.isActive())) {
            this.active = true;
        }
    
    }

    private void validateDocument() {
        if(StringUtil.isNullOrEmpty(this.getDocument())) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "CPF/CNPJ");
        }

        if(!CpfCnpjUtil.isValidCpfOrCnpj(this.document)) {
            throw new InvetoryAppException(EnumErrorCode.CAMPO_INVALIDO, "CPF/CNPJ");
        }
    }

    public void handleUpdateInfo(String name, String document, String contact, String address, Boolean active) {
        if(StringUtil.isNotNullOrEmpty(name)) {
            this.name = name;
        }

        if(StringUtil.isNotNullOrEmpty(document)) {
            this.document = document;
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