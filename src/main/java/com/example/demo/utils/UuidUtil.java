package com.example.demo.utils;

import java.util.UUID;

/**
 * UUID生成工具
 * Created by Ay on 2017/9/16.
 */
public class UuidUtil {
    public static String generateUUID(){

        return UUID.randomUUID().toString().replace("-", "").toString();

    }

}
