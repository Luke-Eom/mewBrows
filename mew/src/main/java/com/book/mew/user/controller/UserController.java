package com.book.mew.user.controller;

import com.book.mew.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/user")
public class UserController {

    private final UserService userService;



}
