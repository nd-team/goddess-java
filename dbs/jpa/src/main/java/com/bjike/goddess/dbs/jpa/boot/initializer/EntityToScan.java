package com.bjike.goddess.dbs.jpa.boot.initializer;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [所有依赖jpa　Entity（实体类）扫描接口]
 * @Version: [1.0.0]
 * @Copy: [org.ndshop]
 */
public interface EntityToScan {
    /**
     *
     */
    /**
     * 扫描　返回值包　Entity
     *
     * @return　扫描包
     */
    String[] entityScan();
}
