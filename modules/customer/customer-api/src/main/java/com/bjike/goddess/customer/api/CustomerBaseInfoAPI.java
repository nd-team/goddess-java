package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerBaseInfoBO;
import com.bjike.goddess.customer.dto.CustomerBaseInfoDTO;
import com.bjike.goddess.customer.to.CustomerBaseInfoTO;

import java.util.List;

/**
 * 客户基本信息业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-15T16:23:28.056 ]
 * @Description: [ 客户基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerBaseInfoAPI {

    /**
     * 自动生成一个客户编号
     *
     * @return class CustomerBaseInfoBO
     */
    default CustomerBaseInfoBO generateCustomerNum() throws SerException {
        return null;
    }

    /**
     * 客户基本信息列表
     *
     * @return class CustomerBaseInfoBO
     */
    default List<CustomerBaseInfoBO> listCustomerBaseInfo(CustomerBaseInfoDTO customerBaseInfoDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param customerBaseInfoTO 客户基本信息信息
     * @return class CustomerBaseInfoBO
     */
    default CustomerBaseInfoBO addCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param customerBaseInfoTO 客户基本信息信息
     * @return class CustomerBaseInfoBO
     */
    default CustomerBaseInfoBO editCustomerBaseInfo(CustomerBaseInfoTO customerBaseInfoTO) throws SerException {
        return null;
    }

    /**
     * 删除基本信息
     *
     * @param id id
     */
    default void deleteCustomerBaseInfo(String id) throws SerException {
        return;
    }

    ;

    /**
     * 冻结客户基本信息
     *
     * @param id id
     */
    default void congealCustomerBaseInfo(String id) throws SerException {
        return;
    }

    ;

    /**
     * 解冻客户基本信息
     *
     * @param id id
     */
    default void thawCustomerBaseInfo(String id) throws SerException {
        return;
    }

    ;

    /**
     * 获取地区
     *
     * @return class String
     */
    default List<String> getCustomerBaseInfoArea() throws SerException {
        return null;
    }

    /**
     * 获取客户名
     *
     * @return class String
     */
    default List<String> getCustomerBaseInfoName() throws SerException {
        return null;
    }

}