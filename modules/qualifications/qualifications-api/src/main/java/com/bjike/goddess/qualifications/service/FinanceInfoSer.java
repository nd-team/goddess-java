package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.FinanceInfoBO;
import com.bjike.goddess.qualifications.dto.FinanceInfoDTO;
import com.bjike.goddess.qualifications.entity.FinanceInfo;
import com.bjike.goddess.qualifications.to.FinanceInfoTO;

/**
 * 财务资料业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 06:42 ]
 * @Description: [ 财务资料业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FinanceInfoSer extends Ser<FinanceInfo, FinanceInfoDTO> {

    default FinanceInfoBO save(FinanceInfoTO to) throws SerException {
        return null;
    }

    default FinanceInfoBO update(FinanceInfoTO to) throws SerException {
        return null;
    }

    default FinanceInfoBO delete(String id) throws SerException {
        return null;
    }


}