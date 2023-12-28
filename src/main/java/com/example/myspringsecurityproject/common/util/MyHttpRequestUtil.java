package com.example.myspringsecurityproject.common.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class MyHttpRequestUtil {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MyHttpRequestUtil.class);

    private MyHttpRequestUtil() {
    }

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }
}
