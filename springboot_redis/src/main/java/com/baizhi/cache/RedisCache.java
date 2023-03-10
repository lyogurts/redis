package com.baizhi.cache;

import com.baizhi.util.ApplicationContextUtils;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisCache implements Cache {
    //当前放入缓存的mapper的namespace
    private  final  String  id;
    //必须存在构造方法
    public RedisCache(String id) {
        System.out.println("id ==================> " + id);//id ==================> com.baizhi.dao.UserDao
        this.id = id;
    }
    //返回cache唯一标识
    @Override
    public String getId() {
        return this.id;
    }
    //缓存放入值
    //使用RedisTemplate
    @Override
    public void putObject(Object o, Object o1) {
        System.out.println("key = " + o);
        System.out.println("value= " + o1);
        //通多application工具获取redisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        //序列化key
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //使用redishash类型作为缓存存储类型 key作为namespace hashkey 作为唯一识别id value是缓存内容
        //                               hashname       key        value
        redisTemplate.opsForHash().put(id.toString(),o.toString(),o1);
    }
    //获取中获取数据
    @Override
    public Object getObject(Object o) {
        System.out.println("key = " + o);
        //通过application工具类获取redisTemplate
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //    根据hashname key获取               hashname          key
      return   redisTemplate.opsForHash().get(id.toString(),o.toString());

    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
