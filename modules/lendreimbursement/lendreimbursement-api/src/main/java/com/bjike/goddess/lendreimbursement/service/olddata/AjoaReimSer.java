package com.bjike.goddess.lendreimbursement.service.olddata;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaReim;
import com.bjike.goddess.lendreimbursement.dto.olddata.AjoaReimDTO;

/**
 * 老系统的报销业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 01:55 ]
 * @Description: [ 老系统的报销业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AjoaReimSer extends Ser<AjoaReim, AjoaReimDTO> {


    /**
     * 从老系统导数据进来
     * @throws SerException
     */
    default  void importReimOldData () throws SerException{};


}