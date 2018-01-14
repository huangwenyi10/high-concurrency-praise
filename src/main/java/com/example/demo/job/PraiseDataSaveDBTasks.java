package com.example.demo.job;

import com.example.demo.model.Mood;
import com.example.demo.model.UserMoodPraiseRel;
import com.example.demo.service.MoodServive;
import com.example.demo.service.UserMoodPraiseRelService;
import com.example.demo.utils.UuidUtil;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Component
@Configurable
@EnableScheduling
public class PraiseDataSaveDBTasks {

    //每5秒执行一次
    @Scheduled(cron = "*/60 * *  * * * ")
    public void savePraiseDataToDB(){
        System.out.println("run .....");
    }

    @Resource
    private RedisTemplate redisTemplate;
    private static final String PRAISE_HASH_KEY = "com.example.high.conc.key";
    @Resource
    private UserMoodPraiseRelService userMoodPraiseRelService;
    @Resource
    private MoodServive moodServive;
    //每10秒执行一次，真实项目当中，我们可以把定时器的执行计划时间设置长一点
    //比如说每天晚上凌晨2点跑一次。
    @Scheduled(cron = "*/10 * *  * * * ")
    public void savePraiseDataToDB2(){
        //获取所有被点赞的说说id
        Set<String> moods = redisTemplate.opsForSet().members(PRAISE_HASH_KEY);
        if(CollectionUtils.isEmpty(moods)){
            return;
        }
        for(String moodId: moods){
            if(redisTemplate.opsForSet().members(moodId) == null){
                continue;
            }else {
                //通过说说id获取所有点赞的用户id列表
                Set<String> userIds = redisTemplate.opsForSet().members(moodId);
                if(CollectionUtils.isEmpty(userIds)){
                    continue;
                }else{
                    for(String userId:userIds){
                        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
                        userMoodPraiseRel.setId(UuidUtil.generateUUID());
                        userMoodPraiseRel.setMoodId(moodId);
                        userMoodPraiseRel.setUserId(userId);
                        //保存说说与用户关联关系
                        userMoodPraiseRelService.save(userMoodPraiseRel);
                    }
                    Mood mood = moodServive.find(moodId);
                    //更新说说点赞数量
                    //mood.setPraiseNum(redisTemplate.opsForSet().size(moodId).intValue());
                    //说说的总点赞数量 = redis 点赞数量 + 数据库的点赞数量
                    mood.setPraiseNum(mood.getPraiseNum() + redisTemplate.opsForSet().size(moodId).intValue());
                    moodServive.update(mood);
                    //清除缓存数据
                    redisTemplate.delete(moodId);
                }
            }
        }
        //清除缓存数据
        redisTemplate.delete(PRAISE_HASH_KEY);

    }
}