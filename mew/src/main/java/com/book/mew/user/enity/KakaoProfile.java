package com.book.mew.user.enity;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class KakaoProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakao_profile_id")
    private Long id;

    private String nickName;
    private String email;
    private String birthDay;

    private Gender gender;

    private String imageUrl;

    private String provider;

    private String providerId;

    @OneToOne(mappedBy = "kakaoProfile", fetch = FetchType.LAZY)
    private User user;

}
