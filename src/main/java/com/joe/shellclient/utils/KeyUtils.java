package com.joe.shellclient.utils;

import java.util.Random;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:20 2018/4/28
 */
public class KeyUtils {
    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
