package com.zzgs.springbootdemo.Service.Impl;

import com.zzgs.springbootdemo.Bean.Jurisdiction;
import com.zzgs.springbootdemo.Mapper.JurisdictionDao;
import com.zzgs.springbootdemo.Service.JurisdictionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   Tang
 * Date:     2019/12/31 13:42
 * Description:
 */
@Service
@Slf4j
public class JurisdictionServiceImpl implements JurisdictionService {

    @Autowired
    JurisdictionDao jurisdictionDao;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询所有资源的访问权限设置
     *
     * @return 资源列表
     */
    @Override
    public List<Jurisdiction> findAll() {
        String key = "JurisdictionList";
        ValueOperations<String,List<Jurisdiction>> operations = redisTemplate.opsForValue();
        List<Jurisdiction> jurisdictionList;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            jurisdictionList = operations.get(key);
            log.info("==========从缓存中获得数据=========");
            return jurisdictionList;
        } else {
            log.info("==========从数据表中获得数据=========");
            // 写入缓存
            jurisdictionList = jurisdictionDao.findAll();
            operations.set(key, jurisdictionList);
            return jurisdictionList;
        }
    }
}
