package com.bjike.goddess.lendreimbursement.api.olddata;

import com.bjike.goddess.common.api.exception.SerException;

/**
 * 老系统的报销业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-11-17 01:55 ]
 * @Description: [ 老系统的报销业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AjoaReimAPI {

    /**
     * 从老系统导数据进来
     * @throws SerException
     */
    default  void importReimOldData () throws SerException{};

}