package com.pasan.samples.leadcrm.config.exceptions;

import com.pasan.samples.leadcrm.util.ErrorCode;
import org.springframework.http.HttpStatus;

public class LogicViolationException extends CommonException{
    public LogicViolationException(ErrorCode errorCode) {
        super(errorCode, HttpStatus.BAD_REQUEST);
    }
}
