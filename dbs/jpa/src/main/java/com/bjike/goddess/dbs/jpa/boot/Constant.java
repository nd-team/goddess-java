package com.bjike.goddess.dbs.jpa.boot;


import com.bjike.goddess.dbs.jpa.boot.initializer.AppConfig;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [常量配置类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class Constant extends AppConfig {
    public static final String SCAN_APP_PACKAGES = "org.ndshop.dbs.jpa.entity";
    public static final String CACHE_NAME[] = {"daoCache", "serCache"};

}