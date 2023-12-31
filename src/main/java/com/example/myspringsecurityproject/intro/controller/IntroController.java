package com.example.myspringsecurityproject.intro.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.myspringsecurityproject.common.exception.MyErrorCode;
import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.intro.domain.ContactVO;
import com.example.myspringsecurityproject.intro.service.IntroService;
import com.example.myspringsecurityproject.mail.service.MailService;

@Controller
@RequestMapping("/intro")
public class IntroController {

    private static final Logger logger = LoggerFactory.getLogger(IntroController.class);

    @Autowired
    private IntroService introService;

    @Autowired
    private MailService mailService;

    /**
     * 메인 페이지
     *
     * @return
     */
    @RequestMapping("/list")
    public String index() {

        return "/intro/list";
    }

    /**
     * contact me 메일 발송
     *
     */
    @PostMapping("/contact/mailSend")
    @ResponseBody
    public Map<String, Object> contact(ContactVO param) throws MyException {

        if (logger.isDebugEnabled()) {
            logger.debug("param : {}", param.toString());
        }

        try {
            mailService.sendContactMeMail(param);
        } catch (Exception e) {
            throw new MyException(MyErrorCode.INTERNAL_SERVER_ERROR_EXCEPTION);
        }

        Map<String, Object> result = new HashMap<>();

        result.put("result", "success");

        return result;
    }

}
