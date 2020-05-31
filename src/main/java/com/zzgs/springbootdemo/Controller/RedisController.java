package com.zzgs.springbootdemo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : LiWenBo
 * @program : springbootdemo
 * @description :
 * @date : 2020-05-28 21:09:02
 */
@Controller
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/redis")
    @ResponseBody
    public String run(){
        return "success";
    }

}
