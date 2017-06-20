package com.bjike.goddess.capability.service;

import com.bjike.goddess.capability.bo.CapacityBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.capability.entity.Capacity;
import com.bjike.goddess.capability.dto.CapacityDTO;

/**
 * 个人资质业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-06-16 06:26 ]
 * @Description: [ 个人资质业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CapacitySer extends Ser<Capacity, CapacityDTO> {

    /**
     * 添加
     *
     * @param capacitys 个人资质信息
     * @param id        公司id
     * @return class CapacityBO
     */
    default CapacityBO addCapacity(String[] capacitys, String id) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param capacitys 个人资质信息
     * @param id        公司id
     * @return class CapacityBO
     */
    default CapacityBO editCapacity(String[] capacitys, String id) throws SerException {
        return null;
    }

    /**
     * 删除个人资质
     *
     * @param id id
     */
    default void deleteCapacity(String id) throws SerException {
        return;
    }

    ;

}