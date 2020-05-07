package com.atguigu.jedis.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: weifeng.sun
 * @time: 2020/5/5 16:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootTest_jedis {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void test01(){
        Set keys = redisTemplate.keys("*");
        System.out.println(keys.size());

        ValueOperations valueOperations = redisTemplate.opsForValue();
        System.out.println(valueOperations.get("first"));
        System.out.println(valueOperations.get("boot1111"));

//        valueOperations.set("boot1111","spring");
    }

    @Test
    public void test02(){
        ListOperations<String, String> listOps = stringRedisTemplate.opsForList();
        listOps.leftPushAll("day02_list","AAA","BBB","CCC","DDD");
        listOps.rightPushIfPresent("day02_list","111");
        listOps.rightPushIfPresent("day02_list","222");
        listOps.rightPushIfPresent("day02_list","333");
        listOps.rightPushIfPresent("day02_list","444");



        List<String> day02_list = listOps.range("day02_list", 0, -1);
        day02_list.forEach(
                e -> System.out.println(e)
        );
    }
}
