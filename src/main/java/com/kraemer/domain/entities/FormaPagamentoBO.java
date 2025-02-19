package com.kraemer.domain.entities;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.NumericUtil;
import com.kraemer.domain.utils.StringUtil;
import com.kraemer.domain.utils.exception.InventoryAppException;

public class FormaPagamentoBO {

    private Long id;
    
    private String nome;

    private Integer numeroMaxParcelas;

    public FormaPagamentoBO(Long id, String nome, Integer numeroMaxParcelas) {
        this.id = id;
        this.nome = nome;
        this.numeroMaxParcelas = numeroMaxParcelas;

        validate();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getnumeroMaxParcelas() {
        return numeroMaxParcelas;
    }

    private void validate() {
        if(StringUtil.isNullOrEmpty(nome)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "nome");
        }

        if(NumericUtil.isNullOrZero(numeroMaxParcelas)) {
            throw new InventoryAppException(EnumErrorCode.CAMPO_OBRIGATORIO, "número de parcelas deve ser maior que 0");
        }
    }

    public void verificarAtualizacao(String nome, Integer numeroMaxParcelas) {
        if(StringUtil.isNotNullOrEmpty(nome)) {
            this.nome = nome;
        }

        if(!NumericUtil.isNullOrZero(numeroMaxParcelas)) {
            this.numeroMaxParcelas = numeroMaxParcelas;
        }
    }
}

