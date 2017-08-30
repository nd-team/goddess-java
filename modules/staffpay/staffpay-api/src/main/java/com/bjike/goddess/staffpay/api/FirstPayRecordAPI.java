package com.bjike.goddess.staffpay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.dto.FirstPayRecordDTO;
import com.bjike.goddess.staffpay.to.FirstPayRecordTO;
import com.bjike.goddess.staffpay.to.GuidePermissionTO;

import java.util.List;

/**
 * 第一次已付款记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:01 ]
 * @Description: [ 第一次已付款记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirstPayRecordAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }
    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

}