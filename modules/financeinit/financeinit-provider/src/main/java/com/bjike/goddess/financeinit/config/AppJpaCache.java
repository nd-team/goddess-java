package com.bjike.goddess.financeinit.config;


import com.bjike.goddess.common.jpa.boot.JpaCache;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


/**
 * 自定义缓存添加
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class AppJpaCache implements JpaCache {

    @Override
    public List<Cache> initCaches() {
        ConcurrentMapCache serCache = new ConcurrentMapCache("financeinitSerCache");
        serCache.put("timeToLiveSeconds", 60 * 60);//1小时过期
        serCache.put("timeToIdleSeconds", 60 * 60 * 12);//闲置时间

        ConcurrentMapCache daoCache = new ConcurrentMapCache("financeinitDaoCache");
        daoCache.put("timeToLiveSeconds", 60 * 60);//1小时过期
        daoCache.put("timeToIdleSeconds", 60 * 60 * 12);//闲置时间

        return Arrays.asList(serCache, daoCache);
    }
}
