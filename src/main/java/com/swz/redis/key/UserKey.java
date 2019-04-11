package com.swz.redis.key;

/**
 * @Package: com.swz.redis.key
 * @Description: 缓存用户预设key
 * @author: swz
 * @date: 2019/4/10 14:57
 */
public class UserKey extends BasePrefix {

    /**
     * 防止被外面实例化
     */
    private UserKey(String prefix) {
        super(prefix);
    }

    /**
     * 需要缓存的字段
     */
    public static UserKey userId = new UserKey("userId_");

}
