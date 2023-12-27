package com.example.myspringsecurityproject.manage.user.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.myspringsecurityproject.manage.user.domain.UserVO;

@Repository
@Mapper
public interface UserRepository {

    /**
     * 사용자 추가
     * @param params
     * @return
     */
    int insertUser(UserVO params);

    /**
     * OAuth 정보 추가
     * @param params
     * @return
     */
    int insertOAuthUserInfo(UserVO params);

    /**
     * 중복 아이디 체크
     * @param param
     * @return
     */
    int getDuplicationIdCnt(String param);
}
