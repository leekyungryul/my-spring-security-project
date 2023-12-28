package com.example.myspringsecurityproject.join.service.impl;

import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.myspringsecurityproject.common.exception.MyException;
import com.example.myspringsecurityproject.join.repository.JoinRepository;
import com.example.myspringsecurityproject.join.service.JoinService;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;
import com.example.myspringsecurityproject.manage.user.repository.UserRepository;

@Service
@Transactional(rollbackFor = MyException.class)
public class JoinServiceImpl implements JoinService {

    @Autowired
    private JoinRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(JoinServiceImpl.class);


    @Override
    public int saveJoinData(UserVO params) throws MyException {

        String newPassword = passwordEncoder.encode(params.getUserPwd());

        params.setUserPwd(newPassword);

        logger.info("params: {}", params);

        userRepository.insertUser(params);

        if (params.getProvider()!=null) {
            userRepository.insertOAuthUserInfo(params);
        }

        return 0;
    }
}
