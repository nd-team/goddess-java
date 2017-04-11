package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.intromanage.bo.CustomerAndPartnerBO;
import com.bjike.goddess.intromanage.entity.CustomerAndPartner;
import com.bjike.goddess.intromanage.dto.CustomerAndPartnerDTO;
import com.bjike.goddess.intromanage.to.CustomerAndPartnerTO;

import java.util.List;

/**
 * 客户及合作伙伴业务接口
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-27 05:56 ]
 * @Description: [ 客户及合作伙伴业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CustomerAndPartnerSer extends Ser<CustomerAndPartner, CustomerAndPartnerDTO> {

    /**
     * 分页查询客户及合作伙伴
     *
     * @return class CustomerAndPartnerBO
     * @throws SerException
     */
    List<CustomerAndPartnerBO> list(CustomerAndPartnerDTO dto) throws SerException;

    /**
     * 保存客户及合作伙伴
     *
     * @param to 客户及合作伙伴to
     * @return class CustomerAndPartnerBO
     * @throws SerException
     */
    CustomerAndPartnerBO save(CustomerAndPartnerTO to) throws SerException;

    /**
     * 根据id删除客户及合作伙伴
     *
     * @param id 客户及合作伙伴唯一标识
     * @throws SerException
     */
    void remove(String id) throws SerException;

    /**
     * 更新客户及合作伙伴
     *
     * @param to 客户及合作伙伴to
     * @throws SerException
     */
    void update(CustomerAndPartnerTO to) throws SerException;

}