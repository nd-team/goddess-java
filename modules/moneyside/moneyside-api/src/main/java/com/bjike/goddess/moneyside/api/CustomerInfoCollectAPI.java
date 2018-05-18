package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CustomerInfoCollectBO;
import com.bjike.goddess.moneyside.dto.CustomerInfoCollectDTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;

import java.util.List;

/**
 * 客户信息汇总业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:11 ]
 * @Description: [ 客户信息汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerInfoCollectAPI {
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
     * 客户信息汇总
     *
     * @param dto
     * @return class CustomerInfoCollectBO
     * @throws SerException
     */
    default List<CustomerInfoCollectBO> collect(CustomerInfoCollectDTO dto) throws SerException {
        return null;
    }
    /**
     * 获取所有投资人
     *
     * @return class String
     * @throws SerException
     */
    default List<String> getInvestor() throws SerException {
        return null;
    }
}