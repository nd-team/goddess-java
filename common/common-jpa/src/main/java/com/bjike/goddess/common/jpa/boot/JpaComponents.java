package com.bjike.goddess.common.jpa.boot;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [配置项扫描类]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class JpaComponents {
    @Autowired
    private JpaCache jpaCache;
    @Autowired
    private EntityToScan packagesToScan;

    @Bean
    public DataSource getDataSource(Environment env) {
        DruidDataSource dds = new DruidDataSource();
        dds.setDriverClassName(env.getProperty("db.driver"));
        dds.setUrl(env.getProperty("db.url"));
        dds.setUsername(env.getProperty("db.username"));
        dds.setPassword(env.getProperty("db.password"));
        return dds;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    /**
     * 实体类管理工厂
     *
     * @param localContainerEntityManagerFactoryBean
     * @return
     */
    @Bean("entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return localContainerEntityManagerFactoryBean.getNativeEntityManagerFactory();
    }

    @Bean("jpaVendorAdapter")
    public JpaVendorAdapter hibernateJpaVendorAdapter(){
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(false);
        hibernateJpaVendorAdapter.setGenerateDdl(true);
        return hibernateJpaVendorAdapter;
    }

    /**
     * 实体管理器
     *
     * @return
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean getLCEMF(DataSource dataSource,JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setDataSource(dataSource);
        lcemf.setJpaVendorAdapter(jpaVendorAdapter);
        String[] packages = ArrayUtils.add(packagesToScan.entityScan(), Constant.SCAN_APP_PACKAGES);
        lcemf.setPackagesToScan(packages);
        Properties properties = new Properties();
        properties.setProperty("hibernate.cache.region.factory_class","org.hibernate.cache.ehcache.EhCacheRegionFactory");
        lcemf.setJpaProperties(properties);
        return lcemf;
    }



    /**
     * 加载缓存配置
     *
     * @return
     */
    @Bean("cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager cacheManager = new SimpleCacheManager();
        List<Cache> caches = new ArrayList<>();
        for (String cache_name : Constant.CACHE_NAME) {
            Cache cache =new ConcurrentMapCache(cache_name);
            cache.put("timeToLiveSeconds",60*60);//1小时过期
            cache.put("timeToIdleSeconds",60*60*12);//闲置时间
            caches.add(cache);
        }
        caches.addAll(jpaCache.initCaches());
        cacheManager.setCaches(caches);
        return cacheManager;
    }


}
