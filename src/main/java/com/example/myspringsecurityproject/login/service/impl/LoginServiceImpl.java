package com.example.myspringsecurityproject.login.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myspringsecurityproject.common.constant.MyConstant;
import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.login.repository.LoginRepository;
import com.example.myspringsecurityproject.login.service.LoginService;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

@Service
public class LoginServiceImpl implements LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    LoginRepository repository;

    @Override
    public UserVO findByUsername(Map<String, Object> params) throws MyException {

        if (logger.isDebugEnabled()) {
            logger.debug(MyConstant.LOG_PARAM, this.getClass().getName(), "findByUsername", params);
        }

        return repository.selectUser(params);

    }
}
