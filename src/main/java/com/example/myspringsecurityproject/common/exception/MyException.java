package com.example.myspringsecurityproject.common.exception;

import java.io.Serializable;

import com.example.myspringsecurityproject.common.util.MyMessageUtil;

public class MyException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -185101301291172901L;

    private final MyErrorCode myErrorCode;

    public MyException(MyErrorCode myErrorCode) {
        super(MyMessageUtil.getMessage("message.error." + myErrorCode.getCode()));
        this.myErrorCode = myErrorCode;
    }

    public MyErrorCode getErrorCode() {
        return myErrorCode;
    }
}
