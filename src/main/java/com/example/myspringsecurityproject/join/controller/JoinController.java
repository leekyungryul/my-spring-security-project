package com.example.myspringsecurityproject.join.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/join")
public class JoinController {

    private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

    @GetMapping("/register")
    public String join() {

        return "/join/register";
    }
}
