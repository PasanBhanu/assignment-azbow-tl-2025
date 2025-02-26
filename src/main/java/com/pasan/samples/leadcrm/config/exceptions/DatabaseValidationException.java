package com.pasan.samples.leadcrm.config.exceptions;

import com.pasan.samples.leadcrm.util.ErrorCode;

public class DatabaseValidationException extends CommonException {

    public DatabaseValidationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
