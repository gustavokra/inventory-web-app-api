package com.kraemer.domain.entities.enums;

import com.kraemer.domain.utils.EnumUtil;

public enum EnumSituacaoCaixa implements IEnum {
    ABERTO("ABERTO"),
    FECHADO("FECHADO"),
    CANCELADO("CANCELADO");

    private String key;

    private EnumSituacaoCaixa(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    public static EnumSituacaoCaixa parseByKey(String key) {
        return (EnumSituacaoCaixa) EnumUtil.parseByKey(EnumSituacaoCaixa.class, key);
    }
    
    
}
