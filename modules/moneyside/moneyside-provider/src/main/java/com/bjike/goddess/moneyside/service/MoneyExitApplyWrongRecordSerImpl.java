package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyWrongRecordBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyWrongRecordDTO;
import com.bjike.goddess.moneyside.entity.MoneyExitApplyWrongRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 资金退出申请有误记录业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:58 ]
 * @Description: [ 资金退出申请有误记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class MoneyExitApplyWrongRecordSerImpl extends ServiceImpl<MoneyExitApplyWrongRecord, MoneyExitApplyWrongRecordDTO> implements MoneyExitApplyWrongRecordSer {
    @Override
    public Long countMoneyExitApplyWrongRecord(MoneyExitApplyWrongRecordDTO moneyExitApplyWrongRecordDTO) throws SerException {
        Long count = super.count(moneyExitApplyWrongRecordDTO);
        return count;
    }

    @Override
    public MoneyExitApplyWrongRecordBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        MoneyExitApplyWrongRecord moneyExitApplyWrongRecord = super.findById(id);
        return BeanTransform.copyProperties(moneyExitApplyWrongRecord,MoneyExitApplyWrongRecordBO.class);
    }

    @Override
    public List<MoneyExitApplyWrongRecordBO> findListMoneyExitApplyWrongRecord(MoneyExitApplyWrongRecordDTO moneyExitApplyWrongRecordDTO) throws SerException {
        List<MoneyExitApplyWrongRecord> moneyExitApplyWrongRecords = super.findByPage(moneyExitApplyWrongRecordDTO);
        List<MoneyExitApplyWrongRecordBO> moneyExitApplyWrongRecordBOS = BeanTransform.copyProperties(moneyExitApplyWrongRecords,MoneyExitApplyWrongRecordBO.class);
        return moneyExitApplyWrongRecordBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeMoneyExitApplyWrongRecord(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);

    }

}