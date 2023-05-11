package com.book.mew.user.service;

import com.book.mew.user.dto.*;
import com.book.mew.user.entity.User;
import com.book.mew.user.enums.LoginType;
import com.book.mew.user.exceptions.NotFoundException;
import com.book.mew.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final List<SocialLoginService> loginServices;
    private final UserRepository userRepo;

    // 로그인 실행 로직
    public LoginResponse doSocialLogin(SocialLoginRequest request) {
        SocialLoginService loginService = this.getLoginService(request.getLoginType());
        SocialAuthResponse socialAuthResponse = loginService.getAccessToken(request.getCode());
        SocialUserResponse socialUserResponse = loginService.getUserInfo(socialAuthResponse.getAccess_token());

        log.info("socialUserResponse {}", socialUserResponse.toString());

        if(userRepo.findByUserId(socialUserResponse.getId()).isEmpty()) {
            this.joinUser(
                    UserJoinRequest.builder()
                            .userId(socialUserResponse.getId())
                            .userEmail(socialUserResponse.getEmail())
                            .userName(socialUserResponse.getName())
                            .loginType(request.getLoginType())
                            .build()
            );
        }

        User user = userRepo.findByUserId(socialUserResponse.getId())
                .orElseThrow(() -> new NotFoundException("ERROR_404", "회원 정보를 찾을 수 없습니다"));

        return LoginResponse.builder()
                .id(user.getId())
                .build();
    }

    // 로그인 서비스 타입 반환
    private SocialLoginService getLoginService(LoginType loginType) {
        for(SocialLoginService loginService : loginServices) {
            if(loginType.equals(loginService.getServiceName())) {
                log.info("로그인 서비스명: {}", loginService.getServiceName());
                return loginService;
            }
        }
        return new LoginServiceImpl();
    }

    // 회원 가입
    private UserJoinResponse joinUser(UserJoinRequest userJoinRequest) {
        User user = userRepo.save(
                User.builder()
                        .userId(userJoinRequest.getUserId())
                        .loginType(userJoinRequest.getLoginType())
                        .userEmail(userJoinRequest.getUserEmail())
                        .userName(userJoinRequest.getUserName())
                        .build()
        );

        return UserJoinResponse.builder()
                .id(user.getId())
                .build();
    }

    // id로 회원 조회
    public UserResponse getUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("ERROR_404", "회원 정보를 찾을 수 없습니다."));

        return UserResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .build();
    }

}
