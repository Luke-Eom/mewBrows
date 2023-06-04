package com.book.mew.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRegisterResponse {

    private String msg;

    // construct in service layer conditionally
    // e.g. 회원 이미 존재 -> 이미 존재하는 회원입니다
    // or find better responseDto

}
