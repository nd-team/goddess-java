package com.bjike.goddess.user.register.boot;


import com.bjike.goddess.dbs.jpa.boot.initializer.JpaCache;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 加入自己所属模块缓存]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class MyJpaCache implements JpaCache {
    @Override
    public List<Cache> initCaches() {
        ConcurrentMapCache serCache = new ConcurrentMapCache("userSerCache");
        ConcurrentMapCache daoCache = new ConcurrentMapCache("userDaoCache");
        return Arrays.asList(serCache, daoCache);
    }
}
