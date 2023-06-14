package com.book.mew.user.enity;

import com.book.mew.schedule.entity.Schedule;
import com.book.mew.user.dto.UserResponse;
import com.book.mew.user.enums.Role;
import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provide;

    private String providerId;

    private String email;

    private String birthDay;

    private String nickName;

    private Gender gender;

    private String realName;

    private String phoneNumber;

    // 추후 구글, 네이버 추가시 inheritance 또는 interface로 userProfile
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "kakao_profile_id")
    private KakaoProfile kakaoProfile;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserResponse toDto() {
        return UserResponse.builder()
                .id(getId())
                .name(getRealName())
                .birthDay(getBirthDay())
                .phoneNumber(getPhoneNumber())
                .build();

    }



}
