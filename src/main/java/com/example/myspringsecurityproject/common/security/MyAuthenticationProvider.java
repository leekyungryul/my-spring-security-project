package com.example.myspringsecurityproject.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.myspringsecurityproject.login.service.impl.MyUserDetailServiceImpl;

/**
 * 파일명 : MyAuthenticationProvider.java
 * 작성일 : 2023/12/18
 * 작성자 : 이경율
 * 설명 : 인증 provider
 * 수정내역
 * ---------------------------------------
 * | 수정일자 | 수정자 | 수정내역
 * ---------------------------------------
 */
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MyUserDetailServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        User user = (User) userDetailsService.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException(username);
        }

        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
