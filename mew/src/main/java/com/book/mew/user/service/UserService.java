package com.book.mew.user.service;

import com.book.mew.schedule.entity.Schedule;
import com.book.mew.schedule.enums.Status;
import com.book.mew.user.dto.UserResponse;
import com.book.mew.user.enity.User;
import com.book.mew.user.exceptions.UserNotFoundException;
import com.book.mew.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepo;

    /*
    *    유저 등록
    *
    * 예약 생성시 유저 등록이 안되어있다면 db에 추가
    *
    */
    public User registerUser(String userName, String birthDay, String phoneNumber) {
        // 회원 중복 체크 로직 추후 추가 - UserResponse msg set
        String msg = "이미 등록된 회원입니다";

        if(userRepo.findByPhoneNumber(phoneNumber).isEmpty()) {
            userRepo.save(
                    User.builder()
                            .realName(userName)
                            .birthDay(birthDay)
                            .phoneNumber(phoneNumber)
                            .build());

            msg = "님의 회원 등록이 성공적으로 완료되었습니다";

        }

        User user = userRepo.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException("ERROR_404", "회원 정보를 찾을 수 없습니다"));

        return user;

    }


    // 회원 전체 조회
    public List<UserResponse> getAllUser() {
        List<User> userList = userRepo.findAll();

        return userList.stream()
                .map(User::toDto).collect(Collectors.toList());

    }

    // 이름으로 회원 조회
    public List<UserResponse> getUserByRealName(String userName) {
        List<User> userList = userRepo.findByRealName(userName);

        return userList.stream()
                .sorted(Comparator.comparing(user -> user.getSchedules().stream()
                        .map(Schedule::getScheduleTime)
                        .min(LocalDateTime::compareTo)
                        .orElse(null)))
                .map(User::toDto).collect(Collectors.toList());
    }

}
