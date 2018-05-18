package com.bjike.goddess.customer.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.customer.bo.CustomerContactWeightSetBO;
import com.bjike.goddess.customer.dto.CustomerContactWeightSetDTO;
import com.bjike.goddess.customer.to.CustomerContactWeightSetTO;
import com.bjike.goddess.customer.to.GuidePermissionTO;

import java.util.List;

/**
 * 客户接触阶段权重设置业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 10:46 ]
 * @Description: [ 客户接触阶段权重设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerContactWeightSetAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 客户接触阶段权重设置总条数
     */
    default Long countContactWeight(CustomerContactWeightSetDTO customerContactWeightSetDTO) throws SerException {
        return null;
    }

    /**
     * 一个客户接触阶段权重设置
     */
    default CustomerContactWeightSetBO getOneContactWeight(String id) throws SerException {
        return null;
    }

    /**
     * 客户接触阶段权重设置列表
     *
     * @return class CustomerContactWeightSetBO
     */
    default List<CustomerContactWeightSetBO> listContactWeight(CustomerContactWeightSetDTO customerContactWeightSetDTO) throws SerException {

        return null;
    }

    /**
     * 添加
     *
     * @param customerContactWeightSetTO 客户接触阶段权重设置
     * @return class CustomerContactWeightSetBO
     */
    default CustomerContactWeightSetBO addContactWeight(CustomerContactWeightSetTO customerContactWeightSetTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param customerContactWeightSetTO 客户接触阶段权重设置
     * @return class CustomerContactWeightSetBO
     */
    default CustomerContactWeightSetBO editContactWeight(CustomerContactWeightSetTO customerContactWeightSetTO) throws SerException {
        return null;
    }

    /**
     * 删除客户接触阶段权重设置
     *
     * @param id id
     */
    default void deleteContactWeight(String id) throws SerException {
        return;
    }
    /**
     * 根据接触阶段名获取对应数据
     *
     * @param customerContactType 接触阶段名
     */
    default CustomerContactWeightSetBO findByCustomerType(String customerContactType) throws SerException {
        return null;
    }
    /**
     * 获取所有的接触阶段名
     *
     */
    default List<String> findName() throws SerException {
        return null;
    }
}