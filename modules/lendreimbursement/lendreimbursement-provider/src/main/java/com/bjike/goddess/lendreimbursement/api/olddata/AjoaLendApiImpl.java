package com.bjike.goddess.lendreimbursement.api.olddata;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.service.olddata.AjoaLendSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 老系统的借款业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 11:43 ]
 * @Description: [ 老系统的借款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("ajoaLendApiImpl")
public class AjoaLendApiImpl implements AjoaLendAPI {

    @Autowired
    private AjoaLendSer ajoaLendSer;

    @Override
    public void importLendOldData() throws SerException {
        ajoaLendSer.importLendOldData();
    }
}