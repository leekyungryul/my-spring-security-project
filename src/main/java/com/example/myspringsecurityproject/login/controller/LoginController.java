package com.example.myspringsecurityproject.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.myspringsecurityproject.common.constant.MyConstant;
import com.example.myspringsecurityproject.common.exception.MyErrorCode;
import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.login.domain.KakaoOAuthToken;
import com.example.myspringsecurityproject.login.domain.KakaoUserInfo;
import com.example.myspringsecurityproject.login.domain.NaverUserInfo;
import com.example.myspringsecurityproject.login.service.LoginService;
import com.example.myspringsecurityproject.mail.service.MailService;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final String ERROR = "error";
    private static final String ERROR_CODE = "errorCode";
    private static final String ERROR_MESSAGE = "errorMessage";
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
    private LoginService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request) throws MyException{

        if (request.getAttribute(ERROR) != null && (Boolean)request.getAttribute(ERROR)) {
            model.addAttribute(ERROR, true);
            model.addAttribute(ERROR_CODE, request.getAttribute(ERROR_CODE));
            model.addAttribute(ERROR_MESSAGE, request.getAttribute(ERROR_MESSAGE));
        } else {
            request.removeAttribute(ERROR);
            model.addAttribute(ERROR, false);
        }

        if (request.getAttribute(IS_OAUTH) != null && (Boolean)request.getAttribute(IS_OAUTH)) {
            model.addAttribute(IS_OAUTH, true);
            model.addAttribute(PROVIDER, request.getAttribute(PROVIDER));
            model.addAttribute(OAUTH_MESSAGE, request.getAttribute(OAUTH_MESSAGE));
        } else {
            request.removeAttribute(IS_OAUTH);
            model.addAttribute(IS_OAUTH, false);
        }

        return "/login/login";
    }

    @RequestMapping("/forgotPassword")
    public String forgotPasswordPage() {
        return "/login/forgotPassword";
    }



    /**
     * 구글 로그인 callback
     * @param model
     * @param code
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping("/auth/google/callback")
    public void googleCallback(Model model, String code, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        logger.info("code: {}", code);
        logger.info("googleCallback");

        Map<String, Object> result = service.findByGoogleId(code);
//        UserVO user = (UserVO) result.get("user");
//        NaverUserInfo userInfo = (NaverUserInfo) result.get(OAUTH2_USER_INFO);
//        String name = userInfo.getName();
//        String email = userInfo.getEmail();
//
//        // 기존 사용자가 아니라면 회원가입 페이지로 유도
//        if (user == null) {
//            request.setAttribute(IS_OAUTH, true);
//            request.setAttribute(OAUTH_MESSAGE, "기존 가입정보가 없습니다.");
//            request.setAttribute(OAUTH2_USER_NAME, name);
//            request.setAttribute(OAUTH2_USER_EMAIL, email);
//            request.setAttribute(PROVIDER, "Naver");
//            request.getRequestDispatcher("/join/register").forward(request, response);
//            return;
//        }
//
//        // 기존 사용자라면 로그인 처리
//        String id = user.getLoginId();
//        String password = user.getUserPwd();
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id, password);
//        Authentication authentication = oAuthAuthenticationProvider.authenticate(token);
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        response.sendRedirect("/main");

    }
}
