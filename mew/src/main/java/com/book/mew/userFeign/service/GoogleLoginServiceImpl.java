package com.book.mew.userFeign.service;

import com.book.mew.userFeign.dto.GoogleLoginResponse;
import com.book.mew.userFeign.dto.GoogleRequestAccessToken;
import com.book.mew.userFeign.dto.SocialAuthResponse;
import com.book.mew.userFeign.dto.SocialUserResponse;
import com.book.mew.userFeign.enums.LoginType;
import com.book.mew.userFeign.feign.google.GoogleAuthApi;
import com.book.mew.userFeign.feign.google.GoogleUserApi;
import com.book.mew.userFeign.utils.GsonLocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Qualifier("googleLogin")
public class GoogleLoginServiceImpl implements SocialLoginService {
    private final GoogleAuthApi gAuthApi;
    private final GoogleUserApi gUserApi;

    @Value("${social.client.google.rest-api-key}")
    private String googleAppKey;
    @Value("${social.client.google.secret-key}")
    private String googleAppSecret;
    @Value("${social.client.google.redirect-uri}")
    private String googleRedirectUri;
    @Value("${social.client.google.grant_type}")
    private String googleGrantType;

    @Override
    public LoginType getServiceName() {
        return LoginType.GOOGLE;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        ResponseEntity<?> response = gAuthApi.getAccessToken(
                GoogleRequestAccessToken.builder()
                        .code(authorizationCode)
                        .client_id(googleAppKey)
                        .client_secret(googleAppSecret)
                        .redirect_uri(googleRedirectUri)
                        .grant_type(googleGrantType)
                        .build()
        );

        log.info("구글 인증 정보");
        log.info(response.toString());

        return new Gson()
                .fromJson(
                        response.getBody().toString(),
                        SocialAuthResponse.class
                );
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        ResponseEntity<?> response = gUserApi.getUserInfo(accessToken);

        log.info("구글 사용자 정보");
        log.info(response.toString());

        String jsonString = response.getBody().toString();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
                .create();

        GoogleLoginResponse googleLoginResponse = gson.fromJson(jsonString, GoogleLoginResponse.class);

        return SocialUserResponse.builder()
                .id(googleLoginResponse.getId())
                .email(googleLoginResponse.getEmail())
                .build();
    }

}
