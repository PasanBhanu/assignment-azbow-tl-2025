package com.pasan.samples.leadcrm.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public record CommonResponse<T>(
        String statusCode,
        String statusDescription,
        T data
) {
    public CommonResponse(String statusCode, String statusDescription, @JsonInclude(JsonInclude.Include.NON_NULL) T data) {
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
        this.data = data;
    }

    public static ResponseEntity<CommonResponse> successResponse() {
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse("200", "success", null));
    }

    public static <T> ResponseEntity<CommonResponse<T>> successResponse(T data) {
        return ResponseEntity.status(HttpStatus.OK).body(new CommonResponse<T>("200", "success", data));
    }

    public static <T> ResponseEntity<CommonResponse> createdResponse(T id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new CommonResponse("201", "created", new CreatedResponse<T>(id)));
    }

    public static CommonResponse failResponse(String statusCode, String statusDescription) {
        return new CommonResponse(statusCode, statusDescription, null);
    }

    public static <T> CommonResponse failResponse(String statusCode, String statusDescription, T data) {
        return new CommonResponse(statusCode, statusDescription, data);
    }
}
