package com.example.demo.mq;

import com.example.demo.model.Mood;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * 生产者
 * @author Ay
 * @date 2017/11/30
 */
@Component
public class MoodProducer {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, final String message) {
        jmsMessagingTemplate.convertAndSend(destination, message);
    }

    public void sendMessage(Destination destination, final Mood ayMood) {
        //mood实体需要实现Serializable序列化接口
        jmsMessagingTemplate.convertAndSend(destination, ayMood);
    }
}
