package com.example.myspringsecurityproject.join.service;

import java.util.Map;

import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

public interface JoinService {

    int saveJoinData(UserVO params) throws MyException;

}
