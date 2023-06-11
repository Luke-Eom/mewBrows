package com.book.mew.userFeign.dto;

import com.book.mew.userFeign.enums.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserJoinRequest {

    private String userId;

    private String userName;

    private LoginType loginType;

    private String userEmail;

    private Integer phoneNumber;

}
