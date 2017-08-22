package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyWrongRecordBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyWrongRecordDTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;

import java.util.List;

/**
 * 资金退出申请有误记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:58 ]
 * @Description: [ 资金退出申请有误记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyExitApplyWrongRecordAPI {
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
     * 资金退出申请有误记录列表总条数
     */
    default Long countMoneyExitApplyWrongRecord(MoneyExitApplyWrongRecordDTO moneyExitApplyWrongRecordDTO) throws SerException {
        return null;
    }

    /**
     * 一个资金退出申请有误记录
     *
     * @return class MoneyExitApplyWrongRecordBO
     */
    default MoneyExitApplyWrongRecordBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 资金退出申请有误记录
     *
     * @param moneyExitApplyWrongRecordDTO 资金退出申请有误记录dto
     * @return class MoneyExitApplyWrongRecordBO
     * @throws SerException
     */
    default List<MoneyExitApplyWrongRecordBO> findListMoneyExitApplyWrongRecord(MoneyExitApplyWrongRecordDTO moneyExitApplyWrongRecordDTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除资金退出申请有误记录
     *
     * @param id
     * @throws SerException
     */
    default void removeMoneyExitApplyWrongRecord(String id) throws SerException {

    }
}