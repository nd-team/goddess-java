package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.AuditMaterialBO;
import com.bjike.goddess.qualifications.dto.AuditMaterialDTO;
import com.bjike.goddess.qualifications.entity.AuditMaterial;
import com.bjike.goddess.qualifications.to.AuditMaterialTO;

/**
 * 审核资料业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:44 ]
 * @Description: [ 审核资料业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AuditMaterialSer extends Ser<AuditMaterial, AuditMaterialDTO> {

    default AuditMaterialBO save(AuditMaterialTO to) throws SerException {
        return null;
    }

    default AuditMaterialBO update(AuditMaterialTO to) throws SerException {
        return null;
    }

    default AuditMaterialBO delete(String id) throws SerException {
        return null;
    }


}