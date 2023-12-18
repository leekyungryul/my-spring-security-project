package com.example.myspringsecurityproject.login.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.myspringsecurityproject.manage.user.domain.UserVO;

@Repository
@Mapper
public interface LoginRepository {

    UserVO selectUser(Map<String, Object> params);

}
