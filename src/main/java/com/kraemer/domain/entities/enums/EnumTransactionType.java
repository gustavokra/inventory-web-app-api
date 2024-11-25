package com.kraemer.domain.entities.enums;

import com.kraemer.domain.utils.EnumUtil;

public enum EnumTransactionType implements IEnum {

    INPUT("INPUT"),
    OUTPUT("OUTPUT");

    private String key;

    private EnumTransactionType(String key) {
        this.key = key;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public boolean containsInEnum(String key) {
        return parseByKey(key) != null;
    }

    public static EnumTransactionType parseByKey(String key) {
        return (EnumTransactionType) EnumUtil.parseByKey(EnumTransactionType.class, key);
    }



    
}
