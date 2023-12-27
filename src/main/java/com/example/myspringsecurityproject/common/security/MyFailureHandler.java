package com.example.myspringsecurityproject.common.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.example.myspringsecurityproject.common.constant.MyConstant;
import com.example.myspringsecurityproject.common.exception.MyErrorCode;
import com.example.myspringsecurityproject.common.util.MyMessageUtil;

public class MyFailureHandler implements AuthenticationFailureHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        saveLoginLog(exception);

        writePrintErrorResponse(request, exception);

        sendForward(request, response, "/login");
    }

    private void sendForward(HttpServletRequest request, HttpServletResponse response, String targetUrl) throws ServletException, IOException {
        request.getRequestDispatcher(targetUrl).forward(request, response);
    }

    private void writePrintErrorResponse(HttpServletRequest request, AuthenticationException exception) {

        String errorCode = "";
        String errorMessage = "";

        if (exception instanceof UsernameNotFoundException) {
            errorCode = MyErrorCode.USER_NOT_FOUND.getCode();
        } else if (exception instanceof BadCredentialsException) {
            // 비밀번호 틀림
            errorCode = MyErrorCode.CREDENTIAL_NOT_MATCHED_EXCEPTION.getCode();
        } else if (exception instanceof LockedException) {
            // 계정 잠김
            errorCode = MyErrorCode.USER_LOCKED_EXCEPTION.getCode();
        } else if (exception instanceof DisabledException) {
            // 계정 비활성화
            errorCode = MyErrorCode.USER_DISABLE_EXCEPTION.getCode();
        } else if (exception instanceof CredentialsExpiredException) {
            // 비밀번호 만료
            errorCode = MyErrorCode.CREDENTIAL_EXPIRED_EXCEPTION.getCode();
        } else {
            // 기타
            errorCode = "";
        }

        errorMessage = MyMessageUtil.getMessage("message.error." + errorCode);

        request.setAttribute("error", true);
        request.setAttribute("errorCode", errorCode);
        request.setAttribute("errorMessage", errorMessage);
    }

    private void saveLoginLog(AuthenticationException exception) {

        if (logger.isDebugEnabled()) {
            logger.debug(MyConstant.LOG_PARAM, this.getClass().getName(), "saveLoginLog", exception);
        }

        if (exception instanceof BadCredentialsException) {
            // 비밀번호 틀림
            logger.info("비밀번호 틀림");

        }
    }
}
