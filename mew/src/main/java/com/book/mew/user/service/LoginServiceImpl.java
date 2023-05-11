package com.book.mew.user.service;

import com.book.mew.user.dto.SocialAuthResponse;
import com.book.mew.user.dto.SocialUserResponse;
import com.book.mew.user.enums.LoginType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Component
@Qualifier("defaultLoginService") // check Qualifier
public class LoginServiceImpl implements SocialLoginService{
    @Override
    public LoginType getServiceName() {
        return LoginType.NORMAL;

    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        return null;

    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        return null;

    }

}