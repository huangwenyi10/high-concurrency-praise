package com.example.demo.dto;

import com.example.demo.model.Mood;

/**
 * 说说DTO
 * Created by Ay on 2018/1/6.
 */
public class MoodDTO extends Mood{
    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户的账号
     */
    private String userAccount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
