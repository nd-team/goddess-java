package com.bjike.goddess.moneyside.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.moneyside.bo.CollectBO;
import com.bjike.goddess.moneyside.bo.FundEntryBO;
import com.bjike.goddess.moneyside.dto.FundEntryDTO;
import com.bjike.goddess.moneyside.entity.FundEntry;
import com.bjike.goddess.moneyside.entity.FundEntryConfirmed;
import com.bjike.goddess.moneyside.entity.FundEntryWrongRecord;
import com.bjike.goddess.moneyside.enums.PassStatus;
import com.bjike.goddess.moneyside.to.CollectTO;
import com.bjike.goddess.moneyside.to.FundEntryTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 资金进入申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:05 ]
 * @Description: [ 资金进入申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "moneysideSerCache")
@Service
public class FundEntrySerImpl extends ServiceImpl<FundEntry, FundEntryDTO> implements FundEntrySer {

    @Autowired
    private FundEntryWrongRecordSer fundEntryWrongRecordSer;
    @Autowired
    private FundEntryConfirmedSer fundEntryConfirmedSer;

    /**
     * 核对时间格式(年月日)
     */
    private void checkDate(FundEntryTO fundEntryTO) throws SerException {
        try {
            DateUtil.parseDate(fundEntryTO.getFundEntryTime());
        } catch (Exception e) {
            throw new SerException("输入的日期格式有误");
        }
    }

    @Override
    public Long countFundEntry(FundEntryDTO fundEntryDTO) throws SerException {
        Long count = super.count(fundEntryDTO);
        return count;
    }

    @Override
    public FundEntryBO getOne(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        FundEntry fundEntry = super.findById(id);
        return BeanTransform.copyProperties(fundEntry, FundEntryBO.class);
    }

    @Override
    public List<FundEntryBO> findListFundEntry(FundEntryDTO fundEntryDTO) throws SerException {
        List<FundEntry> fundEntries = super.findByPage(fundEntryDTO);
        List<FundEntryBO> fundEntryBOS = BeanTransform.copyProperties(fundEntries, FundEntryBO.class);
        return fundEntryBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FundEntryBO insertFundEntry(FundEntryTO fundEntryTO) throws SerException {
        checkDate(fundEntryTO);
        FundEntry fundEntry = BeanTransform.copyProperties(fundEntryTO, FundEntry.class, true);
        fundEntry.setCreateTime(LocalDateTime.now());
        super.save(fundEntry);
        return BeanTransform.copyProperties(fundEntry, FundEntryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FundEntryBO editFundEntry(FundEntryTO fundEntryTO) throws SerException {
        if (StringUtils.isBlank(fundEntryTO.getId())) {
            throw new SerException("id不能为空");
        }
        FundEntry fundEntry = super.findById(fundEntryTO.getId());
        checkDate(fundEntryTO);
        BeanUtils.copyProperties(fundEntryTO, fundEntry);
        fundEntry.setModifyTime(LocalDateTime.now());
        super.update(fundEntry);
        return BeanTransform.copyProperties(fundEntry, FundEntryBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void removeFundEntry(String id) throws SerException {
        if (StringUtils.isBlank(id)) {
            throw new SerException("id不能为空");
        }
        super.remove(id);
    }

    @Override
    public FundEntryBO audit(FundEntryTO fundEntryTO) throws SerException {
        FundEntry fundEntry = super.findById(fundEntryTO.getId());
        BeanTransform.copyProperties(fundEntryTO, fundEntry, true);
        fundEntry.setModifyTime(LocalDateTime.now());
        fundEntry.setConfirmPeople(fundEntryTO.getConfirmPeople());
        fundEntry.setConfirmSituation(fundEntryTO.getConfirmSituation());
        //是否通过
        if (PassStatus.YES.equals(fundEntry.getPass())) {
            FundEntryConfirmed fundEntryConfirmed = new FundEntryConfirmed();
            BeanUtils.copyProperties(fundEntry, fundEntryConfirmed);
            fundEntryConfirmed.setPass(fundEntryTO.getPass());
            fundEntryConfirmedSer.save(fundEntryConfirmed);
            //BeanUtils.copyProperties(fundEntryConfirmed, FundEntryConfirmedBO.class);
        } else if (PassStatus.NO.equals(fundEntry.getPass())) {
            FundEntryWrongRecord fundEntryWrongRecord = new FundEntryWrongRecord();
            BeanUtils.copyProperties(fundEntry, fundEntryWrongRecord);
            fundEntryWrongRecord.setPass(fundEntryTO.getPass());
            fundEntryWrongRecordSer.save(fundEntryWrongRecord);
        }
        super.update(fundEntry);
        return BeanTransform.copyProperties(fundEntry, FundEntryBO.class);
    }

    @Override
    public List<CollectBO> collect(CollectTO to) throws SerException {
        FundEntryDTO dto = new FundEntryDTO();
        String start = to.getStartTime();
        String end = to.getEndTime();
        if (StringUtils.isNotBlank(start) && StringUtils.isNotBlank(end)) {
            String[] condition = new String[]{start, end};
            dto.getConditions().add(Restrict.between("fundEntryTime", condition));
        }

        if (null != to.getApplyPeople()) {
            dto.getConditions().add(Restrict.in("applyPeople", to.getApplyPeople()));
        }
        if (null != to.getAccessToFund()) {
            dto.getConditions().add(Restrict.in("accessToFund", to.getAccessToFund()));
        }
        return fundCollect(dto);
    }

    public List<CollectBO> fundCollect(FundEntryDTO dto) throws SerException {
        List<FundEntry> list = super.findByCis(dto);
        List<CollectBO> boList = new ArrayList<>();
        for (FundEntry model : list) {
            CollectBO bo = new CollectBO();
            bo.setApplyPeople(model.getApplyPeople());
            bo.setAccessToFund(model.getAccessToFund());
            bo.setFundEntryTime(DateUtil.dateToString(model.getFundEntryTime()));
            bo.setMoney(model.getMoney());
            boList.add(bo);

        }
        Double totalMoney = 0.0;
        if (list != null) {
            totalMoney = list.stream().filter(p -> p.getMoney() != null).mapToDouble(p -> p.getMoney()).sum();

            CollectBO totalBO = new CollectBO("合计", "", "", totalMoney);
            boList.add(totalBO);
        } else {
            CollectBO totalBO = new CollectBO("合计", "", "", totalMoney);
            boList.add(totalBO);
        }
        return boList;
    }

}