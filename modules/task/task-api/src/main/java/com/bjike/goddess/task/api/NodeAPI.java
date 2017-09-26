package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.FieldBO;
import com.bjike.goddess.task.entity.Field;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-19 11:10]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface NodeAPI {
    default List<String> node(String tableId) throws SerException {
        return null;
    }

    default List<FieldBO> detail(String tableId,String node) throws SerException {
        return null;
    }
}
