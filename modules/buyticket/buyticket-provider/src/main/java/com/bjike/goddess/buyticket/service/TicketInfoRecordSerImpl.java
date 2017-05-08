package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.TicketInfoRecordBO;
import com.bjike.goddess.buyticket.dto.TicketInfoRecordDTO;
import com.bjike.goddess.buyticket.entity.TicketInfoRecord;
import com.bjike.goddess.buyticket.to.TicketInfoRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 车票信息记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 03:48 ]
 * @Description: [ 车票信息记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class TicketInfoRecordSerImpl extends ServiceImpl<TicketInfoRecord, TicketInfoRecordDTO> implements TicketInfoRecordSer {

    @Override
    public Long countTicketInfoRecord(TicketInfoRecordDTO ticketInfoRecordDTO) throws SerException {
        ticketInfoRecordDTO.getSorts().add("createTime=desc");
        Long count = super.count(ticketInfoRecordDTO);
        return count;
    }
    @Override
    public TicketInfoRecordBO getOne(String id) throws SerException {
        TicketInfoRecord ticketInfoRecord = super.findById(id);
        return BeanTransform.copyProperties(ticketInfoRecord,TicketInfoRecordBO.class,true);
    }


    @Override
    public List<TicketInfoRecordBO> findListTicketInfoRecord(TicketInfoRecordDTO ticketInfoRecordDTO) throws SerException {
        List<TicketInfoRecord> ticketInfoRecords = super.findByCis(ticketInfoRecordDTO,true);
        List<TicketInfoRecordBO> ticketInfoRecordBOS = BeanTransform.copyProperties(ticketInfoRecords,TicketInfoRecordBO.class,true);
        return ticketInfoRecordBOS;
    }

    @Override
    public TicketInfoRecordBO insertTicketInfoRecord(TicketInfoRecordTO ticketInfoRecordTO) throws SerException {
        TicketInfoRecord ticketInfoRecord = BeanTransform.copyProperties(ticketInfoRecordTO,TicketInfoRecord.class,true);
        ticketInfoRecord.setCreateTime(LocalDateTime.now());
        super.save(ticketInfoRecord);
        return BeanTransform.copyProperties(ticketInfoRecord,TicketInfoRecordBO.class);
    }

    @Override
    public TicketInfoRecordBO editTicketInfoRecord(TicketInfoRecordTO ticketInfoRecordTO) throws SerException {
        TicketInfoRecord ticketInfoRecord = super.findById(ticketInfoRecordTO.getId());
        BeanTransform.copyProperties(ticketInfoRecordTO,ticketInfoRecord,true);
        ticketInfoRecord.setModifyTime(LocalDateTime.now());
        super.update(ticketInfoRecord);
        return BeanTransform.copyProperties(ticketInfoRecord,TicketInfoRecordBO.class);
    }

    @Override
    public void removeTicketInfoRecord(String id) throws SerException {
        super.remove(id);
    }
    @Override
    public void congealTicketInfoRecord(String id) throws SerException{
        try {
            TicketInfoRecord ticketInfoRecord = super.findById(id);
            ticketInfoRecord.setModifyTime(LocalDateTime.now());
            ticketInfoRecord.setStatus(Status.CONGEAL);

            super.update(ticketInfoRecord);
        } catch (SerException e) {
            throw new SerException("冻结出现错误，冻结失败"+e.getMessage());
        }
    }

    @Override
    public void thawTicketInfoRecord(String id) throws SerException{
        try {
            TicketInfoRecord ticketInfoRecord = super.findById(id);
            ticketInfoRecord.setModifyTime(LocalDateTime.now());
            ticketInfoRecord.setStatus(Status.THAW);

            super.update(ticketInfoRecord);
        } catch (SerException e) {
            throw new SerException("解冻出现错误，解冻失败"+e.getMessage());
        }
    }
}