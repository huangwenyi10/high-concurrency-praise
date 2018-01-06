package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试类
 * Created by Ay on 2018/1/6.
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello ay";
    }
}
