package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.dto.FieldDTO;
import com.bjike.goddess.task.entity.Field;
import com.bjike.goddess.task.to.FieldTO;

import java.util.List;

/**
 * 列业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-16 08:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface FieldSer extends Ser<Field, FieldDTO> {
    /**
     * 列添加
     *
     * @param to
     * @throws SerException
     */
    default void add(FieldTO to) throws SerException {

    }


    /**
     * 某个表的表列
     *
     * @param tableId
     * @param node
     * @throws SerException
     */
    default List<Field> list(String tableId,String node) throws SerException {
        return null;
    }
    /**
     * 最大序列
     *
     * @param tableId
     * @param node
     * @throws SerException
     */
    default Integer getSeq(String tableId, String node) throws SerException {
        return null;
    }
}
