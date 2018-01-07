package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by Ay on 2018/1/6.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


}
