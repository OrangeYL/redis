package com.orange.redis.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis工具类，封装redisTemplate
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    public RedisUtil(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * String类型，根据key获取value
     * @param key
     * @return
     */
    public Object get(Object key){
        if(key==null){
            return null;
        }else{
            return redisTemplate.opsForValue().get(key);
        }
    }

    /**
     * String类型，存入
     * @param key
     * @param value
     * @return
     */
    public Boolean set(Object key,Object value){
        try {
            redisTemplate.opsForValue().set(key,value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
           return false;
        }
    }

    /**
     * List类型，获取全部内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(Object key, int start, int end){
        try {
            return redisTemplate.opsForList().range(key,start,end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * List类型，存入
     * @param key 键
     * @param value 值
     * @return
     */
    public Boolean lSet(Object key,List<Object> value){
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
