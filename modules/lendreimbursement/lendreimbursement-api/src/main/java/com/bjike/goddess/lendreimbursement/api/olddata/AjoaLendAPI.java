package com.bjike.goddess.lendreimbursement.api.olddata;

import com.bjike.goddess.common.api.exception.SerException;

/**
 * 老系统的借款业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 11:43 ]
 * @Description: [ 老系统的借款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AjoaLendAPI {

    /**
     * 从老系统导数据进来
     * @throws SerException
     */
    default  void importLendOldData () throws SerException{};

}