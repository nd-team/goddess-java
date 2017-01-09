package com.bjike.goddess.user.register.boot;

import com.alibaba.druid.pool.DruidDataSource;
import com.bjike.goddess.dbs.jpa.boot.initializer.Components;
import com.bjike.goddess.dbs.jpa.boot.initializer.EntityToScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: 扫描依赖jpa entity]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public class MyComponents extends Components implements EntityToScan {
    @Bean
    public DataSource getDataSource(Environment env) {
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(env.getProperty("db.driver"));
        dds.setUrl(env.getProperty("db.url"));
        dds.setUsername(env.getProperty("db.username"));
        dds.setPassword(env.getProperty("db.password"));
        return dds;
    }
    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.user.common.entity"};
    }
}
