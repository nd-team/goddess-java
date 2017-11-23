package com.bjike.goddess.lendreimbursement.service.olddata;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaLend;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaLendDTO;

/**
 * 老系统的借款业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 11:43 ]
 * @Description: [ 老系统的借款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AjoaLendSer extends Ser<AjoaLend, AjoaLendDTO> {


    /**
     * 从老系统导数据进来
     * @throws SerException
     */
    default  void importLendOldData () throws SerException{};
}