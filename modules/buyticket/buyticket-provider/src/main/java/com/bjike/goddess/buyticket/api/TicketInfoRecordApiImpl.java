package com.bjike.goddess.buyticket.api;

import com.bjike.goddess.buyticket.bo.TicketInfoRecordBO;
import com.bjike.goddess.buyticket.dto.TicketInfoRecordDTO;
import com.bjike.goddess.buyticket.entity.TicketInfoRecord;
import com.bjike.goddess.buyticket.service.TicketInfoRecordSer;
import com.bjike.goddess.buyticket.to.TicketInfoRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 车票信息记录业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:48 ]
 * @Description: [ 车票信息记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("ticketInfoRecordApiImpl")
public class TicketInfoRecordApiImpl implements TicketInfoRecordAPI {

    @Autowired
    private TicketInfoRecordSer ticketInfoRecordSer;
    @Override
    public Long countTicketInfoRecord(TicketInfoRecordDTO ticketInfoRecordDTO) throws SerException {
        return ticketInfoRecordSer.countTicketInfoRecord(ticketInfoRecordDTO);
    }
    @Override
    public TicketInfoRecordBO getOne(String id) throws SerException {
        return ticketInfoRecordSer.getOne(id);
    }
    @Override
    public List<TicketInfoRecordBO> findListTicketInfoRecord(TicketInfoRecordDTO ticketInfoRecordDTO) throws SerException {
        return ticketInfoRecordSer.findListTicketInfoRecord(ticketInfoRecordDTO);
    }

    @Override
    public TicketInfoRecordBO insertTicketInfoRecord(TicketInfoRecordTO ticketInfoRecordTO) throws SerException {
        return ticketInfoRecordSer.insertTicketInfoRecord(ticketInfoRecordTO);
    }

    @Override
    public TicketInfoRecordBO editTicketInfoRecord(TicketInfoRecordTO ticketInfoRecordTO) throws SerException {
        return ticketInfoRecordSer.editTicketInfoRecord(ticketInfoRecordTO);
    }

    @Override
    public void removeTicketInfoRecord(String id) throws SerException {
        ticketInfoRecordSer.removeTicketInfoRecord(id);
    }
    @Override
    public void congealTicketInfoRecord(String id) throws SerException{
        ticketInfoRecordSer.congealTicketInfoRecord(id);
    }

    @Override
    public void thawTicketInfoRecord(String id) throws SerException{
        ticketInfoRecordSer.thawTicketInfoRecord(id);
    }
}