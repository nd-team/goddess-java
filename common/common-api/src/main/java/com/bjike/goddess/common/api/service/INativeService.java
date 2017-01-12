package com.bjike.goddess.common.api.service;


import com.bjike.goddess.common.api.exception.SerException;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2016-12-27 15:47]
 * @Description: [原生sql查询接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface INativeService<BE> {

    /**
     * 适用比较复杂并需要原生sql的查询
     * @param sql  查询sql
     * @param clazz 返回对象类型
     * @param fields 查询字段（该字段顺序必须与sql查询字段顺序一致）
     * @return Object（该实体的类型必须与数据库类型一致）
     */

    default List<BE> findBySql(String sql, Class clazz, String[] fields) throws SerException {
        return null;
    }
}
