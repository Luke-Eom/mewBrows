package com.book.mew.user.dto;

import com.nimbusds.openid.connect.sdk.claims.Gender;

import java.util.Map;

public class KakaoUserInfo implements OAuth2UserInfo {

    private Map<String, Object> attributes;


    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) getKakaoAccount().get("email");
    }

    @Override
    public String getNickName() {
        return (String) getProfile().get("nickname");
    }

    @Override
    public Gender getGender() {
        String gender = (String) getKakaoAccount().get("gender");

        if (gender.equals("male")) {
            return Gender.MALE;
        } else {
            return Gender.FEMALE;
        }
    }

    @Override
    public String getImgUrl() {
        return (String)getProfile().get("profile_image_url");
    }

    public Map<String, Object> getKakaoAccount(){
        return(Map<String, Object>) attributes.get("kakao_account");
    }

    public Map<String, Object> getProfile(){
        return (Map<String, Object>) getKakaoAccount().get("profile");
    }
}
