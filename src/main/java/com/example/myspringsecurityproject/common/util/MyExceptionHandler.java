package com.example.myspringsecurityproject.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.myspringsecurityproject.common.exception.MyErrorResponse;
import com.example.myspringsecurityproject.common.exception.MyException;

/**
 * 파일명 : MyExceptionHandler
 * 작성일 : 2023-12-18
 * 작성자 : 이경율
 * 설명 : 공통 에러처리
 *
 * 수정내역
 * ---------------------------------------
 * | 수정일자 | 수정자 | 수정내역 |
 * ---------------------------------------
 */
@ControllerAdvice
public class MyExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ExceptionHandler(value = {MyException.class})
    public ResponseEntity<MyErrorResponse> handleAll(MyException e) {

        // TODO : 에러 로그 db 처리 추가 필요
        logger.error("handleAll throw Exception: {}", e.getErrorCode());

        return MyErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
