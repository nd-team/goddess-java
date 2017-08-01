package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.TicketInfoRecordBO;
import com.bjike.goddess.buyticket.dto.TicketInfoRecordDTO;
import com.bjike.goddess.buyticket.entity.TicketInfoRecord;
import com.bjike.goddess.buyticket.to.GuidePermissionTO;
import com.bjike.goddess.buyticket.to.TicketInfoRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 车票信息记录业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:48 ]
 * @Description: [ 车票信息记录业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TicketInfoRecordSer extends Ser<TicketInfoRecord, TicketInfoRecordDTO> {

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
     * 车票信息记录列表总条数
     */
    default Long countTicketInfoRecord(TicketInfoRecordDTO ticketInfoRecordDTO) throws SerException {
        return null;
    }

    /**
     * 一个车票信息记录
     *
     * @return class TicketInfoRecordBO
     */
    default TicketInfoRecordBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 车票信息记录
     *
     * @param ticketInfoRecordDTO 车票信息记录dto
     * @return class TicketInfoRecordBO
     * @throws SerException
     */
    default List<TicketInfoRecordBO> findListTicketInfoRecord(TicketInfoRecordDTO ticketInfoRecordDTO) throws SerException {
        return null;
    }

    /**
     * 添加车票信息记录
     *
     * @param ticketInfoRecordTO 车票信息记录数据to
     * @return class TicketInfoRecordBO
     * @throws SerException
     */
    default TicketInfoRecordBO insertTicketInfoRecord(TicketInfoRecordTO ticketInfoRecordTO) throws SerException {
        return null;
    }

    /**
     * 编辑车票信息记录
     *
     * @param ticketInfoRecordTO 车票信息记录数据to
     * @return class TicketInfoRecordBO
     * @throws SerException
     */
    default TicketInfoRecordBO editTicketInfoRecord(TicketInfoRecordTO ticketInfoRecordTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除车票信息记录
     *
     * @param id
     * @throws SerException
     */
    default void removeTicketInfoRecord(String id) throws SerException {

    }

    /**
     * 冻结车票信息记录
     *
     * @param id 车票信息记录ID
     * @throws SerException 冻结车票信息记录异常
     */
    default void congealTicketInfoRecord(String id) throws SerException {

    }

    /**
     * 解冻车票信息记录
     *
     * @param id 车票信息记录Id
     * @throws SerException 解冻车票信息记录异常
     */
    default void thawTicketInfoRecord(String id) throws SerException {

    }
}