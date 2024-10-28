package com.kraemer.domain.vo;

public class QueryFieldInfoVO {
    private String fieldName;
    private String fieldValue;
    
    public QueryFieldInfoVO(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }
    
}
