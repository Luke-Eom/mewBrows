package com.book.mew.user.dto;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

public interface OAuth2UserInfo {
    String getProvider();
    String getProviderId();
    String getNickName();
    String getEmail();
    Gender getGender();
    String getImgUrl();
}