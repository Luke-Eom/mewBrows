package com.book.mew.user.service;

import com.book.mew.user.dto.*;
import com.book.mew.user.entity.User;
import com.book.mew.user.enums.LoginType;
import com.book.mew.user.exceptions.UserNotFoundException;
import com.book.mew.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        // 소셜 로그인 findByUserId -> findByUserEmail for non-login User (Entity)
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
                .orElseThrow(() -> new UserNotFoundException("ERROR_404", "회원 정보를 찾을 수 없습니다"));

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
                        .userName(userJoinRequest.getUserName())
                        .loginType(userJoinRequest.getLoginType())
                        .userEmail(userJoinRequest.getUserEmail())
                        .phoneNumber(userJoinRequest.getPhoneNumber())
                        .build()
        );

        return UserJoinResponse.builder()
                .id(user.getId())
                .build();

    }

    //회원 등록 by admin(admin일때만 가능하게)
    // (회원 가입 part에서 phoneNumber 또는 unique한 value 중복있을 시,
    // 로그인 타입이 default이면 로그인 타입을 바꿔주고 추가 데이터 업데이트) - code update req
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        // 회원 중복 체크 로직 추후 추가 - UserResponse msg set
        String msg = "이미 등록된 회원입니다";

        if(userRepo.findByPhoneNumber(userRegisterRequest.getPhoneNumber()).isEmpty()) {
            userRepo.save(
                    User.builder()
                            .userName(userRegisterRequest.getUserName())
                            .birthDate(userRegisterRequest.getBirthDate())
                            .phoneNumber(userRegisterRequest.getPhoneNumber())
                            .build());

            msg = "님의 회원 등록이 성공적으로 완료되었습니다";

        }

        User user = userRepo.findByPhoneNumber(userRegisterRequest.getPhoneNumber())
                .orElseThrow(() -> new UserNotFoundException("ERROR_404", "회원 정보를 찾을 수 없습니다"));

        return UserRegisterResponse.builder()
                .msg(user.getUserName()+msg)
                .build();

    }


    // 회원 전체 조회
    public List<UserResponse> getAllUser() {
        List<User> userList = userRepo.findAll();

        return userList.stream()
                .map(User::toDto).collect(Collectors.toList());

    }

    // id로 회원 조회
    public UserResponse getUser(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow(() -> new UserNotFoundException("ERROR_404", "회원 정보를 찾을 수 없습니다."));

        return UserResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .build();
    }

}
