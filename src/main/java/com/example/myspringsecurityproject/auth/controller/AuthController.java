package com.example.myspringsecurityproject.auth.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.myspringsecurityproject.common.constant.MyConstant;
import com.example.myspringsecurityproject.common.exception.MyErrorCode;
import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.mail.service.MailService;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;
import com.example.myspringsecurityproject.manage.user.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserService userService;

    private String forgotPwdAuthCode = "";
    private String forgotPwdLoginId = "";

    @PostMapping("/forgotPwd/checkLoginId")
    @ResponseBody
    public Map<String, Object> checkLoginId(@RequestBody Map<String, Object> params, Model model) throws MyException {

        int result = userService.getDuplicationIdCnt((String) params.get("loginId"));

        if (result != 0) {
            forgotPwdLoginId = (String) params.get("loginId");
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    @GetMapping("/requestCode")
    public void requestToken(String provider, HttpServletRequest request, HttpServletResponse response) throws IOException {

        String url = "";

        switch (provider) {
            case "kakao":
                url = "https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=803bba301ece0ff0621c3e274fe8e6b2&redirect_uri=http://localhost:8080/api/auth/kakao/callback";
                break;
            case "naver":
                url = "https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=bQI9RwpG4sp4afTMjGx1&state=1234&redirect_uri=http://localhost:8080/api/auth/naver/callback";
                break;
            default:
                break;
        }

        response.sendRedirect(url);

    }

    @PostMapping("/forgotPwd/mailSend/requestAuthCode")
    @ResponseBody
    public Map<String, Object> forgotPassword(@RequestBody Map<String, Object> params, Model model) throws MyException {

        String email = params.get("email").toString();
        String code;
        try {
            code = mailService.sendForgotPwdMessage(email);
        } catch (Exception e) {
            logger.error(MyConstant.LOG_ERROR, e.getMessage());
            throw new MyException(MyErrorCode.MY_EXCEPTION);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("code", code);

        forgotPwdAuthCode = code;

        return result;
    }

    @PostMapping("/forgotPwd/checkAuthCode")
    @ResponseBody
    public Map<String, Object> checkAuthCode(@RequestBody Map<String, Object> params, Model model) throws MyException {

        boolean result = false;

        String code = (String) params.get("authCode");
        int elapsedTime = (int) params.get("elapsedTime");

        logger.info("code : {}", code);
        logger.info("elapsedTime : {}", elapsedTime);

        if (elapsedTime > 100) {
            throw new MyException(MyErrorCode.AUTHENTICATION_TIME_OUT_EXCEPTION);
        }

        if (code.equals(forgotPwdAuthCode)) {
            result = true;
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", result);

        return resultMap;
    }

    @PostMapping("/forgotPwd/resetNewPwd")
    @ResponseBody
    public Map<String, Object> resetNewPwd(@RequestBody Map<String, Object> params, Model model) throws MyException {

        String loginId = forgotPwdLoginId;
        String newPwd = (String) params.get("newPwd");
        String newPwdConfirm = (String) params.get("newPwdConfirm");

        logger.info("loginId : {}", loginId);
        logger.info("newPwd : {}", newPwd);

        if (!forgotPwdValidateExcution(loginId, newPwd, newPwdConfirm)) {
            throw new MyException(MyErrorCode.BAD_REQUEST_EXCEPTION);
        }

        UserVO userVO = new UserVO();
        userVO.setLoginId(loginId);
        userVO.setUserPwd(passwordEncoder.encode(newPwd));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("result", userService.updateUserPwd(userVO));

        return resultMap;
    }

    private boolean forgotPwdValidateExcution(String loginId, String newPwd, String newPwdConfirm) {

        if (loginId == null || loginId.equals("")) {
            return false;
        }
        if (newPwd == null || newPwd.equals("")) {
            return false;
        }
        if (newPwdConfirm == null || newPwdConfirm.equals("")) {
            return false;
        }
        if (!newPwd.equals(newPwdConfirm)) {
            return false;
        }

        return true;
    }
}
