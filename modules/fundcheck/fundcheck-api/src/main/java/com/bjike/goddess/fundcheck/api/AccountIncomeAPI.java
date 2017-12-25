package com.bjike.goddess.fundcheck.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundcheck.bo.AccountIncomeBO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;

import java.util.List;

/**
 * 账务收入业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 01:52 ]
 * @Description: [ 账务收入业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountIncomeAPI {
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
    /**
     * 账务收入
     *
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return class AccountIncomeBO
     * @throws SerException
     */
    default List<AccountIncomeBO> collect(String startTime,String endTime) throws SerException {
        return null;
    }
}