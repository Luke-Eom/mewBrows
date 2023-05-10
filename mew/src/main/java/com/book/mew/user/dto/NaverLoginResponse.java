package com.book.mew.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NaverLoginResponse {
    @Builder.Default
    private Response response = Response.builder().build();
    private String resultCode;
    private String message;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Response {
        private String id;
        private String nickName;
        private String profile_image;
        private String age;
        private String gender;
        private String email;
        private String mobile;
        private String name;
        private String birthyear;
        private String birthday;

    }

}