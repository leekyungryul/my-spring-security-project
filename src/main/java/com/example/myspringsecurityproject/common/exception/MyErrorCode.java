package com.example.myspringsecurityproject.common.exception;

public enum MyErrorCode {
    USER_NOT_FOUND(404, "User not found", "2001"),;

    private final int status;
    private final String error;
    private final String code;

    MyErrorCode(final int status, final String error, final String code) {
        this.status = status;
        this.error = error;
        this.code = code;
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
}
