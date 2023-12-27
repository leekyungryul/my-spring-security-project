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
}
