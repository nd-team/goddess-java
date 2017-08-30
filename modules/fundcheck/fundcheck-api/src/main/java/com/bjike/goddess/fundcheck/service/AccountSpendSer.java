package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.fundcheck.bo.AccountSpendBO;
import com.bjike.goddess.fundcheck.dto.AccountSpendDTO;
import com.bjike.goddess.fundcheck.entity.AccountSpend;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;

import java.util.List;

/**
 * 账务支出业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:02 ]
 * @Description: [ 账务支出业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountSpendSer extends Ser<AccountSpend, AccountSpendDTO> {
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
     * @return class AccountSpendBO
     * @throws SerException
     */
    default List<AccountSpendBO> collect(String startTime,String endTime) throws SerException {
        return null;
    }
}