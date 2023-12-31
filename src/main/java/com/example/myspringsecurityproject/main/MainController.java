package com.example.myspringsecurityproject.main;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myspringsecurityproject.common.util.MySesstionUtil;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @RequestMapping("/main")
    public String mainPage(Model model) {

        UserVO userVO = MySesstionUtil.getMyUserFromSession();

        logger.debug("userVO : {}", userVO);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        logger.debug("authentication : {}", authentication);

        model.addAttribute("userVO", userVO);

        return "/main/main";
    }
}
