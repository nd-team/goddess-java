package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerLevelBO;
import com.bjike.goddess.customer.dto.CustomerLevelDTO;
import com.bjike.goddess.customer.to.CustomerLevelTO;

import java.util.List;

/**
 * 客户级别业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T17:00:40.872 ]
 * @Description: [ 客户级别业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerLevelAPI {

    /**
     * 客户级别列表总条数
     *
     */
    default Long countCustomerLevel(CustomerLevelDTO customerLevelDTO) throws SerException {
        return null;
    }
    /**
     * 客户级别列表
     * @return class CustomerLevelBO
     */
    default List<CustomerLevelBO> listCustomerLevel(CustomerLevelDTO customerLevelDTO) throws SerException {return null;}
    /**
     *  添加
     * @param customerLevelTO 客户级别信息
     * @return class CustomerLevelBO
     */
    default CustomerLevelBO addCustomerLevel(CustomerLevelTO customerLevelTO) throws SerException { return null;}

    /**
     *  编辑
     * @param customerLevelTO 客户级别信息
     * @return class CustomerLevelBO
     */
    default CustomerLevelBO editCustomerLevel(CustomerLevelTO customerLevelTO) throws SerException { return null;}

    /**
     * 删除级别
     * @param id id
     */
    default void deleteCustomerLevel(String id ) throws SerException {return;};

    /**
     * 冻结客户级别
     * @param id id
     */
    default void congealCustomerLevel(String id ) throws SerException {return;};

    /**
     * 解冻客户级别
     * @param id id
     */
    default void thawCustomerLevel(String id ) throws SerException {return;};

    /**
     * 根据级别名查找
     * @param name 级别名
     */
    default CustomerLevelBO getCustomerLevelByName (String name )throws SerException {return null ;}

    /**
     * 获取级别数组
     *
     * @return class String
     */
    default List<String> getAllLevel() throws SerException {
        return null;
    }
}