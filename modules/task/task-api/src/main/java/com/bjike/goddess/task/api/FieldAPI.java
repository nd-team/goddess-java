package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.dto.FieldDTO;
import com.bjike.goddess.task.to.FieldTO;
import com.bjike.goddess.task.vo.FieldVO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-16 08:50]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface FieldAPI {
    /**
     * 列添加
     *
     * @param to
     * @throws SerException
     */
    default void add(FieldTO to) throws SerException {

    }
    /**
     * 列删除
     *
     * @param id
     * @throws SerException
     */
    default void delete(String id) throws SerException {

    }


    /**
     * 列列表
     *
     * @param tableId
     * @param node
     * @throws SerException
     */
    default List<FieldVO> list(String tableId,String node ) throws SerException {
        return null;
    }
    
}
