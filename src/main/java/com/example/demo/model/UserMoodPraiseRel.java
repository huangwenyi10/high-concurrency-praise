package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 描述：说说点赞关联表
 *
 * @author ay
 * @date 2017/9/16
 */
@Entity
public class UserMoodPraiseRel {
    /**
     * 主键
     */
    @Id
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 说说id
     */
    private String moodId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMoodId() {
        return moodId;
    }

    public void setMoodId(String moodId) {
        this.moodId = moodId;
    }
}
