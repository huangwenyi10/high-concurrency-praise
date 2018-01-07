package com.example.demo.service.impl;

import com.example.demo.dto.MoodDTO;
import com.example.demo.model.Mood;
import com.example.demo.model.User;
import com.example.demo.model.UserMoodPraiseRel;
import com.example.demo.repository.MoodRepository;
import com.example.demo.service.MoodServive;
import com.example.demo.service.UserMoodPraiseRelService;
import com.example.demo.service.UserService;
import com.example.demo.utils.UuidUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 说说服务类
 * Created by Ay on 2018/1/6.
 */
@Service
public class MoodServiveImpl implements MoodServive{
    @Resource
    private MoodRepository moodRepository;
    @Resource
    private UserService userService;
    @Resource
    private UserMoodPraiseRelService userMoodPraiseRelService;

    @Override
    public List<MoodDTO> findAll() {
        List<Mood> moodList =  moodRepository.findAll();
        if(CollectionUtils.isEmpty(moodList)){
            //return new ArrayList<>();
            return Collections.EMPTY_LIST;
        }
        List<MoodDTO> moodDTOList = new ArrayList<>();
        for(Mood mood: moodList){
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setUserId(mood.getUserId());
            moodDTO.setPraiseNum(mood.getPraiseNum());
            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setContent(mood.getContent());
            //通过userID查询用户
            User user =  userService.find(mood.getUserId());
            //用户名
            moodDTO.setUserName(user.getName());
            //账户
            moodDTO.setUserAccount(user.getAccount());
            moodDTOList.add(moodDTO);
        }
        return moodDTOList;
    }

    @Override
    public boolean praiseMood(String userId, String moodId) {
        //插入到用户说说关联表中
        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
        userMoodPraiseRel.setId(UuidUtil.generateUUID());
        userMoodPraiseRel.setUserId(userId);
        userMoodPraiseRel.setMoodId(moodId);
        userMoodPraiseRelService.save(userMoodPraiseRel);

        Mood mood = moodRepository.findOne(moodId);
        mood.setPraiseNum(mood.getPraiseNum() + 1);
        moodRepository.saveAndFlush(mood);
        return Boolean.TRUE;
    }
}
