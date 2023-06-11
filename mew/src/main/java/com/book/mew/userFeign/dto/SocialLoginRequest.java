package com.book.mew.userFeign.dto;

import com.book.mew.userFeign.enums.LoginType;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class SocialLoginRequest {

    @NotNull
    private LoginType loginType;

    @NotNull
    private String code;

}
