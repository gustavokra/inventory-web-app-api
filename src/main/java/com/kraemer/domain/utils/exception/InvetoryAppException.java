package com.kraemer.domain.utils.exception;

import com.kraemer.domain.entities.enums.EnumErrorCode;
import com.kraemer.domain.utils.StringUtil;

public class InvetoryAppException extends RuntimeException {
    private String errorCode = "-1";

    public InvetoryAppException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public InvetoryAppException(EnumErrorCode error) {
        super(error.getErro());
        this.errorCode = error.getKey();
    }

    public InvetoryAppException(EnumErrorCode error, Object... args) {
        super(StringUtil.stringPatternFormat(error.getErro(), args));
        this.errorCode = error.getKey();
    }

    public String getErrorCode() {
        return errorCode;
    }
}
