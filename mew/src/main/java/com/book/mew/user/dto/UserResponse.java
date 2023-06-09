package com.book.mew.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {

    private Long id;
    private String name;
    private String birthDay;
    private String phoneNumber;
    private boolean experience;

}
