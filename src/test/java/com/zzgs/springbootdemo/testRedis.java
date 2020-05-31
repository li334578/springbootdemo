package com.zzgs.springbootdemo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-28 20:25:43
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootdemoApplication.class)
public class testRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void run1(){
        redisTemplate.opsForValue().set("java","我爱java");
        String str =(String) redisTemplate.opsForValue().get("java");
        System.out.println(str);
    }
}
