package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.BuyTicketRecordBO;
import com.bjike.goddess.buyticket.dto.BuyTicketRecordDTO;
import com.bjike.goddess.buyticket.to.BuyTicketRecordTO;
import com.bjike.goddess.buyticket.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface BuyTicketRecordAPI {

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



}