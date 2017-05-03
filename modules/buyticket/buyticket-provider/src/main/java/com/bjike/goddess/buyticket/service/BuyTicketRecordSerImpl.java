package com.bjike.goddess.buyticket.service;

import com.bjike.goddess.buyticket.bo.BuyTicketRecordBO;
import com.bjike.goddess.buyticket.dto.BuyTicketRecordDTO;
import com.bjike.goddess.buyticket.entity.BuyTicketRecord;
import com.bjike.goddess.buyticket.to.BuyTicketRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 车票购买记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-25 04:33 ]
 * @Description: [ 车票购买记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "buyticketSerCache")
@Service
public class BuyTicketRecordSerImpl extends ServiceImpl<BuyTicketRecord, BuyTicketRecordDTO> implements BuyTicketRecordSer {

    @Override
    public Long countBuyTicketRecord(BuyTicketRecordDTO buyTicketRecordDTO) throws SerException {
        buyTicketRecordDTO.getSorts().add("createTime=desc");
        Long count = super.count(buyTicketRecordDTO);
        return count;
    }
    @Override
    public BuyTicketRecordBO getOne(String id) throws SerException {
        BuyTicketRecord buyTicketRecord = super.findById(id);
        return BeanTransform.copyProperties(buyTicketRecord,BuyTicketRecordBO.class,true);
    }


    @Override
    public List<BuyTicketRecordBO> findListBuyTicketRecord(BuyTicketRecordDTO buyTicketRecordDTO) throws SerException {
        List<BuyTicketRecord> buyTicketRecords = super.findByCis(buyTicketRecordDTO, true);
        List<BuyTicketRecordBO> buyTicketRecordBOS = BeanTransform.copyProperties(buyTicketRecords, BuyTicketRecordBO.class, true);
        return buyTicketRecordBOS;
    }

    @Override
    public BuyTicketRecordBO insertBuyTicketRecord(BuyTicketRecordTO buyTicketRecordTO) throws SerException {
        BuyTicketRecord buyTicketRecord = BeanTransform.copyProperties(buyTicketRecordTO, BuyTicketRecord.class, true);
        buyTicketRecord.setCreateTime(LocalDateTime.now());
        super.save(buyTicketRecord);
        return BeanTransform.copyProperties(buyTicketRecord, BuyTicketRecordBO.class, true);
    }

    @Override
    public BuyTicketRecordBO editBuyTicketRecord(BuyTicketRecordTO buyTicketRecordTO) throws SerException {
        BuyTicketRecord buyTicketRecord = super.findById(buyTicketRecordTO.getId());
        BeanTransform.copyProperties(buyTicketRecordTO,buyTicketRecord,true);
        buyTicketRecord.setModifyTime(LocalDateTime.now());
        super.update(buyTicketRecord);
        return BeanTransform.copyProperties(buyTicketRecord,BuyTicketRecordBO.class);
    }

    @Override
    public void removeBuyTicketRecord(String id) throws SerException {
        super.remove(id);
    }


    @Override
    public BuyTicketRecordBO sendBuyTicketRecord(BuyTicketRecordTO buyTicketRecordTO) throws SerException {
        //todo:未做发送邮件
        return null;
    }
}