package com.example.myspringsecurityproject.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;

public class MyMessageUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private static MessageSourceAccessor messageSourceAccessor;

    private MyMessageUtil() {}

    public static void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        MyMessageUtil.messageSourceAccessor = messageSourceAccessor;
    }

    public static String getMessage(String key) {

        if (messageSourceAccessor == null) {
            return "MessageSourceAccessor is not initialized.";
        }

        return messageSourceAccessor.getMessage(key);
    }

}
