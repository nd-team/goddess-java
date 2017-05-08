package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BasicInfoBO;
import com.bjike.goddess.buyticket.bo.BuyTicketRecordBO;
import com.bjike.goddess.buyticket.dto.BuyTicketRecordDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketRecord;
import com.bjike.goddess.buyticket.to.BuyTicketRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 车票购买记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:33 ]
 * @Description: [ 车票购买记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface BuyTicketRecordSer extends Ser<BuyTicketRecord, BuyTicketRecordDTO> {

    /**
     * 车票购买记录列表总条数
     */
    default Long countBuyTicketRecord(BuyTicketRecordDTO buyTicketRecordDTO) throws SerException {
        return null;
    }
    /**
     * 一个车票购买记录
     * @return class BuyTicketRecordBO
     */
    default BuyTicketRecordBO getOne(String id) throws SerException {return null;}

    /**
     * 车票购买记录
     *
     * @param buyTicketRecordDTO 车票购买记录dto
     * @return class BuyTicketRecordBO
     * @throws SerException
     */
    default List<BuyTicketRecordBO> findListBuyTicketRecord(BuyTicketRecordDTO buyTicketRecordDTO) throws SerException {
        return null;
    }

    /**
     * 添加车票购买记录
     *
     * @param buyTicketRecordTO 车票购买记录数据to
     * @return class BuyTicketRecordBO
     * @throws SerException
     */
    default BuyTicketRecordBO insertBuyTicketRecord(BuyTicketRecordTO buyTicketRecordTO) throws SerException {
        return null;
    }

    /**
     * 编辑车票购买记录
     *
     * @param buyTicketRecordTO 车票购买记录数据to
     * @return class BuyTicketRecordBO
     * @throws SerException
     */
    default BuyTicketRecordBO editBuyTicketRecord(BuyTicketRecordTO buyTicketRecordTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除车票购买记录
     *
     * @param id
     * @throws SerException
     */
    default void removeBuyTicketRecord(String id) throws SerException {

    }


    /**
     * 发送邮件
     *
     * @return class BuyTicketApplyBO
     */
    default BuyTicketRecordBO sendBuyTicketRecord(BuyTicketRecordTO buyTicketRecordTO) throws SerException {
        return null;
    }
}