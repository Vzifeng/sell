package com.example.sell.utils;

import java.util.UUID;

/**
 * 获取UUID
 */
public class UUIDUtil {
    public static synchronized String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
