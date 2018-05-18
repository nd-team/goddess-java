package com.bjike.goddess.lendreimbursement.api.olddata;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.entity.olddata.AjoaReim;
import com.bjike.goddess.lendreimbursement.service.olddata.AjoaReimSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 老系统的报销业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 01:55 ]
 * @Description: [ 老系统的报销业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("ajoaReimApiImpl")
public class AjoaReimApiImpl implements AjoaReimAPI {

    @Autowired
    private AjoaReimSer ajoaReimSer;

    @Override
    public void importReimOldData() throws SerException {
        ajoaReimSer.importReimOldData();
    }
}