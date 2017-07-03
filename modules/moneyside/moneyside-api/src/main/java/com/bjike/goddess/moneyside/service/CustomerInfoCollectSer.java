package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.moneyside.bo.CustomerInfoBO;
import com.bjike.goddess.moneyside.bo.CustomerInfoCollectBO;
import com.bjike.goddess.moneyside.dto.CustomerInfoCollectDTO;
import com.bjike.goddess.moneyside.dto.CustomerInfoDTO;
import com.bjike.goddess.moneyside.entity.CustomerInfoCollect;

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
public interface CustomerInfoCollectSer extends Ser<CustomerInfoCollect, CustomerInfoCollectDTO> {

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
}