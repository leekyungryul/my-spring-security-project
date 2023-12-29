package com.example.myspringsecurityproject.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.myspringsecurityproject.common.security.MyAuthenticationProvider;
import com.example.myspringsecurityproject.common.security.MyFailureHandler;
import com.example.myspringsecurityproject.common.security.MyOAuthAuthenticationProvider;
import com.example.myspringsecurityproject.common.security.MySuccessHandler;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new MyAuthenticationProvider();
    }

    @Bean
    public AuthenticationProvider oAuthAuthenticationProvider() {
        return new MyOAuthAuthenticationProvider();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler(){
        return new MySuccessHandler();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new MyFailureHandler();
    }

    @Bean
    WebSecurityCustomizer webSecustomizer() {
        return (web) -> web.ignoring().antMatchers(
                "/css/**"
                , "/js/**"
                , "/images/**"
                , "/fonts/**"
                , "/favicon/**"
                , "/favicon.ico");
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors().disable()
                .csrf().disable()
                .headers()
                .and()
                .authorizeRequests().antMatchers("/", "/login","/forgotPassword", "/join/**", "/intro/**", "/auth/**", "/api/**").permitAll()
                .and()
                .authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/manage/**").hasAnyRole("ADMIN", "MANAGER")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler());

        return httpSecurity.build();
    }
}
