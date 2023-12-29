package com.example.myspringsecurityproject.api.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.myspringsecurityproject.login.domain.KakaoUserInfo;
import com.example.myspringsecurityproject.login.domain.NaverUserInfo;
import com.example.myspringsecurityproject.login.service.LoginService;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

@RestController
@RequestMapping("/api/auth")
public class ApiAuthController {

    private static final Logger logger = LoggerFactory.getLogger(ApiAuthController.class);

    private static final String IS_OAUTH = "isOAuth";
    private static final String PROVIDER = "provider";
    private static final String PROVIDER_USER_ID = "providerUserId";
    private static final String OAUTH_MESSAGE = "oauthMessage";
    private static final String OAUTH2_USER_INFO = "oAuth2UserInfo";
    private static final String OAUTH2_USER_NAME = "name";
    private static final String OAUTH2_USER_EMAIL = "email";
    private static final String F_KAKAO = "kakao";
    private static final String F_NAVER = "naver";

    @Autowired
    private LoginService loginService;

    @Autowired
    private AuthenticationProvider oAuthAuthenticationProvider;

    @Value("${my.main}")
    private String main;

    /**
     * 카카오 로그인 callback
     * @param model
     * @param code
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/kakao/callback")
    public void kakaoCallback(Model model, String code, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Map<String, Object> result = loginService.findByKakaoId(code);
        UserVO user = (UserVO) result.get("user");
        KakaoUserInfo userInfo = (KakaoUserInfo) result.get(OAUTH2_USER_INFO);
        String name = userInfo.getProperties().getNickname();

        // 기존 사용자라면 로그인 처리
        if (null != user) {
            String id = user.getLoginId();
            String password = user.getUserPwd();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password);
            Authentication authentication = oAuthAuthenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            clearAuthenticationAttributes(request);
            HttpSession session = request.getSession(false);
            session.setAttribute("myUser", user);
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(user.getLanguage()));
            sendRedirect(request, response, user);
            // 기존 사용자가 아니라면 회원가입 페이지로 유도
        } else {
            request.setAttribute(IS_OAUTH, true);
            request.setAttribute(OAUTH_MESSAGE, "기존 가입정보가 없습니다.");
            request.setAttribute(OAUTH2_USER_NAME, name);
            request.setAttribute(OAUTH2_USER_EMAIL, userInfo.getKakao_account().getEmail());
            request.setAttribute(PROVIDER, F_KAKAO);
            request.setAttribute(PROVIDER_USER_ID, userInfo.getId());
            request.getRequestDispatcher("/join/register").forward(request, response);
        }
    }

    /**
     * 네이버 로그인 callback
     * @param model
     * @param code
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/naver/callback")
    public void naverCallback(Model model, String code, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        Map<String, Object> result = loginService.findByNaverId(code);
        UserVO user = (UserVO) result.get("user");
        NaverUserInfo userInfo = (NaverUserInfo) result.get(OAUTH2_USER_INFO);
        String name = userInfo.getName();
        String email = userInfo.getEmail();

        if (user != null) {
            // 기존 사용자라면 로그인 처리
            String id = user.getLoginId();
            String password = user.getUserPwd();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password);
            Authentication authentication = oAuthAuthenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            clearAuthenticationAttributes(request);
            HttpSession session = request.getSession(false);
            session.setAttribute("myUser", user);
            session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, new Locale(user.getLanguage()));
            sendRedirect(request, response, user);
        } else {
            // 기존 사용자가 아니라면 회원가입 페이지로 유도
            request.setAttribute(IS_OAUTH, true);
            request.setAttribute(OAUTH_MESSAGE, "기존 가입정보가 없습니다.");
            request.setAttribute(OAUTH2_USER_NAME, name);
            request.setAttribute(OAUTH2_USER_EMAIL, email);
            request.setAttribute(PROVIDER, F_NAVER);
            request.setAttribute(PROVIDER_USER_ID, userInfo.getId());
            request.getRequestDispatcher("/join/register").forward(request, response);
        }
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
