package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyConfirmedBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyConfirmedDTO;

import java.util.List;

/**
 * 资金退出申请确认表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:57 ]
 * @Description: [ 资金退出申请确认表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyExitApplyConfirmedAPI {
    /**
     * 资金退出申请确认表列表总条数
     */
    default Long countMoneyExitApplyConfirmed(MoneyExitApplyConfirmedDTO moneyExitApplyConfirmedDTO) throws SerException {
        return null;
    }

    /**
     * 一个资金退出申请确认表
     *
     * @return class MoneyExitApplyConfirmedBO
     */
    default MoneyExitApplyConfirmedBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 资金退出申请确认表
     *
     * @param moneyExitApplyConfirmedDTO 资金退出申请确认表dto
     * @return class MoneyExitApplyConfirmedBO
     * @throws SerException
     */
    default List<MoneyExitApplyConfirmedBO> findListMoneyExitApplyConfirmed(MoneyExitApplyConfirmedDTO moneyExitApplyConfirmedDTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除资金退出申请确认表
     *
     * @param id
     * @throws SerException
     */
    default void removeMoneyExitApplyConfirmed(String id) throws SerException {

    }

}