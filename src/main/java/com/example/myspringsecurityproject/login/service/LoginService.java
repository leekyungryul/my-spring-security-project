package com.example.myspringsecurityproject.login.service;

import java.util.Map;

import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.login.domain.KakaoUserInfo;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

public interface LoginService {

    UserVO findByUsername(Map<String, Object> params) throws MyException;

    Map<String, Object> findByKakaoId(String param) throws MyException;

    Map<String, Object> findByNaverId(String param) throws MyException;
    Map<String, Object> findByGoogleId(String param) throws MyException;
}
