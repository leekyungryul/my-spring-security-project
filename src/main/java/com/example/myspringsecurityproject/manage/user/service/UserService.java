package com.example.myspringsecurityproject.manage.user.service;

import com.example.myspringsecurityproject.manage.user.domain.UserVO;

public interface UserService {

    int getDuplicationIdCnt(String param);

    /**
     * 비밀번호 변경
     * @param userVO
     * @return
     */
    int updateUserPwd(UserVO userVO);
}
