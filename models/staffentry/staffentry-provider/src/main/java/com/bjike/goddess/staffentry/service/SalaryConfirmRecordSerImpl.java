package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.staffentry.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.staffentry.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.staffentry.entity.SalaryConfirmRecord;
import com.bjike.goddess.staffentry.to.SalaryConfirmRecordTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 薪资确认业务实现
 * @Author: [tanghaixiang]
 * @Date: [2017-03-10 17:26]
 * @Description: [薪资确认业务实现]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@CacheConfig(cacheNames = "staffentrySerCache")
@Service
public class SalaryConfirmRecordSerImpl extends ServiceImpl<SalaryConfirmRecord, SalaryConfirmRecordDTO> implements SalaryConfirmRecordSer {

    @Cacheable
    @Override
    public List<SalaryConfirmRecord> listSalaryConfirmRecord(SalaryConfirmRecordDTO salaryConfirmRecordDTO) throws SerException {

        //TODO :tanghaixiang 2017-03-11 未做薪资确认分页查询
//        salaryConfirmRecordDTO
        List<SalaryConfirmRecord> salaryConfirmRecords = super.findByPage( salaryConfirmRecordDTO );
        return salaryConfirmRecords;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SalaryConfirmRecordBO insertSalaryConfirmRecord(SalaryConfirmRecordTO salaryConfirmRecordTO) throws SerException {
        SalaryConfirmRecord salaryConfirmRecord = BeanTransform.copyProperties( salaryConfirmRecordTO , SalaryConfirmRecord.class ,true);
        salaryConfirmRecord.setCreateTime( LocalDateTime.now() );
        try {
            super.save( salaryConfirmRecord );
        } catch (SerException e) {
            throw  new SerException( e.getMessage() );
        }
        return BeanTransform.copyProperties( salaryConfirmRecord ,SalaryConfirmRecordBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SalaryConfirmRecordBO editSalaryConfirmRecord(SalaryConfirmRecordTO salaryConfirmRecordTO) throws SerException {
        SalaryConfirmRecord salaryConfirmRecord = BeanTransform.copyProperties( salaryConfirmRecordTO , SalaryConfirmRecord.class ,true);
        salaryConfirmRecord.setModifyTime( LocalDateTime.now() );
        super.update( salaryConfirmRecord );

        return BeanTransform.copyProperties( salaryConfirmRecord ,SalaryConfirmRecordBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeSalaryConfirmRecord(String id) throws SerException {
        try {
            super.remove( id );
        } catch (SerException e) {
            throw  new SerException(e.getMessage() );
        }

    }
}
