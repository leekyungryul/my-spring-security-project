package com.example.myspringsecurityproject.manage.user.domain;

import java.io.Serializable;
import java.util.List;

import com.example.myspringsecurityproject.common.domain.MyVO;

public class UserVO extends MyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String loginId;
    private String userName;
    private String status;
    private String email;
    private String userPwd;
    private String pwdChangeDttm;
    private String expireDttm;
    private int pwdFailCount;
    private boolean enabled;
    private boolean expired;
    private boolean passwordExpired;
    private int passwordRespite;
    private boolean locked;
    private String role;
    private List<UserRoleVO> roles;
    private String provider;
    private String providerUserId;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getPwdChangeDttm() {
        return pwdChangeDttm;
    }

    public void setPwdChangeDttm(String pwdChangeDttm) {
        this.pwdChangeDttm = pwdChangeDttm;
    }

    public String getExpireDttm() {
        return expireDttm;
    }

    public void setExpireDttm(String expireDttm) {
        this.expireDttm = expireDttm;
    }

    public int getPwdFailCount() {
        return pwdFailCount;
    }

    public void setPwdFailCount(int pwdFailCount) {
        this.pwdFailCount = pwdFailCount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public boolean isPasswordExpired() {
        return passwordExpired;
    }

    public void setPasswordExpired(boolean passwordExpired) {
        this.passwordExpired = passwordExpired;
    }

    public int getPasswordRespite() {
        return passwordRespite;
    }

    public void setPasswordRespite(int passwordRespite) {
        this.passwordRespite = passwordRespite;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<UserRoleVO> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleVO> roles) {
        this.roles = roles;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
}
