package com.example.myspringsecurityproject.manage.user.domain;

public class UserOAuth2VO extends UserVO {

    private String provider;
    private String providerUserId;

    @Override
    public String getProvider() {
        return provider;
    }

    @Override
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @Override
    public String getProviderUserId() {
        return providerUserId;
    }

    @Override
    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }
}
