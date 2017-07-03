package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.FundEntryWrongRecordBO;
import com.bjike.goddess.moneyside.dto.FundEntryWrongRecordDTO;
import com.bjike.goddess.moneyside.entity.FundEntryWrongRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资金进入申请有误记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:07 ]
 * @Description: [ 资金进入申请有误记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class FundEntryWrongRecordSerImpl extends ServiceImpl<FundEntryWrongRecord, FundEntryWrongRecordDTO> implements FundEntryWrongRecordSer {
    @Override
    public Long countFundEntryWrongRecord(FundEntryWrongRecordDTO fundEntryWrongRecordDTO) throws SerException {
        Long count = super.count(fundEntryWrongRecordDTO);
        return count;
    }

    @Override
    public FundEntryWrongRecordBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        FundEntryWrongRecord fundEntryWrongRecord = super.findById(id);
        return BeanTransform.copyProperties(fundEntryWrongRecord, FundEntryWrongRecordBO.class);
    }

    @Override
    public List<FundEntryWrongRecordBO> findListFundEntryWrongRecord(FundEntryWrongRecordDTO fundEntryWrongRecordDTO) throws SerException {
        List<FundEntryWrongRecord> fundEntryWrongRecords = super.findByPage(fundEntryWrongRecordDTO);
        List<FundEntryWrongRecordBO> fundEntryWrongRecordBOS = BeanTransform.copyProperties(fundEntryWrongRecords, FundEntryWrongRecordBO.class);
        return fundEntryWrongRecordBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeFundEntryWrongRecord(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);

    }
}