package com.book.mew.user.dto;

import com.book.mew.userFeign.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginResponse {

    private Long id;
    private String name;
    private String email;
    private String imageUrl;
    private Role role;
    private String tokenType;
    private String accessToken;
    private String refreshToken;

}