package com.book.mew.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SocialAuthResponse {

    private String access_token;

    private String token_type;

    private String refresh_token;

    private String expiry;

    private String scope;

    private String refresh_token_expiry;

}
