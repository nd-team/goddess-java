package com.bjike.goddess.bonusmoneyperparepay.service;

import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingBO;
import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingPayBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.WaitingPayDTO;
import com.bjike.goddess.bonusmoneyperparepay.entity.WaitingPay;
import com.bjike.goddess.bonusmoneyperparepay.to.WaitingPayTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 等待付款业务接口
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-06-30 05:32 ]
 * @Description: [ 等待付款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WaitingPaySer extends Ser<WaitingPay, WaitingPayDTO> {
    /**
     * 等待付款总条数
     */
    default Long countWaiting(WaitingPayDTO waitingPayDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取等待付款列表
     *
     * @return class WaitingPayBO
     */
    default WaitingPayBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 等待付款列表
     *
     * @return class WaitingPayBO
     */
    default List<WaitingPayBO> listWaiting(WaitingPayDTO waitingPayDTO) throws SerException {
        return null;
    }

    /**
     * 已付款列表
     *
     * @return class WaitingPayBO
     */
    default List<WaitingPayBO> list(WaitingPayDTO waitingPayDTO) throws SerException {
        return null;
    }


    /**
     * 删除
     *
     * @param id
     */
    default void deleteWaiting(String id) throws SerException {
        return;
    }

    /**
     * 支付
     * @param id
     * @param payMoney
     * @throws SerException
     */
    default void payMoney(String id,Double payMoney) throws  SerException {
        return;
    }

    /**
     * 项目组汇总
     *
     * @return class MoneyPerpareBO
     * @throws SerException
     */
    default List<WaitingBO> projectCompare(Integer month, String[] projectGroup) throws SerException {
        return null;
    }

    /**
     * 月汇总
     *
     * @return class MoneyPerpareBO
     * @throws SerException
     */
    default List<WaitingBO> monthCompare(Integer month) throws SerException {
        return null;
    }

    /**
     * 年汇总
     *
     * @return class MoneyPerpareBO
     * @throws SerException
     */
    default List<WaitingBO> yearsCompare(Integer years) throws SerException {
        return null;
    }


    /**
     * 等待付款导出excel
     *
     * @param startMonth
     * @param endMonth
     * @return
     * @throws SerException
     */
    byte[] exportExcel(Integer startMonth, Integer endMonth) throws SerException;

    /**
     * 已付款导出excel
     *
     * @param startMonth
     * @param endMonth
     * @return
     * @throws SerException
     */
    byte[] exportArealdyExcel(Integer startMonth, Integer endMonth) throws SerException;


}