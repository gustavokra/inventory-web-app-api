package com.kraemer.domain.entities.enums;

import com.kraemer.domain.utils.EnumUtil;

public enum EnumDBImpl implements IEnum {
    
    SQLITE("SQLITE");

    private final String key;

    private EnumDBImpl(String key) {
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

    public static EnumDBImpl parseByKey(String key) {
        return (EnumDBImpl) EnumUtil.parseByKey(EnumDBImpl.class, key);
    }

}
