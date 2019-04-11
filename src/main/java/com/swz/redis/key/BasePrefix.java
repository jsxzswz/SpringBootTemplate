package com.swz.redis.key;

/**
 * @Package: com.swz.redis.key
 * @Description: key值预设
 * @author: swz
 * @date: 2019/4/10 14:54
 */
public class BasePrefix implements IKeyPrefix {

    private String prefix;

    public BasePrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();//拿到参数类类名
        return className + ":" + prefix;
    }
}
