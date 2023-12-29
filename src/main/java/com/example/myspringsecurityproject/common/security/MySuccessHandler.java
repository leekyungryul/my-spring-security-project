package com.example.myspringsecurityproject.common.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.myspringsecurityproject.manage.user.domain.UserVO;

public class MySuccessHandler implements AuthenticationSuccessHandler {

    @Value("${my.main}")
    private String main;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        clearAuthenticationAttributes(request);

        HttpSession session = request.getSession(false);

        UserVO userVo = ((MySecurityUser) authentication.getPrincipal()).getMyUser();

        session.setAttribute("myUser", userVo);
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(userVo.getLanguage()));

        sendRedirect(request, response, userVo);

    }

    private void sendRedirect(HttpServletRequest request, HttpServletResponse response, UserVO userVo) throws IOException {

        String targetUrl = main;

        HttpSession session = request.getSession(false);

        if (session != null) {
            String prevPage = (String) session.getAttribute("referer");
            targetUrl = StringUtils.hasLength(prevPage) ? prevPage : targetUrl;
        }

        (new DefaultRedirectStrategy()).sendRedirect(request, response, targetUrl);

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
