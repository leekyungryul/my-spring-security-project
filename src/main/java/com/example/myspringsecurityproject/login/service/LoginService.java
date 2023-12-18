package com.example.myspringsecurityproject.login.service;

import java.util.Map;

import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

public interface LoginService {

    UserVO findByUsername(Map<String, Object> params) throws MyException;
}
