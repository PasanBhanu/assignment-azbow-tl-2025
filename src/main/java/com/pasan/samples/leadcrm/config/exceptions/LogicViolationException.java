package com.pasan.samples.leadcrm.config.exceptions;

import com.pasan.samples.leadcrm.util.ErrorCode;

public class LogicViolationException extends CommonException{
    public LogicViolationException(ErrorCode errorCode) {
        super(errorCode);
    }
}
