package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyDTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.MoneyExitApplyTO;

import java.util.List;

/**
 * 资金退出申请表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:37 ]
 * @Description: [ 资金退出申请表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyExitApplyAPI {
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
     * 资金退出申请表列表总条数
     */
    default Long countMoneyExitApply(MoneyExitApplyDTO moneyExitApplyDTO) throws SerException {
        return null;
    }

    /**
     * 一个资金退出申请表
     *
     * @return class MoneyExitApplyBO
     */
    default MoneyExitApplyBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 资金退出申请表
     *
     * @param moneyExitApplyDTO 资金退出申请表dto
     * @return class MoneyExitApplyBO
     * @throws SerException
     */
    default List<MoneyExitApplyBO> findListMoneyExitApply(MoneyExitApplyDTO moneyExitApplyDTO) throws SerException {
        return null;
    }

    /**
     * 添加资金退出申请表
     *
     * @param moneyExitApplyTO 资金退出申请表数据to
     * @return class MoneyExitApplyBO
     * @throws SerException
     */
    default MoneyExitApplyBO insertMoneyExitApply(MoneyExitApplyTO moneyExitApplyTO) throws SerException {
        return null;
    }

    /**
     * 编辑资金退出申请表
     *
     * @param moneyExitApplyTO 资金退出申请表数据to
     * @return class MoneyExitApplyBO
     * @throws SerException
     */
    default MoneyExitApplyBO editMoneyExitApply(MoneyExitApplyTO moneyExitApplyTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除资金退出申请表
     *
     * @param id
     * @throws SerException
     */
    default void removeMoneyExitApply(String id) throws SerException {

    }
    /**
     * 审核
     *
     * @param moneyExitApplyTO 审核to
     * @return class MoneyExitApplyBO
     * @throws SerException
     */
    default MoneyExitApplyBO audit(MoneyExitApplyTO moneyExitApplyTO) throws SerException {
        return null;
    }
}