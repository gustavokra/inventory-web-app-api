package com.kraemer.domain.entities;

import java.util.Objects;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.CpfCnpjUtil;
import com.kraemer.domain.utils.NumericUtil;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class ClientBO {

    private Long id;

    private String name;

    private String document;

    private String contact;

    private String address;

    private Long idLoja;

    private Boolean active;

    public ClientBO(Long id, String name, String document, String contact, String address, Long idLoja, Boolean active) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.contact = contact;
        this.address = address;
        this.idLoja = idLoja;
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

    public Long getIdLoja() {
        return idLoja;
    }

    public Boolean isActive() {
        return active;
    }

    private void validate() {

        if(NumericUtil.isNullOrZero(this.idLoja)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "idLoja");
        }

        validateDocument();

        if (StringUtil.isNullOrEmpty(this.getName())) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "nome");
        }

        if (Objects.isNull(this.isActive())) {
            this.active = true;
        }

    }

    private void validateDocument() {
        if (StringUtil.isNotNullOrEmpty(this.getDocument()) && !CpfCnpjUtil.isValidCpfOrCnpj(this.document)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_INVALIDO, "CPF/CNPJ");
        }
    }

    public void handleUpdateInfo(String name, String document, String contact, String address, Long idLoja, Boolean active) {
        if (StringUtil.isNotNullOrEmpty(name)) {
            this.name = name;
        }

        if (StringUtil.isNotNullOrEmpty(document)) {
            this.document = document;
            validateDocument();
        }

        if (StringUtil.isNotNullOrEmpty(contact)) {
            this.contact = contact;
        }

        if (StringUtil.isNotNullOrEmpty(address)) {
            this.address = address;
        }

        if (NumericUtil.isNullOrZero(idLoja)) {
            this.idLoja = idLoja;
        }

        if (!Objects.isNull(active)) {
            this.active = active;
        }
    }

}