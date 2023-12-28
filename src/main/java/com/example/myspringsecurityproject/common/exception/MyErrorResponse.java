package com.example.myspringsecurityproject.common.exception;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.http.ResponseEntity;

import com.example.myspringsecurityproject.common.util.MyMessageUtil;

public class MyErrorResponse {

    private final LocalDateTime errorTime = LocalDateTime.now(ZoneOffset.UTC);
    private final LocalDateTime errorLocalTime = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public MyErrorResponse(int status, String error, String code, String message) {
        super();
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public LocalDateTime getErrorTime() {
        return errorTime;
    }

    public LocalDateTime getErrorLocalTime() {
        return errorLocalTime;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public static ResponseEntity<MyErrorResponse> toResponseEntity(MyErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(new MyErrorResponse(errorCode.getStatus(), errorCode.getError(), errorCode.getCode(), MyMessageUtil.getMessage("message.error." + errorCode.getCode())));
    }
}
