package com.example.myspringsecurityproject.login.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.myspringsecurityproject.common.constant.MyConstant;
import com.example.myspringsecurityproject.common.security.MySecurityUser;
import com.example.myspringsecurityproject.login.service.LoginService;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

/**
 * 파일명 : MyUserDetailServiceImpl.java
 * 작성일 : 2023/12/18
 * 작성자 : 이경율
 * 설명 : Spring Security에서 사용자의 정보를 가져오는 클래스
 * 수정 내역
 * ---------------------------------------
 * | 수정일자 | 수정자 | 수정내역 |
 * ---------------------------------------
 */
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MyUserDetailServiceImpl.class);

    @Autowired
    LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        if (logger.isDebugEnabled()) {
            logger.debug(MyConstant.LOG_PARAM, this.getClass().getName(), "loadUserByUsername", loginId);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("loginId", loginId);

        UserVO user = loginService.findByUsername(params);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with loginId: " + loginId);
        }

        return new MySecurityUser(user);

    }
}
