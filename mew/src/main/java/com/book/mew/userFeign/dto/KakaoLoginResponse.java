package com.book.mew.userFeign.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KakaoLoginResponse {

    private String id;

    @Builder.Default
    private KakaoLoginData kakao_account = KakaoLoginData.builder().build();

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class KakaoLoginData {

        private String gender;

        private String email;

        @Builder.Default
        private KakaoProfile profile = KakaoProfile.builder().build();

        @Builder.Default
        private KakaoProperty properties = KakaoProperty.builder().build();

        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class KakaoProfile {
            private String nickname;
        }

        @Builder
        @AllArgsConstructor
        @NoArgsConstructor
        @Data
        public static class KakaoProperty {

            private String nickname;

        }

    }

}
