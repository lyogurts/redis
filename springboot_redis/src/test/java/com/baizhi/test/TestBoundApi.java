package com.baizhi.test;

import com.baizhi.SpringbootRedisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundGeoOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootTest(classes = SpringbootRedisApplication.class)
public class TestBoundApi {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testBoundApi(){
        //redisTemplate存储的java对象，得转换成序列化。
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //绑定,对key进行绑定，后续所有操作都是基于这个key的操作
        BoundValueOperations boundValueOps = redisTemplate.boundValueOps("ages");
        boundValueOps.set("张三");
        boundValueOps.append("是一个好人");
    }
}
