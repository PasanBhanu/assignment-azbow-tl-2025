package com.pasan.samples.leadcrm.config.exceptions;

import com.pasan.samples.leadcrm.util.ErrorCode;

public class CommonException extends RuntimeException {
    private String errorCode;
    private String errorDescription;

    public CommonException(ErrorCode errorCode) {
        super(errorCode.errorCode);
        this.errorCode = errorCode.errorCode;
        this.errorDescription = errorCode.errorDescription;
    }
}
