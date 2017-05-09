package com.bjike.goddess.incomecheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.incomecheck.bo.CheckIndexBO;
import com.bjike.goddess.incomecheck.dto.CheckIndexDTO;
import com.bjike.goddess.incomecheck.to.CheckIndexTO;

import java.util.List;

/**
 * 指标设置业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-29 09:49 ]
 * @Description: [ 指标设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CheckIndexAPI {

    /**
     * 指标设置列表总条数
     */
    default Long countCheckIndex(CheckIndexDTO checkIndexDTO) throws SerException {
        return null;
    }


    /**
     * 指标设置列表id
     *
     * @return class CheckIndexBO
     */
    default CheckIndexBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 指标设置列表
     *
     * @return class CheckIndexBO
     */
    default List<CheckIndexBO> listCheckIndex(CheckIndexDTO checkIndexDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param checkIndexTO 指标设置信息
     * @return class CheckIndexBO
     */
    default CheckIndexBO addCheckIndex(CheckIndexTO checkIndexTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param checkIndexTO 指标设置信息
     * @return class CheckIndexBO
     */
    default CheckIndexBO editCheckIndex(CheckIndexTO checkIndexTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteCheckIndex(String id) throws SerException {
        return;
    }

    
}