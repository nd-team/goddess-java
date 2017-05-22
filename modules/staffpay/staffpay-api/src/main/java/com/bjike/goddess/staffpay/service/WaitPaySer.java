package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.WaitPayBO;
import com.bjike.goddess.staffpay.dto.WaitPayDTO;
import com.bjike.goddess.staffpay.entity.WaitPay;
import com.bjike.goddess.staffpay.to.WaitPayTO;

import java.util.List;

/**
 * 等待付款业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 11:38 ]
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
     * @return class FirstPayRecordBO
     * @throws SerException
     */
    default FirstPayRecordBO payment(WaitPayTO waitPayTO) throws SerException {
        return null;
    }
}