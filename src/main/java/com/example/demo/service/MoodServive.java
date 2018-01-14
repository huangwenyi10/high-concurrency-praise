package com.example.demo.service;

import com.example.demo.dto.MoodDTO;
import com.example.demo.model.Mood;

import java.util.List;

/**
 * 说说接口
 * Created by Ay on 2018/1/6.
 */
public interface MoodServive {
    //传统查询
    List<MoodDTO> findAll();
    //新的
    List<MoodDTO> findAllForRedis();
    //传统查询
    boolean praiseMood(String userId, String moodId);
    //新的
    boolean praiseMoodForRedis(String userId, String moodId);

    Mood find(String id);

    void update(Mood mood);
}
