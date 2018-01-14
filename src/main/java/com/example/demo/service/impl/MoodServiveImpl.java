package com.example.demo.service.impl;

import com.example.demo.dto.MoodDTO;
import com.example.demo.model.Mood;
import com.example.demo.model.User;
import com.example.demo.model.UserMoodPraiseRel;
import com.example.demo.mq.MoodProducer;
import com.example.demo.repository.MoodRepository;
import com.example.demo.service.MoodServive;
import com.example.demo.service.UserMoodPraiseRelService;
import com.example.demo.service.UserService;
import com.example.demo.utils.UuidUtil;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.jms.Destination;
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

    private static final String PRAISE_HASH_KEY = "com.example.high.conc.key";

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private MoodProducer producer;

    //队列
    private static Destination destination = new ActiveMQQueue("ay.queue.high.concurrency-praise");

    @Override
    public boolean praiseMoodForRedis(String userId, String moodId) {
        Mood mood = new Mood();
        mood.setUserId(userId);
        mood.setId(moodId);
        producer.sendMessage(destination,mood);

//        //1.存放到hashmap中
//        redisTemplate.opsForSet().add(PRAISE_HASH_KEY , moodId);
//        //2.存放到set中
//        redisTemplate.opsForSet().add(moodId,userId);
        return Boolean.TRUE;
    }

    @Override
    public List<MoodDTO> findAllForRedis() {
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
            //错误的  error
//            if(redisTemplate.opsForSet().members(mood.getId()) != null){
//                moodDTO.setPraiseNum(redisTemplate.opsForSet().size(mood.getId()).intValue());
//            }else {
//                moodDTO.setPraiseNum(mood.getPraiseNum());
//            }
            //right = 总点赞数量 ： 数据库的点赞数量 + redis的点赞数量
            moodDTO.setPraiseNum(mood.getPraiseNum() + redisTemplate.opsForSet().size(mood.getId()).intValue());

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
    public Mood find(String id) {
        return moodRepository.findOne(id);
    }

    @Override
    public void update(Mood mood) {
        moodRepository.save(mood);
    }
}
