package com.book.mew.user.service;

import com.book.mew.user.dto.KakaoUserInfo;
import com.book.mew.user.dto.LoginResponse;
import com.book.mew.user.dto.OAuth2UserInfo;
import com.book.mew.user.dto.OAuthTokenResponse;
import com.book.mew.user.enity.User;
import com.book.mew.user.repository.UserRepository;
import com.book.mew.user.security.JwtTokenProvider;
import com.book.mew.user.security.TokenProvider;
import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OAuthService {

    private static final String BEARER_TYPE = "Bearer ";

    private final InMemoryClientRegistrationRepository inMemoryRepo;
    private final UserRepository userRepo;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginResponse login(String providerName, String code) {

        ClientRegistration provider = inMemoryRepo.findByRegistrationId(providerName);
        OAuthTokenResponse tokenResponse = getToken(code, provider);
        User user = getUserprofile(providerName, tokenResponse, provider);

        String accessToken = jwtTokenProvider.createAccessToken(String.valueOf(user.getId()));
        String refreshToken = jwtTokenProvider.createRefreshToken();

        return LoginResponse.builder()
                .id(user.getId())
                .name(user.getUserProfile().getNickName())
                .email(user.getEmail())
                .imageUrl(user.getUserProfile().getImageUrl())
                .role(user.getRole())
                .tokenType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    private OAuthTokenResponse getToken(String code, ClientRegistration provider) {
        return WebClient.create()
                .post()
                .uri(provider.getProviderDetails().getTokenUri())
                .headers(header -> {
                    header.setContentType(MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED_VALUE));
                    header.setAcceptCharset(Collections.singletonList(StandardCharsets.UTF_8));

                })
                .bodyValue(tokenRequest(code, provider))
                .retrieve()
                .bodyToMono(OAuthTokenResponse.class)
                .block();

    }

    private MultiValueMap<String, String> tokenRequest(String code, ClientRegistration provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUri());
        formData.add("client_secret", provider.getClientSecret());
        formData.add("client_id", provider.getClientId());

        return formData;

    }

    private User getUserprofile(String providerName, OAuthTokenResponse tokenResponse, ClientRegistration provider) {
        Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponse);
        OAuth2UserInfo oauth2UserInfo = null;

        if(providerName.equals("kakao")) {
            oauth2UserInfo = new KakaoUserInfo(userAttributes);
        } else {
            log.info("잘못된 접근입니다");
        }

        String provide = oauth2UserInfo.getProvider();
        String providerId = oauth2UserInfo.getProviderId();
        String nickName = oauth2UserInfo.getNickName();
        String email = oauth2UserInfo.getEmail();
        Gender gender = oauth2UserInfo.getGender();
        String imgUrl = oauth2UserInfo.getImgUrl();

        User user = userRepo.findByEmail(email);

        if(user == null) {
            user = userRepo.save(User.builder()
                            .email(email)
                            .nickName(nickName)
                            .gender(gender)
                            .provide(provide)
                            .providerId(providerId)
                    .build());

        }

        return user;

    }

    private Map<String, Object> getUserAttributes(ClientRegistration provider, OAuthTokenResponse tokenResponse) {
        return WebClient.create()
                .get()
                .uri(provider.getProviderDetails().getUserInfoEndpoint().getUri())
                .headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

    }

}
