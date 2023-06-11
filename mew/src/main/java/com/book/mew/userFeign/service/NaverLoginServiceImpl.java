package com.book.mew.userFeign.service;

import com.book.mew.userFeign.dto.NaverLoginResponse;
import com.book.mew.userFeign.dto.SocialAuthResponse;
import com.book.mew.userFeign.dto.SocialUserResponse;
import com.book.mew.userFeign.enums.LoginType;
import com.book.mew.userFeign.feign.naver.NaverAuthApi;
import com.book.mew.userFeign.feign.naver.NaverUserApi;
import com.book.mew.userFeign.utils.GsonLocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Qualifier("naverLogin")
public class NaverLoginServiceImpl implements SocialLoginService{
    private final NaverAuthApi nAuthApi;
    private final NaverUserApi nUserApi;

    @Value("${social.client.naver.rest-api-key}")
    private String naverAppKey;
    @Value("${social.client.naver.secret-key}")
    private String naverAppSecret;
    @Value("${social.client.naver.redirect-uri}")
    private String naverRedirectUri;
    @Value("${social.client.naver.grant_type}")
    private String naverGrantType;

    @Override
    public LoginType getServiceName() {
        return LoginType.NAVER;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        ResponseEntity<?> response = nAuthApi.getAccessToken(
                naverGrantType,
                naverAppKey,
                naverAppSecret,
                authorizationCode,
                "state");

        log.info("naver auth response {}", response.toString());

        return new Gson()
                .fromJson(
                        String.valueOf(response.getBody())
                        , SocialAuthResponse.class
                );

    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        Map<String ,String> headerMap = new HashMap<>();
        headerMap.put("authorization", "Bearer " + accessToken);
        ResponseEntity<?> response = nUserApi.getUserInfo(headerMap);

        log.info("네이버 사용자 정보");
        log.info(response.toString());

        String jsonString = response.getBody().toString();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
                .create();

        NaverLoginResponse naverLoginResponse = gson.fromJson(jsonString, NaverLoginResponse.class);
        NaverLoginResponse.Response naverUserInfo = naverLoginResponse.getResponse();

        return SocialUserResponse.builder()
                .id(naverUserInfo.getId())
                .gender(naverUserInfo.getGender())
                .name(naverUserInfo.getName())
                .email(naverUserInfo.getEmail())
                .build();

    }
}
