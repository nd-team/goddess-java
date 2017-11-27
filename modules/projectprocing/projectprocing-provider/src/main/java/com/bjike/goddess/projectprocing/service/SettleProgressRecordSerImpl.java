package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.SettleProgressRecordBO;
import com.bjike.goddess.projectprocing.dto.SettleProgressRecordDTO;
import com.bjike.goddess.projectprocing.entity.SettleProgressRecord;
import com.bjike.goddess.projectprocing.to.SettleProgressRecordTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 结算进度调整记录&结算问题汇总业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 03:19 ]
 * @Description: [ 结算进度调整记录&结算问题汇总业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class SettleProgressRecordSerImpl extends ServiceImpl<SettleProgressRecord, SettleProgressRecordDTO> implements SettleProgressRecordSer {
    @Override
    public Long countManage(SettleProgressRecordDTO settleProgressRecordDTO) throws SerException {
        Long count = super.count(settleProgressRecordDTO);
        return count;
    }

    @Override
    public SettleProgressRecordBO getOneById(String id) throws SerException {
        SettleProgressRecord settleProgressRecord = super.findById(id);
        return BeanTransform.copyProperties(settleProgressRecord, SettleProgressRecordBO.class);
    }

    @Override
    public List<SettleProgressRecordBO> listManage(SettleProgressRecordDTO settleProgressRecordDTO) throws SerException {
        List<SettleProgressRecord> settleProgressRecordList = super.findByCis(settleProgressRecordDTO, true);
        return BeanTransform.copyProperties(settleProgressRecordList, SettleProgressRecordBO.class);
    }

    @Override
    public SettleProgressRecordBO addManage(SettleProgressRecordTO settleProgressRecordTO) throws SerException {
        SettleProgressRecord settleProgressRecord = BeanTransform.copyProperties(settleProgressRecordTO, SettleProgressRecord.class, true);
        settleProgressRecord.setCreateTime(LocalDateTime.now());
        super.save(settleProgressRecord);
        return BeanTransform.copyProperties(settleProgressRecord, SettleProgressRecordBO.class);
    }

    @Override
    public SettleProgressRecordBO editManage(SettleProgressRecordTO settleProgressRecordTO) throws SerException {
        SettleProgressRecord settleProgressRecord = super.findById(settleProgressRecordTO.getId());
        LocalDateTime dateTime = settleProgressRecord.getCreateTime();
        settleProgressRecord = BeanTransform.copyProperties(settleProgressRecordTO, SettleProgressRecord.class, true);
        settleProgressRecord.setCreateTime(dateTime);
        settleProgressRecord.setModifyTime(LocalDateTime.now());
        super.update(settleProgressRecord);
        return BeanTransform.copyProperties(settleProgressRecord, SettleProgressRecordBO.class);
    }

    @Override
    public void deleteManage(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void checkAnalysis(String id, String moneyModule,String moneyModuleOpinion) throws SerException {
        SettleProgressRecord settleProgressRecord = super.findById(id);
        settleProgressRecord.setMoneyModule(moneyModule);
        settleProgressRecord.setMoneyModuleOpinion(moneyModuleOpinion);
        settleProgressRecord.setModifyTime(LocalDateTime.now());
        super.update(settleProgressRecord);
    }

    @Override
    public void confirm(String id,String generalManager, String approvalExam, Boolean confirm) throws SerException {
        SettleProgressRecord settleProgressRecord = super.findById(id);
        settleProgressRecord.setGeneralManager(generalManager);
        settleProgressRecord.setApprovalExam(approvalExam);
        settleProgressRecord.setConfirm(confirm);
        settleProgressRecord.setModifyTime(LocalDateTime.now());
        super.update(settleProgressRecord);
    }
}