package com.example.myspringsecurityproject.join.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myspringsecurityproject.join.service.JoinService;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

@Controller
@RequestMapping("/join")
public class JoinController {

    private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

    @Autowired
    private JoinService service;

    @GetMapping("/register")
    public String join() {

        return "/join/register";
    }

    @PostMapping("/register")
    @ResponseBody
    public int joinPost(@RequestBody UserVO param) {

        logger.info("param: {}", param);

        service.saveJoinData(param);

        return 0;
    }
}
