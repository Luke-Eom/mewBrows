package com.book.mew.userFeign.controller;

import com.book.mew.userFeign.dto.*;
import com.book.mew.userFeign.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인
    @PostMapping("/social-login")
    public ResponseEntity<LoginResponse> doSocialLogin(@RequestBody @Valid SocialLoginRequest request) {

        return ResponseEntity.ok(userService.doSocialLogin(request));

    }

    // 회원 전체 조회
    // pagination
    @GetMapping("/user-list")
    public ResponseEntity<List<UserResponse>> getAllUser(){

        return ResponseEntity.ok(
                userService.getAllUser()
        );

    }

    // 회원 id로 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {

        return ResponseEntity.ok(
                userService.getUser(id)
        );

    }

    // 회원 등록
    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody @Valid UserRegisterRequest request) {

        return ResponseEntity.created(URI.create("/register"))
                .body(userService.registerUser(request));

    }

}
