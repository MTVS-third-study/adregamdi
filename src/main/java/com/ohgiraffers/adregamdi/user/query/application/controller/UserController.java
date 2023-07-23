package com.ohgiraffers.adregamdi.user.query.application.controller;

import com.ohgiraffers.adregamdi.user.query.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

}