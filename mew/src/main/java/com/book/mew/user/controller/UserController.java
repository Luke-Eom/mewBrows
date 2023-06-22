package com.book.mew.user.controller;

import com.book.mew.user.dto.UserResponse;
import com.book.mew.user.enity.User;
import com.book.mew.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController("/user")
public class UserController {

    private final UserService userService;

    public ResponseEntity<List<UserResponse>> getUserListByName(String name) {

        List<UserResponse> userList = userService.getUserByRealName(name);

        return ResponseEntity.ok(userList);

    }



}
