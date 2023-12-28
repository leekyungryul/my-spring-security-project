package com.example.myspringsecurityproject.mail.service;

import javax.mail.internet.MimeMessage;

public interface MailService {

    /**
     * 메일 내용 작성
     * @param to
     * @return
     * @throws Exception
     */
    MimeMessage createMessage(String to) throws Exception;

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
    String sendForgotPwdMessage(String to) throws Exception;

}
