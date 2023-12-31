package com.example.myspringsecurityproject.common.exception;

public enum MyErrorCode {
    USER_NOT_FOUND(404, "User not found", "2001"),
    CREDENTIAL_NOT_MATCHED_EXCEPTION(401, "Credential Not Matched Exception", "2002"),
    USER_DISABLE_EXCEPTION(401, "User Disable Exception", "2003"),
    USER_LOCKED_EXCEPTION(401, "User Locked Exception", "2004"),
    CREDENTIAL_EXPIRED_EXCEPTION(401, "Account Expired Exception", "2005"),
    BAD_REQUEST_EXCEPTION(400, "Bad Request Exception", "400"),
    AUTHENTICATION_TIME_OUT_EXCEPTION(400, "Authentication Time Out Exception", "2006"),
    UN_AUTHORIZED_EXCEPTION(401, "Unauthorized Exception", "401"),
    FORBIDDEN_EXCEPTION(403, "Forbidden Exception", "403"),
    NOT_FOUND_EXCEPTION(404, "Not Found Exception", "404"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed Exception", "405"),
    MY_EXCEPTION(406, "My Exception", "1000"),
    REQUEST_TIMEOUT_EXCEPTION(408, "Request Timeout Exception", "408"),
    USER_EXIST_EXCEPTION(400, "User Exist Exception", "409"),

    INTERNAL_SERVER_ERROR_EXCEPTION(500, "INTERNAL_SERVER_ERROR", "500"),;

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
