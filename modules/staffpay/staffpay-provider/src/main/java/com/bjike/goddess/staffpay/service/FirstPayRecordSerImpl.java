package com.bjike.goddess.staffpay.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffpay.bo.FirstPayRecordBO;
import com.bjike.goddess.staffpay.bo.PayRecordBO;
import com.bjike.goddess.staffpay.dto.FirstPayRecordDTO;
import com.bjike.goddess.staffpay.entity.FirstPayRecord;
import com.bjike.goddess.staffpay.entity.PayRecord;
import com.bjike.goddess.staffpay.enums.ConfirmStatus;
import com.bjike.goddess.staffpay.to.FirstPayRecordTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 第一次已付款记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-18 02:01 ]
 * @Description: [ 第一次已付款记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "staffpaySerCache")
@Service
public class FirstPayRecordSerImpl extends ServiceImpl<FirstPayRecord, FirstPayRecordDTO> implements FirstPayRecordSer {

    @Autowired
    private PayRecordSer payRecordSer;
    @Override
    public Long countFirstPayRecord(FirstPayRecordDTO firstPayRecordDTO) throws SerException {
        Long count = super.count(firstPayRecordDTO);
        return count;
    }

    @Override
    public FirstPayRecordBO getOne(String id) throws SerException {
        FirstPayRecord firstPayRecord = super.findById(id);
        return BeanTransform.copyProperties(firstPayRecord,FirstPayRecordBO.class);
    }

    @Override
    public List<FirstPayRecordBO> findListFirstPayRecord(FirstPayRecordDTO firstPayRecordDTO) throws SerException {
        List<FirstPayRecord> firstPayRecords = super.findByPage(firstPayRecordDTO);
        List<FirstPayRecordBO> firstPayRecordBOS = BeanTransform.copyProperties(firstPayRecords,FirstPayRecordBO.class);
        return firstPayRecordBOS;
    }
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeFirstPayRecord(String id) throws SerException {
        super.remove(id);
    }
    @Override
    public PayRecordBO payment(FirstPayRecordTO firstPayRecordTO) throws SerException {
        FirstPayRecord firstPayRecord = super.findById(firstPayRecordTO.getId());
        BeanTransform.copyProperties(firstPayRecordTO,firstPayRecord,true);
        if (ConfirmStatus.NO.equals(firstPayRecord.getConfirmFirstSalary())) {
            firstPayRecord.setConfirmFirstSalary(ConfirmStatus.YES);
            super.update(firstPayRecord);
        }

        PayRecord payRecord = new PayRecord();
        BeanUtils.copyProperties(firstPayRecord,payRecord);
        payRecordSer.save(payRecord);
        return BeanTransform.copyProperties(payRecord, PayRecordBO.class);
    }
}