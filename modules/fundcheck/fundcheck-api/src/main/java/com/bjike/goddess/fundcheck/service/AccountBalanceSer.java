package com.bjike.goddess.fundcheck.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.fundcheck.bo.AccountBalanceBO;
import com.bjike.goddess.fundcheck.dto.AccountBalanceDTO;
import com.bjike.goddess.fundcheck.entity.AccountBalance;
import com.bjike.goddess.fundcheck.to.AccountBalanceCollectTO;
import com.bjike.goddess.fundcheck.to.GuidePermissionTO;

import java.util.List;

/**
 * 账上余额业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-07-01 02:08 ]
 * @Description: [ 账上余额业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AccountBalanceSer extends Ser<AccountBalance, AccountBalanceDTO> {
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
     * 账上余额
     *
     * @param to
     * @return class AccountBalanceBO
     * @throws SerException
     */
    default List<AccountBalanceBO> collect(AccountBalanceCollectTO to) throws SerException {
        return null;
    }
}