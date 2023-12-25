package com.example.myspringsecurityproject.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private static final String ERROR = "error";
    private static final String ERROR_CODE = "errorCode";
    private static final String ERROR_MESSAGE = "errorMessage";

    @RequestMapping("/login")
    public String login(Model model, HttpServletRequest request) {

        if (request.getAttribute(ERROR) != null && (Boolean)request.getAttribute(ERROR)) {
            model.addAttribute(ERROR, true);
            model.addAttribute(ERROR_CODE, request.getAttribute(ERROR_CODE));
            model.addAttribute(ERROR_MESSAGE, request.getAttribute(ERROR_MESSAGE));
        } else {
            request.removeAttribute(ERROR);
            model.addAttribute(ERROR, false);
        }
        return "/login/login";
    }
}
