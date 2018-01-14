package com.example.demo.mq;

import com.example.demo.model.Mood;
import com.example.demo.service.MoodServive;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 * 消费者
 * @author Ay
 * @date   2017/11/30
 */
@Component
public class MoodConsumer {

    private static final String PRAISE_HASH_KEY = "com.example.high.conc.key";

    @Resource
    private RedisTemplate redisTemplate;

    @JmsListener(destination = "ay.queue.high.concurrency-praise")
    public void receiveQueue(Mood mood){
        //1.存放到hashmap中
        redisTemplate.opsForSet().add(PRAISE_HASH_KEY , mood.getId());
        //2.存放到set中
        redisTemplate.opsForSet().add(mood.getId(),mood.getUserId());
    }
}
