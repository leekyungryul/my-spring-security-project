package com.example.myspringsecurityproject.join.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myspringsecurityproject.common.exception.MyErrorCode;
import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.join.service.JoinService;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;
import com.example.myspringsecurityproject.manage.user.service.UserService;

@Controller
@RequestMapping("/join")
public class JoinController {

    private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

    @Autowired
    private JoinService service;

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String join(Model model, HttpServletRequest request) {

        if (request.getAttribute("isOAuth") != null && (Boolean)request.getAttribute("isOAuth")) {
            model.addAttribute("isOAuth", true);
            model.addAttribute("provider", request.getAttribute("provider"));
            model.addAttribute("providerUserId", request.getAttribute("providerUserId"));
            model.addAttribute("name", request.getAttribute("name"));
            model.addAttribute("email", request.getAttribute("email"));
            model.addAttribute("oauthMessage", request.getAttribute("oauthMessage"));
        } else {
            model.addAttribute("isOAuth", false);
        }

        if (request.getAttribute("error") != null && (Boolean)request.getAttribute("error")) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", request.getAttribute("message"));
        } else {
            model.addAttribute("error", false);
        }

        return "/join/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public int joinPost(@RequestBody UserVO param, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("param: {}", param);

        if (!validateExcution(param)) {
            throw new MyException(MyErrorCode.BAD_REQUEST_EXCEPTION);
        }

        service.saveJoinData(param);

        return 0;
    }

    private boolean validateExcution(UserVO param) {

        if (param.getLoginId() == null || param.getLoginId().equals("")) {
            return false;
        }
        if (param.getUserPwd() == null || param.getUserPwd().equals("")) {
            return false;
        }
        if (param.getUserName() == null || param.getUserName().equals("")) {
            return false;
        }
        if (param.getEmail() == null || param.getEmail().equals("")) {
            return false;
        }
        if (!duplicateCheck(param.getLoginId())) {
            throw new MyException(MyErrorCode.USER_EXIST_EXCEPTION);
        }

        return true;

    }

    private boolean duplicateCheck(String param) {
        return userService.getDuplicationIdCnt(param) <= 0;
    }
}
