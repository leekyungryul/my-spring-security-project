package com.example.myspringsecurityproject.common.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.myspringsecurityproject.common.domain.MyVO;
import com.example.myspringsecurityproject.manage.user.domain.UserRoleVO;
import com.example.myspringsecurityproject.manage.user.domain.UserVO;

public class MySecurityUser extends User {

    private static final long serialVersionUID = 1L;

    private UserVO myUser;
    private Map<String, Object> attributes;

    public MySecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public MySecurityUser(UserVO userVO) {

        super(userVO.getLoginId(), userVO.getUserPwd(),
                userVO.isEnabled(), !userVO.isExpired(), !userVO.isPasswordExpired(), !userVO.isLocked(),
                makeGrantedAuthority(userVO.getRoles())
            );

        this.myUser = userVO;
    }

    public MySecurityUser(UserVO userVo, Map<String, Object> attributes) {

        super(userVo.getLoginId(), userVo.getUserPwd(),
                userVo.isEnabled(), !userVo.isExpired(), !userVo.isPasswordExpired(), !userVo.isLocked(),
                makeGrantedAuthority(userVo.getRoles())
            );

        this.myUser = userVo;
        this.attributes = attributes;
    }

    private static Collection<? extends GrantedAuthority> makeGrantedAuthority(Collection<UserRoleVO> roles) {

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (UserRoleVO role : roles) {
            if (role.getRoleCode().contains("ADMIN")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else if (role.getRoleCode().contains("MANAGER")) {
                authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
            }
        }

        return authorities;
    }

    public UserVO getMyUser() {
        return myUser;
    }

    public void setMyUser(UserVO myUser) {
        this.myUser = myUser;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
