package com.swz.utils;

import java.util.UUID;

/**
 * @Package: com.swz.utils
 * @Description: 生成随机字符串的工具类 uuid
 * @author: swz
 * @date: 2019/4/11 15:44
 */
public class UuidUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(UuidUtil.getUUID());
    }

}
