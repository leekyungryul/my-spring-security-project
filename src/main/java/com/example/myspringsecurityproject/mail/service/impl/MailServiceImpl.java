package com.example.myspringsecurityproject.mail.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.intro.domain.ContactVO;
import com.example.myspringsecurityproject.mail.service.MailService;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${my.mail.owner-email}")
    private String ownerEmail;

    @Value("${my.mail.owner-name}")
    private String ownerName;

    @Value("${my.mail.project-name}")
    private String projectName;

    private String ePw;

    @Override
    public MimeMessage createMessage(String to) throws MyException, MessagingException, UnsupportedEncodingException {

        if (logger.isDebugEnabled()) {
            logger.debug("createMessage called");
            logger.debug("to: " + to);
            logger.debug("ePw: " + ePw);
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject("My Project");

        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<h1> 안녕하세요</h1>";
        msg += "<h1> My Project 입니다.</h1>";
        msg += "<br>";
        msg += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msg += "<h3 style='color:blue;'>비밀번호 찾기 인증코드입니다.</h3>";
        msg += "<div style='font-size:130%'>";
        msg += "CODE : <strong>";
        msg += ePw + "</strong><div><br/> "; // 메일에 인증번호 넣기
        msg += "</div>";
        message.setText(msg, "utf-8", "html");
        message.setFrom(new InternetAddress("leekyung1111@gmail.com", "이경율"));

        return message;
    }

    /**
     * 랜덤 인증 코드 생성
     * @return
     */
    @Override
    public String createKey() {
        logger.info("createKey called");
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int index = rnd.nextInt(3);
            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    break;
            }
        }

        return key.toString();
    }

    @Override
    public String sendForgotPwdMessage(String to) throws MyException, MessagingException, UnsupportedEncodingException {

        ePw = createKey();

        MimeMessage message = createMessage(to);

        javaMailSender.send(message);

        return ePw;
    }

    @Override
    public void sendContactMeMail(ContactVO param) throws Exception {

        String name = param.getName();
        String email = param.getEmail();
        String phone = param.getPhone();
        String contents = param.getMessage();

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, ownerEmail);
        message.setSubject("Contact Me");
        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<h1>My Project로부터 받은 메시지</h1>";
        msg += "<br>";
        msg += "<div align='center' style='border:1px solid #dadce0; font-family:verdana; padding:20px'>";
        msg += "<h3 style='color:#4d90fe; text-align:left'>보낸 사람 : " + name + "</h3>";
        msg += "<h3 style='color:#4d90fe; text-align:left'>보낸 사람 이메일 : " + email + "</h3>";
        msg += "<h3 style='color:#4d90fe; text-align:left'>보낸 사람 전화번호 : " + phone + "</h3>";
        msg += "<h3 style='color:#4d90fe; text-align:left'>내용</h3>";
        msg += "<div style='font-size:130%; text-align:left'>";
        msg += contents + "<div><br/> ";
        msg += "</div>";
        message.setText(msg, "utf-8", "html");
        message.setFrom(new InternetAddress(email, name));

        javaMailSender.send(message);

        responseContactMeMail(param);
    }

    private void responseContactMeMail(ContactVO param) throws Exception {

        String name = param.getName();
        String email = param.getEmail();

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("안녕하세요 My Project입니다.");
        String msg = "";
        msg += "<div style='margin:100px;'>";
        msg += "<h1>" + name + "님</h1>";
        msg += "<h1>My Project로부터 메시지를 받았습니다.</h1>";
        msg += "<br>";
        msg += "<div align='center' style='border:1px solid #dadce0; font-family:verdana; padding:20px'>";
        msg += "<h3 style='color:black; text-align:left'>이름 : " + name + "</h3>";
        msg += "<h3 style='color:black; text-align:left'>이메일 : " + email + "</h3>";
        msg += "<h3 style='color:black; text-align:left'>내용 :</h3>";
        msg += "<div style='font-size:130%; text-align:left'>";
        msg += "<h4>"+param.getMessage() + "</h4><br/> ";
        msg += "</div>";
        msg += "</div>";
        msg += "<h3 style='color:black; text-align:left'>위 내용에 대해 빠른 시일 내에 답변 드리겠습니다.</h3>";
        message.setText(msg, "utf-8", "html");
        message.setFrom(new InternetAddress(email, projectName));

        javaMailSender.send(message);

    }
}
