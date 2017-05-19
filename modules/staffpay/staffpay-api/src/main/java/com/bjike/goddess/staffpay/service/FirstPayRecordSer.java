package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.dto.FirstPayRecordDTO;
import com.bjike.goddess.staffpay.entity.FirstPayRecord;
import com.bjike.goddess.staffpay.to.FirstPayRecordTO;
import com.bjike.goddess.staffpay.to.WaitPayTO;

import java.util.List;

/**
 * 第一次已付款记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:01 ]
 * @Description: [ 第一次已付款记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface FirstPayRecordSer extends Ser<FirstPayRecord, FirstPayRecordDTO> {

    /**
     * 第一次已付款记录列表总条数
     */
    default Long countFirstPayRecord(FirstPayRecordDTO firstPayRecordDTO) throws SerException {
        return null;
    }

    /**
     * 一个第一次已付款记录
     *
     * @return class FirstPayRecordBO
     */
    default FirstPayRecordBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 第一次已付款记录
     *
     * @param firstPayRecordDTO 第一次已付款记录dto
     * @return class FirstPayRecordBO
     * @throws SerException
     */
    default List<FirstPayRecordBO> findListFirstPayRecord(FirstPayRecordDTO firstPayRecordDTO) throws SerException {
        return null;
    }
    /**
     * 根据id删除第一次已付款记录
     *
     * @param id
     * @throws SerException
     */
    default void removeFirstPayRecord(String id) throws SerException {

    }
    /**
     * 付款
     *
     * @param firstPayRecordTO
     * @return class PayRecordBO
     * @throws SerException
     */
    default PayRecordBO payment(FirstPayRecordTO firstPayRecordTO) throws SerException {
        return null;
    }
}