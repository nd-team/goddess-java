package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.CooperationSituationBO;
import com.bjike.goddess.supplier.bo.EnterpriseQualificationBO;
import com.bjike.goddess.supplier.dto.EnterpriseQualificationDTO;
import com.bjike.goddess.supplier.entity.EnterpriseQualification;

import java.util.List;

/**
 * 企业资质业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:47:02.259 ]
 * @Description: [ 企业资质业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EnterpriseQualificationSer extends Ser<EnterpriseQualification, EnterpriseQualificationDTO> {

    /**
     * 根据供应商基本信息ID查询企业资质
     *
     * @param info_id 供应商基本信息ID
     * @return
     * @throws SerException
     */
    default List<EnterpriseQualificationBO> findByInformation(String info_id) throws SerException {
        return null;
    }

}