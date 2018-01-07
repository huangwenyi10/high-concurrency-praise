package com.example.demo.service.impl;

import com.example.demo.model.UserMoodPraiseRel;
import com.example.demo.repository.UserMoodPraiseRelRepository;
import com.example.demo.service.UserMoodPraiseRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户说说点赞关联服务类
 * Created by Ay on 2018/1/6.
 */
@Service
public class UserMoodPraiseRelServiceImpl implements UserMoodPraiseRelService{

    @Resource
    private UserMoodPraiseRelRepository userMoodPraiseRelRepository;

    @Override
    public UserMoodPraiseRel save(UserMoodPraiseRel userMoodPraiseRel) {
        return userMoodPraiseRelRepository.save(userMoodPraiseRel);
    }
}
