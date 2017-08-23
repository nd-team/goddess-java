package com.bjike.goddess.bonusmoneyperparepay.service;

import com.bjike.goddess.bonusmoneyperparepay.bo.MoneyPerpareBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.MoneyPerpareContrastBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.PerpareBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.MoneyPerpareDTO;
import com.bjike.goddess.bonusmoneyperparepay.entity.MoneyPerpare;
import com.bjike.goddess.bonusmoneyperparepay.to.GuidePermissionTO;
import com.bjike.goddess.bonusmoneyperparepay.to.MoneyPerpareTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 奖金资金准备业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 04:44 ]
 * @Description: [ 奖金资金准备业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MoneyPerpareSer extends Ser<MoneyPerpare, MoneyPerpareDTO> {

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
     * 奖金资金准备总条数
     */
    default Long countMoney(MoneyPerpareDTO moneyPerpareDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取奖金资金准备列表
     *
     * @return class MoneyPerpareBO
     */
    default MoneyPerpareBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 奖金资金准备列表
     *
     * @return class MoneyPerpareBO
     */
    default List<MoneyPerpareBO> listMoneyPerpare(MoneyPerpareDTO moneyPerpareDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param moneyPerpareTO 奖金资金准备
     * @return class MoneyPerpareBO
     */
    default MoneyPerpareBO addMoneyPerpare(MoneyPerpareTO moneyPerpareTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param moneyPerpareTO 　奖金资金准备
     * @return class MoneyPerpareBO
     */
    default MoneyPerpareBO editMoneyPerpare(MoneyPerpareTO moneyPerpareTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id
     */
    default void deleteMoneyPerpare(String id) throws SerException {
        return;
    }

    /**
     * 项目组汇总
     *
     * @return class MoneyPerpareBO
     * @throws SerException
     */
    default List<PerpareBO> projectCompare(Integer years, Integer month, String[] projectGroup) throws SerException {
        return null;
    }

    /**
     * 月汇总
     *
     * @return class MoneyPerpareBO
     * @throws SerException
     */
    default List<PerpareBO> monthCompare(Integer years, Integer month) throws SerException {
        return null;
    }

    /**
     * 年汇总
     *
     * @return class MoneyPerpareBO
     * @throws SerException
     */
    default List<PerpareBO> yearsCompare(Integer years) throws SerException {
        return null;
    }

    /**
     * 资金准备对比
     *
     * @return class MoneyPerpareContrastBO
     * @throws SerException
     */
    default List<MoneyPerpareContrastBO> contrastCompare(Integer years, Integer month) throws SerException {
        return null;
    }


    /**
     * 获取所有的项目组
     *
     * @return
     * @throws SerException
     */
    default List<String> findAllProject() throws SerException {
        return null;
    }


}