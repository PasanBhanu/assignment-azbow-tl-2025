package com.pasan.samples.leadcrm.config;

import com.pasan.samples.leadcrm.config.exceptions.CommonException;
import com.pasan.samples.leadcrm.controller.model.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler {
    @ExceptionHandler(CommonException.class)
    protected ResponseEntity<CommonResponse> handleCommonException(CommonException ex) {
        CommonResponse response = CommonResponse.failResponse(ex.getErrorCode(), ex.getErrorDescription());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
