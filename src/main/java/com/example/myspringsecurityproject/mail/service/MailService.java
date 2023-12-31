package com.example.myspringsecurityproject.mail.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.intro.domain.ContactVO;

public interface MailService {

    /**
     * 메일 내용 작성
     * @param to
     * @return
     * @throws Exception
     */
    MimeMessage createMessage(String to) throws MyException, MessagingException, UnsupportedEncodingException;

    /**
     * 랜덤 인증 코드 전송
     * @return
     */
    String createKey();

    /**
     * 메일 발송
     * @param to
     * @return
     * @throws Exception
     */
    String sendForgotPwdMessage(String to) throws MyException, MessagingException, UnsupportedEncodingException;

    void sendContactMeMail(ContactVO param) throws Exception;
}
