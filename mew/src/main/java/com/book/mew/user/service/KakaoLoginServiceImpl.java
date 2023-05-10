package com.book.mew.user.service;

import com.book.mew.user.dto.KakaoLoginResponse;
import com.book.mew.user.dto.SocialAuthResponse;
import com.book.mew.user.dto.SocialUserResponse;
import com.book.mew.user.enums.LoginType;
import com.book.mew.user.feign.kakao.KakaoAuthApi;
import com.book.mew.user.feign.kakao.KakaoUserApi;
import com.book.mew.user.utils.GsonLocalDateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Qualifier("kakaoLogin")
public class KakaoLoginServiceImpl implements SocialLoginService{
    private final KakaoAuthApi kAuthApi;
    private final KakaoUserApi kUserApi;

    @Value("${social.client.kakao.rest-api-key}")
    private String kakaoAppKey;
    @Value("${social.client.kakao.secret-key}")
    private String kakaoAppSecret;
    @Value("${social.client.kakao.redirect-uri}")
    private String kakaoRedirectUri;
    @Value("${social.client.kakao.grant_type}")
    private String kakaoGrantType;


    @Override
    public LoginType getServiceName() {
        return LoginType.KAKAO;
    }

    @Override
    public SocialAuthResponse getAccessToken(String authorizationCode) {
        ResponseEntity<?> response = kAuthApi.getAccessToken(
                kakaoAppKey,
                kakaoAppSecret,
                kakaoGrantType,
                kakaoRedirectUri,
                authorizationCode
        );

        log.info("kakao auth response {}", response.toString());

        return new Gson()
                .fromJson(
                        String.valueOf(response.getBody())
                        , SocialAuthResponse.class
                );
    }

    @Override
    public SocialUserResponse getUserInfo(String accessToken) {
        Map<String ,String> headerMap = new HashMap<>();
        headerMap.put("authorization", "Bearer" + accessToken);
        ResponseEntity<?> response = kUserApi.getUserInfo(headerMap);

        log.info("카카오 사용자 정보");
        log.info(response.toString());

        String jsonString = response.getBody().toString();
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new GsonLocalDateTimeAdapter())
                .create();

        KakaoLoginResponse kaKaoLoginResponse = gson.fromJson(jsonString, KakaoLoginResponse.class);
        KakaoLoginResponse.KakaoLoginData kakaoLoginData = Optional.ofNullable(kaKaoLoginResponse.getKakao_account())
                .orElse(KakaoLoginResponse.KakaoLoginData.builder().build());

        String name = Optional.ofNullable(kakaoLoginData.getProfile())
                .orElse(KakaoLoginResponse.KakaoLoginData.KakaoProfile.builder().build())
                .getNickname();

        return SocialUserResponse.builder()
                .id(kaKaoLoginResponse.getId())
                .gender(kakaoLoginData.getGender())
                .name(name)
                .email(kakaoLoginData.getEmail())
                .build();

    }
}
