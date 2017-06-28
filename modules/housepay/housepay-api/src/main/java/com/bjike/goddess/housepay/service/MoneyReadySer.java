package com.bjike.goddess.housepay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.housepay.bo.CollectCompareBO;
import com.bjike.goddess.housepay.bo.MoneyReadyBO;
import com.bjike.goddess.housepay.dto.MoneyReadyDTO;
import com.bjike.goddess.housepay.entity.MoneyReady;
import com.bjike.goddess.housepay.to.GuidePermissionTO;
import com.bjike.goddess.housepay.to.MoneyReadyTO;

import java.util.List;

/**
 * 资金准备审核表业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-12 05:32 ]
 * @Description: [ 资金准备审核表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyReadySer extends Ser<MoneyReady, MoneyReadyDTO> {
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
     * 资金准备审核表列表总条数
     */
    default Long countMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        return null;
    }

    /**
     * 一个资金准备审核表
     *
     * @return class MoneyReadyBO
     */
    default MoneyReadyBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 资金准备审核表
     *
     * @param moneyReadyDTO 资金准备审核表dto
     * @return class MoneyReadyBO
     * @throws SerException
     */
    default List<MoneyReadyBO> findListMoneyReady(MoneyReadyDTO moneyReadyDTO) throws SerException {
        return null;
    }

    /**
     * 添加资金准备审核表
     *
     * @param moneyReadyTO 资金准备审核表数据to
     * @return class MoneyReadyBO
     * @throws SerException
     */
    default MoneyReadyBO insertMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        return null;
    }

    /**
     * 编辑资金准备审核表
     *
     * @param moneyReadyTO 资金准备审核表数据to
     * @return class MoneyReadyBO
     * @throws SerException
     */
    default MoneyReadyBO editMoneyReady(MoneyReadyTO moneyReadyTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除资金准备审核表
     *
     * @param id
     * @throws SerException
     */
    default void removeMoneyReady(String id) throws SerException {

    }
    /**
     * 汇总对比
     *
     * @return class CollectCompareBO
     * @throws SerException
     */
    default List<CollectCompareBO> collectCompare(Integer month) throws SerException {
        return null;
    }
}