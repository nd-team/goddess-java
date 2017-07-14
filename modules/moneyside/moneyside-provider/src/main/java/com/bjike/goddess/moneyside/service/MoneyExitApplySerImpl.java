package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyDTO;
import com.bjike.goddess.moneyside.entity.MoneyExitApply;
import com.bjike.goddess.moneyside.entity.MoneyExitApplyConfirmed;
import com.bjike.goddess.moneyside.entity.MoneyExitApplyWrongRecord;
import com.bjike.goddess.moneyside.enums.PassStatus;
import com.bjike.goddess.moneyside.to.MoneyExitApplyTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 资金退出申请表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-07 10:37 ]
 * @Description: [ 资金退出申请表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class MoneyExitApplySerImpl extends ServiceImpl<MoneyExitApply, MoneyExitApplyDTO> implements MoneyExitApplySer {
    @Autowired
    private MoneyExitApplyConfirmedSer moneyExitApplyConfirmedSer;
    @Autowired
    private MoneyExitApplyWrongRecordSer moneyExitApplyWrongRecordSer;
    /**
     * 核对时间格式(年月日)
     */
    private void checkDate(MoneyExitApplyTO moneyExitApplyTO) throws SerException{
        try {
            DateUtil.parseDate(moneyExitApplyTO.getStartProjectTime());
            DateUtil.parseDate(moneyExitApplyTO.getEndProjectTime());
            DateUtil.parseDate(moneyExitApplyTO.getForecastArriveTime());
            DateUtil.parseDate(moneyExitApplyTO.getExitTime());
        }catch (Exception e){
            throw new SerException("输入的时间格式有误");
        }
    }
    @Override
    public Long countMoneyExitApply(MoneyExitApplyDTO moneyExitApplyDTO) throws SerException {
        Long count = super.count(moneyExitApplyDTO);
        return count;
    }

    @Override
    public MoneyExitApplyBO getOne(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        MoneyExitApply moneyExitApply = super.findById(id);
        return BeanTransform.copyProperties(moneyExitApply, MoneyExitApplyBO.class);
    }

    @Override
    public List<MoneyExitApplyBO> findListMoneyExitApply(MoneyExitApplyDTO moneyExitApplyDTO) throws SerException {
        List<MoneyExitApply> moneyExitApplies = super.findByCis(moneyExitApplyDTO);
        List<MoneyExitApplyBO> moneyExitApplyBOS = BeanTransform.copyProperties(moneyExitApplies, MoneyExitApplyBO.class);
        return moneyExitApplyBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyExitApplyBO insertMoneyExitApply(MoneyExitApplyTO moneyExitApplyTO) throws SerException {
        checkDate(moneyExitApplyTO);
        MoneyExitApply moneyExitApply = BeanTransform.copyProperties(moneyExitApplyTO, MoneyExitApply.class, true);
        moneyExitApply.setCreateTime(LocalDateTime.now());
        //剩余金额（累计投资金额-退出金额）
        moneyExitApply.setResidueMoney(moneyExitApplyTO.getAccumulativeInvestMoney()-moneyExitApplyTO.getExitMoney());
        super.save(moneyExitApply);
        return BeanTransform.copyProperties(moneyExitApply, MoneyExitApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MoneyExitApplyBO editMoneyExitApply(MoneyExitApplyTO moneyExitApplyTO) throws SerException {
        if(StringUtils.isBlank(moneyExitApplyTO.getId())){
            throw new SerException("id不能为空");
        }
        MoneyExitApply moneyExitApply = super.findById(moneyExitApplyTO.getId());
        checkDate(moneyExitApplyTO);
        BeanUtils.copyProperties(moneyExitApplyTO,moneyExitApply);
        moneyExitApply.setModifyTime(LocalDateTime.now());
        //剩余金额（累计投资金额-退出金额）
        moneyExitApply.setResidueMoney(moneyExitApplyTO.getAccumulativeInvestMoney()-moneyExitApplyTO.getExitMoney());
        super.update(moneyExitApply);
        return BeanTransform.copyProperties(moneyExitApply,MoneyExitApplyBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeMoneyExitApply(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }
    @Override
    public MoneyExitApplyBO audit(MoneyExitApplyTO moneyExitApplyTO) throws SerException {
        MoneyExitApply moneyExitApply = super.findById(moneyExitApplyTO.getId());
        BeanUtils.copyProperties(moneyExitApplyTO,moneyExitApply);
        moneyExitApply.setApprover(moneyExitApplyTO.getApprover());
        moneyExitApply.setApproverOpinion(moneyExitApplyTO.getApproverOpinion());
        moneyExitApply.setApproverTime(LocalDate.parse(moneyExitApplyTO.getApproverTime()));
        //是否通过
        if(PassStatus.YES.equals(moneyExitApply.getPass())){
            MoneyExitApplyConfirmed moneyExitApplyConfirmed = new MoneyExitApplyConfirmed();
            BeanUtils.copyProperties(moneyExitApply,moneyExitApplyConfirmed);
            moneyExitApplyConfirmed.setPass(moneyExitApplyTO.getPass());
            moneyExitApplyConfirmedSer.save(moneyExitApplyConfirmed);
        }else if(PassStatus.NO.equals(moneyExitApply.getPass())){
            MoneyExitApplyWrongRecord moneyExitApplyWrongRecord = new MoneyExitApplyWrongRecord();
            BeanUtils.copyProperties(moneyExitApply,moneyExitApplyWrongRecord);
            moneyExitApplyWrongRecord.setPass(moneyExitApplyTO.getPass());
            moneyExitApplyWrongRecordSer.save(moneyExitApplyWrongRecord);
        }
        super.update(moneyExitApply);
        return BeanTransform.copyProperties(moneyExitApply,MoneyExitApplyBO.class);
    }
}