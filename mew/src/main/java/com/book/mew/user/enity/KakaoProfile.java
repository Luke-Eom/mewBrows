package com.book.mew.user.enity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class KakaoProfile {

    private Long id;

    private String nickName;

    private String phoneNumber;

    @OneToOne(mappedBy = "kakaoProfile", fetch = FetchType.LAZY)
    private User user;

    private Integer age;

    private String provider;

    private String providerId;

    private String imageUrl;


}
