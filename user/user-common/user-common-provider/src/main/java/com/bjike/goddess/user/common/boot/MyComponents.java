package com.bjike.goddess.user.common.boot;

import com.alibaba.druid.pool.DruidDataSource;
import com.bjike.goddess.dbs.jpa.boot.initializer.Components;
import com.bjike.goddess.dbs.jpa.boot.initializer.EntityToScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [entity扫描类]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
@Component
public class MyComponents extends Components implements EntityToScan{

    @Bean
    public DataSource getDataSource(Environment env) {
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(env.getProperty("db.driver"));
        dds.setUrl(env.getProperty("db.url"));
        dds.setUsername(env.getProperty("db.username"));
        dds.setPassword(env.getProperty("db.password"));
        return dds;
    }

//    @Bean(name = "transactionManager")
//    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
//        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
//        return jpaTransactionManager;
//    }

    @Override
    public String[] entityScan() {
        return new String[]{"com.bjike.goddess.user.common.entity"};
    }
}
