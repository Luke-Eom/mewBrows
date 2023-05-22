package com.book.mew.user.entity;


import com.book.mew.user.enums.LoginType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User extends CommonEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String userId;
    @Column(length = 50)
    private String userName;
    @Column(length = 50)
    private String userEmail;
    @Column(columnDefinition = "ENUM('KAKAO', 'NAVER', 'GOOGLE', 'NORMAL') DEFAULT 'NORMAL'")
    @Enumerated(EnumType.STRING)
    private LoginType loginType;

}
