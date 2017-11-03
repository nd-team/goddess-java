package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerWeightFirstFactorBO;
import com.bjike.goddess.customer.dto.CustomerWeightFirstFactorDTO;
import com.bjike.goddess.customer.to.CustomerWeightFirstFactorTO;

import java.util.List;

/**
 * 客户权重一层因素层设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:16 ]
 * @Description: [ 客户权重一层因素层设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerWeightFirstFactorAPI {
    /**
     * 客户权重一层因素层设置总条数
     */
    default Long countFirstFactor(CustomerWeightFirstFactorDTO customerWeightFirstFactorDTO) throws SerException {
        return null;
    }

    /**
     * 一个客户权重一层因素层设置
     */
    default CustomerWeightFirstFactorBO getOneFirstFactor(String id) throws SerException {
        return null;
    }

    /**
     * 客户权重一层因素层设置列表
     *
     * @return class CustomerWeightFirstFactorBO
     */
    default List<CustomerWeightFirstFactorBO> listFirstFactor(CustomerWeightFirstFactorDTO customerWeightFirstFactorDTO) throws SerException {

        return null;
    }

    /**
     * 添加
     *
     * @param customerWeightFirstFactorTO 客户权重一层因素层设置
     * @return class CustomerWeightFirstFactorBO
     */
    default CustomerWeightFirstFactorBO addFirstFactor(CustomerWeightFirstFactorTO customerWeightFirstFactorTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param customerWeightFirstFactorTO 客户权重一层因素层设置
     * @return class CustomerWeightFirstFactorBO
     */
    default CustomerWeightFirstFactorBO editFirstFactor(CustomerWeightFirstFactorTO customerWeightFirstFactorTO) throws SerException {
        return null;
    }

    /**
     * 删除客户权重一层因素层设置
     *
     * @param id id
     */
    default void deleteFirstFactor(String id) throws SerException {
        return;
    }
}