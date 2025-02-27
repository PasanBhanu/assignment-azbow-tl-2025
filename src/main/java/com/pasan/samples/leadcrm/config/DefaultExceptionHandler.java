package com.pasan.samples.leadcrm.config;

import com.pasan.samples.leadcrm.config.exceptions.CommonException;
import com.pasan.samples.leadcrm.controller.model.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler {

    /**
     * Handle application exception
     *
     * @param ex Exception
     * @return Response
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<CommonResponse> handleCommonException(CommonException ex) {
        CommonResponse response = CommonResponse.failResponse(ex.getErrorCode(), ex.getErrorDescription());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    /**
     * Handle request validation exception
     *
     * @param ex Exception
     * @return Response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResponse.failResponse("400", "validation failed", errors));
    }
}
