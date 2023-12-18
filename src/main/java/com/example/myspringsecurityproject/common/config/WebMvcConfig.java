package com.example.myspringsecurityproject.common.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.myspringsecurityproject.common.util.MyMessageUtil;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/login");
        registry.setOrder(1);
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource);
        MyMessageUtil.setMessageSourceAccessor(messageSourceAccessor);
        return messageSourceAccessor;
    }
}
