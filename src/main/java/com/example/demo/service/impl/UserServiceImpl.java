package com.example.demo.service.impl;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务类
 * Created by Ay on 2018/1/6.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserRepository userRepository;

    @Override
    public User find(String id) {
        return userRepository.findOne(id);
    }
}
