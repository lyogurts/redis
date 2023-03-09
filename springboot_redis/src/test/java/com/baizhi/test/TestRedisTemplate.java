package com.baizhi.test;

import com.baizhi.SpringbootRedisApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = SpringbootRedisApplication.class)
public class TestRedisTemplate {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //操作redis字符串
    @Test
    public void testString(){
        stringRedisTemplate.opsForValue().set("name","lyogurts"); //设置一个key value
        String name = stringRedisTemplate.opsForValue().get("name");//获取一个key对应的value
        System.out.println(name);
        stringRedisTemplate.opsForValue().set("code","1234",200, TimeUnit.SECONDS);//设置一个key value，并设置超时时间
        stringRedisTemplate.opsForValue().append("name","是一个软件工程学生");
    }


    //操作redis key 相关的
    @Test
    public void testKey(){
//        stringRedisTemplate.delete("name");

        Boolean name = stringRedisTemplate.hasKey("name"); //判断某个key是否存在 true
        System.out.println(name);

        DataType name1 = stringRedisTemplate.type("name");//判断某个key所对应值的类型 STRING
        System.out.println(name1);

        Set<String> keys = stringRedisTemplate.keys("*");//查看所有key对应的值 key=name
        keys.forEach(key -> System.out.println("key="+key));

        Long expire = stringRedisTemplate.getExpire("name"); //获取超时时间 -1 永不超时， -2不存在
        System.out.println(expire); //-1
        stringRedisTemplate.randomKey(); //随机获取key
    }

    //操作redis中的list类型
    @Test
    public void testList(){
        stringRedisTemplate.opsForList().leftPush("ages","23"); //创建一个列表，放入元素
        stringRedisTemplate.opsForList().leftPushAll("days","one","two","three","four");//创建一个列表，放入多个value
        ArrayList<String> names = new ArrayList<>();
        names.add("zangsan");
        names.add("zangsan2");
        names.add("zangsan3");
        stringRedisTemplate.opsForList().leftPushAll("names",names);//创建一个列表，放入集合
        List<String> list = stringRedisTemplate.opsForList().range("names", 0, -1);//获取范围内的元素
        list.forEach(value-> System.out.println("value="+value));
        stringRedisTemplate.opsForList().index("names",2);
    }

    //操作redis中的set
    @Test
    public void testSet(){
        stringRedisTemplate.opsForSet().add("sets","喻百强","zhangsan","lisi"); //创建set并放入多个元素
        Set<String> sets = stringRedisTemplate.opsForSet().members("sets");//set无序，members查看
        sets.forEach(value-> System.out.println("sets="+value));
        Long sets1 = stringRedisTemplate.opsForSet().size("sets");//获取集合长度
        System.out.println(sets1);
    }

    //操作redis中的zset
    @Test
    public void testZset(){
        stringRedisTemplate.opsForZSet().add("zsets","ZHANGSAN",112);
        stringRedisTemplate.opsForZSet().add("zsets","ZHANGSAN2",122);
        Set<String> zsets = stringRedisTemplate.opsForZSet().range("zsets", 0, -1);
        zsets.forEach(value-> System.out.println("zset="+value));
        Set<ZSetOperations.TypedTuple<String>> zsets1 = stringRedisTemplate.opsForZSet().rangeByScoreWithScores("zsets", 0, 1000);
        zsets1.forEach(value-> {
                    System.out.println("zsets=" + value.getValue());
                    System.out.println("zsets=" + value.getScore());
                                }
                );
    }

    //操作redis中的hash
    @Test
    public void testHash(){
        stringRedisTemplate.opsForHash().put("maps","names","liming");//创建一个hash,存入key value
        stringRedisTemplate.opsForHash().get("maps","names");//获取某个key的值
        stringRedisTemplate.opsForHash().values("maps"); //获取所有value
        stringRedisTemplate.opsForHash().keys("maps");//获取所有key
    }
}
