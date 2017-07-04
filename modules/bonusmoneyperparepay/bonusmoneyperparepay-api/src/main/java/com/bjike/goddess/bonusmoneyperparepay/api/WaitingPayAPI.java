package com.bjike.goddess.bonusmoneyperparepay.api;

import com.bjike.goddess.bonusmoneyperparepay.bo.WaitingPayBO;
import com.bjike.goddess.bonusmoneyperparepay.dto.WaitingPayDTO;
import com.bjike.goddess.bonusmoneyperparepay.to.WaitingPayTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface WaitingPayAPI {
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
     * 删除
     *
     * @param id
     */
    default void deleteWaiting(String id) throws SerException {
        return;
    }
}