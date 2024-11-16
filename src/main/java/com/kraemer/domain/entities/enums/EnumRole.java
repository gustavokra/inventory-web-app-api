package com.kraemer.domain.entities.enums;

import com.kraemer.domain.utils.EnumUtil;

public enum EnumRole implements IEnum {
    
    ADMIN("ADMIN"),
    USER("USER");

    private final String key;

    private EnumRole(String key) {
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

    public static EnumRole parseByKey(String key) {
        return (EnumRole) EnumUtil.parseByKey(EnumRole.class, key);
    }
}
