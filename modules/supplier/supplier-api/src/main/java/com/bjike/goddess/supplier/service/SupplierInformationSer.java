package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.supplier.bo.SupplierInformationBO;
import com.bjike.goddess.supplier.dto.SupplierInformationDTO;
import com.bjike.goddess.supplier.entity.SupplierInformation;
import com.bjike.goddess.supplier.to.*;

/**
 * 供应商基本信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:46:45.052 ]
 * @Description: [ 供应商基本信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface SupplierInformationSer extends Ser<SupplierInformation, SupplierInformationDTO> {

    default SupplierInformationBO save(SupplierInformationTO to) throws SerException {
        return null;
    }

    default SupplierInformationBO update(SupplierInformationTO to, ContactSituationTO contactSituation
            , CooperationSituationTO cooperationSituation, EnterpriseQualificationTO enterpriseQualification
            , RewardSituationTO rewardSituation) throws SerException {
        return null;
    }


}