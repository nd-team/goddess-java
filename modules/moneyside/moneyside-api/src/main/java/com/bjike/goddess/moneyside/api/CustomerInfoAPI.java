package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CustomerInfoBO;
import com.bjike.goddess.moneyside.dto.CustomerInfoDTO;
import com.bjike.goddess.moneyside.to.CustomerInfoTO;

import java.util.List;

/**
 * 客户信息业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 09:58 ]
 * @Description: [ 客户信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerInfoAPI {
    /**
     * 客户信息列表总条数
     */
    default Long countCustomerInfo(CustomerInfoDTO customerInfoDTO) throws SerException {
        return null;
    }

    /**
     * 一个客户信息
     *
     * @return class CustomerInfoBO
     */
    default CustomerInfoBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 客户信息
     *
     * @param customerInfoDTO 客户信息dto
     * @return class CustomerInfoBO
     * @throws SerException
     */
    default List<CustomerInfoBO> findListCustomerInfo(CustomerInfoDTO customerInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加客户信息
     *
     * @param customerInfoTO 客户信息数据to
     * @return class CustomerInfoBO
     * @throws SerException
     */
    default CustomerInfoBO insertCustomerInfo(CustomerInfoTO customerInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑客户信息
     *
     * @param customerInfoTO 客户信息数据to
     * @return class CustomerInfoBO
     * @throws SerException
     */
    default CustomerInfoBO editCustomerInfo(CustomerInfoTO customerInfoTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除客户信息
     *
     * @param id
     * @throws SerException
     */
    default void removeCustomerInfo(String id) throws SerException {

    }
}