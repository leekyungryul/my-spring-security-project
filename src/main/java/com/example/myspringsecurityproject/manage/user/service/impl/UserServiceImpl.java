package com.example.myspringsecurityproject.manage.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myspringsecurityproject.manage.user.domain.UserVO;
import com.example.myspringsecurityproject.manage.user.repository.UserRepository;
import com.example.myspringsecurityproject.manage.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public int getDuplicationIdCnt(String param) {

        return repository.getDuplicationIdCnt(param);
    }

    /**
     * 비밀번호 변경
     * @param userVO
     * @return
     */
    @Override
    public int updateUserPwd(UserVO userVO) {

        return repository.updateUserPwd(userVO);

    }
}
