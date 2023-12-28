package com.example.myspringsecurityproject.common.util;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;

import com.example.myspringsecurityproject.common.constant.MyConstant;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

public class MySesstionUtil {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MySesstionUtil.class);

    private MySesstionUtil() {
    }

    public static HttpSession getSession() {
        return MyHttpRequestUtil.getCurrentRequest().getSession();
    }

    public static UserVO getMyUserFromSession() {
        return (UserVO) getSession().getAttribute(MyConstant.MY_USER);
    }


}
