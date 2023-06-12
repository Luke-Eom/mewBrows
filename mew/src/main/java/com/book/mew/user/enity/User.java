package com.book.mew.user.enity;

import com.book.mew.schedule.entity.Schedule;
import com.book.mew.userFeign.enums.Role;
import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.*;
import org.springframework.data.domain.Auditable;

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
    private Long id;
    private String provide;
    private String providerId;

    private String realName;

    private String nickName;

    private String email;

    private Gender gender;

    private KakaoProfile userProfile;

    @OneToMany(mappedBy = "user")
    private List<Schedule> schedules = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Role role;



}
