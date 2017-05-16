package com.bjike.goddess.housepay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.housepay.bo.MoneyReadyBO;
import com.bjike.goddess.housepay.bo.WaitPayBO;
import com.bjike.goddess.housepay.dto.MoneyReadyDTO;
import com.bjike.goddess.housepay.dto.WaitPayDTO;
import com.bjike.goddess.housepay.entity.WaitPay;
import com.bjike.goddess.housepay.to.MoneyReadyTO;
import com.bjike.goddess.housepay.to.WaitPayTO;

import java.util.List;

/**
 * 等待付款业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-13 09:11 ]
 * @Description: [ 等待付款业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WaitPaySer extends Ser<WaitPay, WaitPayDTO> {
    /**
     * 等待付款列表总条数
     */
    default Long countWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        return null;
    }

    /**
     * 一个等待付款
     *
     * @return class WaitPayBO
     */
    default WaitPayBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 等待付款
     *
     * @param waitPayDTO 等待付款dto
     * @return class WaitPayBO
     * @throws SerException
     */
    default List<WaitPayBO> findListWaitPay(WaitPayDTO waitPayDTO) throws SerException {
        return null;
    }

    /**
     * 添加等待付款
     *
     * @param waitPayTO 等待付款数据to
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO insertWaitPay(WaitPayTO waitPayTO) throws SerException {
        return null;
    }

    /**
     * 编辑等待付款
     *
     * @param waitPayTO 等待付款数据to
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO editWaitPay(WaitPayTO waitPayTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除等待付款
     *
     * @param id
     * @throws SerException
     */
    default void removeWaitPay(String id) throws SerException {

    }
    /**
     * 付款
     *
     * @param waitPayTO
     * @return class WaitPayBO
     * @throws SerException
     */
    default WaitPayBO payment(WaitPayTO waitPayTO) throws SerException {
        return null;
    }

}