package com.book.mew.userFeign.service;

import com.book.mew.userFeign.dto.SocialAuthResponse;
import com.book.mew.userFeign.dto.SocialUserResponse;
import com.book.mew.userFeign.enums.LoginType;
import org.springframework.stereotype.Service;

@Service
public interface SocialLoginService {
    LoginType getServiceName();
    SocialAuthResponse getAccessToken(String authorizationCode);
    SocialUserResponse getUserInfo(String accessToken);
}
