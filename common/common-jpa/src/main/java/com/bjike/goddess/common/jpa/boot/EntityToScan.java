package com.bjike.goddess.common.jpa.boot;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [所有依赖jpa　Entity（实体类）扫描接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface EntityToScan<T> {

    /**
     * 扫描　返回值包　Entity
     *
     * @return　扫描包
     */
    default String[] entityScan(){
    	return null;
    }
}
