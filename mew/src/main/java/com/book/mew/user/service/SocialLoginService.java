package com.book.mew.user.service;

import com.book.mew.user.dto.SocialAuthResponse;
import com.book.mew.user.dto.SocialUserResponse;
import com.book.mew.user.enums.LoginType;
import org.springframework.stereotype.Service;

@Service
public interface SocialLoginService {
    LoginType getServiceName();
    SocialAuthResponse getAccessToken(String authorizationCode);
    SocialUserResponse getUserInfo(String accessToken);
}
