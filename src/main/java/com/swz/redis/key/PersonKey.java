package com.swz.redis.key;

/**
 * @Package: com.swz.redis.key
 * @Description: 缓存用户预设key
 * @author: swz
 * @date: 2019/4/10 14:57
 */
public class PersonKey extends BasePrefix {

    /**
     * 防止被外面实例化
     */
    private PersonKey(String prefix) {
        super(prefix);
    }

    /**
     * 需要缓存的字段
     */
    public static PersonKey personId = new PersonKey("personId_");

    /**
     * 需要缓存的字段
     */
    public static PersonKey personList = new PersonKey("personList");

}
