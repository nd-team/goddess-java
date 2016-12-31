package com.bjike.goddess.dbs.jpa.boot.initializer;


import org.springframework.cache.Cache;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [缓存接口，配置自己模块的缓存]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface JpaCache {
    default List<Cache> initCaches() {
        return new ArrayList<>(0);
    }

}
