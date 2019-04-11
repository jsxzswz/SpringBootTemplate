package com.swz.redis.key;

/**
 * @Package: com.swz.redis.key
 * @Description: 缓冲key前缀
 * @author: swz
 * @date: 2019/4/10 14:54
 */
public interface IKeyPrefix {

    /**
     * 前缀
     *
     * @return
     */
    String getPrefix();
}
