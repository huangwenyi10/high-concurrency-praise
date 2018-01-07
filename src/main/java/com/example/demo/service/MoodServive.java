package com.example.demo.service;

import com.example.demo.dto.MoodDTO;

import java.util.List;

/**
 * 说说接口
 * Created by Ay on 2018/1/6.
 */
public interface MoodServive {

    List<MoodDTO> findAll();

    boolean praiseMood(String userId, String moodId);
}
