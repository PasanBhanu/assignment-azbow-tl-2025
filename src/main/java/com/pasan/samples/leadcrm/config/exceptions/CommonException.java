package com.pasan.samples.leadcrm.config.exceptions;

import com.pasan.samples.leadcrm.util.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CommonException extends RuntimeException {
    private String errorCode;
    private String errorDescription;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public CommonException(ErrorCode errorCode, HttpStatus httpStatus) {
        super(errorCode.errorCode);
        this.errorCode = errorCode.errorCode;
        this.errorDescription = errorCode.errorDescription;
    }
}
