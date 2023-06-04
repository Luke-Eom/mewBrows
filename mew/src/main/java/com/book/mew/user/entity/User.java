package com.book.mew.user.entity;


import com.book.mew.user.dto.UserJoinRequest;
import com.book.mew.user.dto.UserRegisterRequest;
import com.book.mew.user.dto.UserResponse;
import com.book.mew.user.enums.LoginType;
import com.book.mew.user.enums.Role;
import io.micrometer.core.lang.Nullable;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String userId;

    @Column(length = 11)
    private Integer phoneNumber;

    @NonNull
    @Column(length = 50)
    private String userName;

    @Column(length = 50)
    private String userEmail;

    @Column(columnDefinition = "ENUM('GUEST', 'MEMBER', 'ADMIN') DEFAULT 'GUEST'")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(columnDefinition = "ENUM('KAKAO', 'NAVER', 'GOOGLE', 'NORMAL') DEFAULT 'NORMAL'")
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    public UserResponse toDto() {
        return UserResponse.builder()
                .id(getId())
                .userId(getUserId())
                .userName(getUserName())
                .userEmail(getUserEmail())
                .userPhoneNumber(getPhoneNumber())
                .build();

    }

}
