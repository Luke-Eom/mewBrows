package com.book.mew.user.dto;

import com.book.mew.user.enums.LoginType;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SocialLoginRequest {

    @NotNull
    private LoginType loginType;

    @NotNull
    private String code;

}
