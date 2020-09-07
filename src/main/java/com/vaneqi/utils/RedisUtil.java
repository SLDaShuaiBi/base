package com.vaneqi.utils;

import com.vaneqi.config.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author qinlei
 * @Date 2020/8/19
 * @Desc
 */
@Component
public class RedisUtil {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 删除 key
     *
     * @param key
     * @return
     */
    public boolean del(String key) {
        boolean result = stringRedisTemplate.delete(key);
        return result;
    }

    /**
     * 保存 key:value
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 保存 key:value
     *
     * @param key
     * @param value
     * @param milliSeconds 有效毫秒数
     */
    public void set(String key, String value, long milliSeconds) {
        stringRedisTemplate.opsForValue().set(key, value, milliSeconds, TimeUnit.MILLISECONDS);
    }

    /**
     * 获取 key 对应的 value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        String result = stringRedisTemplate.opsForValue().get(key);
        return result;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param millSeconds
     */
    public void expire(String key, long millSeconds) {
        stringRedisTemplate.expire(key, millSeconds, TimeUnit.MILLISECONDS);
    }
}
