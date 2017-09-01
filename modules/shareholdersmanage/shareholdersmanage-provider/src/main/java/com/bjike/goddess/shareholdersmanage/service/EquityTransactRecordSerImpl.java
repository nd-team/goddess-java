package com.bjike.goddess.shareholdersmanage.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordBO;
import com.bjike.goddess.shareholdersmanage.bo.EquityTransactRecordDetailBO;
import com.bjike.goddess.shareholdersmanage.bo.ShareAndTypeBO;
import com.bjike.goddess.shareholdersmanage.dto.EquityTransactRecordDTO;
import com.bjike.goddess.shareholdersmanage.entity.EquityTransactRecord;
import com.bjike.goddess.shareholdersmanage.to.EquityTransactRecordTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 股权交易记录业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-08-21 04:05 ]
 * @Description: [ 股权交易记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "shareholdersmanageSerCache")
@Service
public class EquityTransactRecordSerImpl extends ServiceImpl<EquityTransactRecord, EquityTransactRecordDTO> implements EquityTransactRecordSer {
    @Override
    public Long countTrans(EquityTransactRecordDTO equityTransactRecordDTO) throws SerException {
        Long count = super.count(equityTransactRecordDTO);
        return count;
    }

    @Override
    public EquityTransactRecordBO getOne(String id) throws SerException {
        EquityTransactRecord equityTransactRecord = super.findById(id);
        return BeanTransform.copyProperties(equityTransactRecord, EquityTransactRecordBO.class);
    }

    @Override
    public List<EquityTransactRecordBO> findList(EquityTransactRecordDTO equityTransactRecordDTO) throws SerException {
        equityTransactRecordDTO.getSorts().add("modifyTime=desc");
        List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
        return BeanTransform.copyProperties(equityTransactRecords, EquityTransactRecordBO.class);
    }

    @Override
    public Double transTotalAmount(String equityType) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("equityType", equityType));
        List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
        Double totalAmount = 0d;
        if (equityTransactRecords != null && equityTransactRecords.size() > 0) {
            totalAmount = equityTransactRecords.stream().mapToDouble(s -> s.getAmount()).sum();//总出资额
        }
        return totalAmount;
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateTrans(EquityTransactRecordTO equityTransactRecordTO) throws SerException {
        EquityTransactRecord equityTransactRecord = super.findById(equityTransactRecordTO.getId());
        if (equityTransactRecord == null) {
            throw new SerException("该对象不存在");
        }
        BeanTransform.copyProperties(equityTransactRecordTO, equityTransactRecord, true);
        equityTransactRecord.setModifyTime(LocalDateTime.now());
        super.update(equityTransactRecord);
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateTransList() throws SerException {
        for (String equityType : findEquityType()){
            EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
            equityTransactRecordDTO.getConditions().add(Restrict.eq("equityType", equityType));
            List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
            Double totalAmount = 0d;
            List<EquityTransactRecord> list = new ArrayList<>();
            totalAmount = equityTransactRecords.stream().mapToDouble(s -> s.getAmount()).sum();//总出资额
            for (EquityTransactRecord equityTransactRecord : equityTransactRecords){
                if (totalAmount != 0) {
                    equityTransactRecord.setPercentage((equityTransactRecord.getAmount() / totalAmount)*100);
                    equityTransactRecord.setModifyTime(LocalDateTime.now());
                } else {
                    equityTransactRecord.setPercentage(0d);
                    equityTransactRecord.setModifyTime(LocalDateTime.now());
                }
                list.add(equityTransactRecord);
            }
            super.update(list);
        }
    }
    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteTransact(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteByName(String shareholderName) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
        super.remove(equityTransactRecords);
    }

    @Override
    public EquityTransactRecordBO getByName(String shareholderName) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("shareholderName",shareholderName));
        EquityTransactRecord equityTransactRecord = super.findOne(equityTransactRecordDTO);
        return BeanTransform.copyProperties(equityTransactRecord,EquityTransactRecordBO.class);
    }

    @Override
    public List<EquityTransactRecordBO> summationTrans() throws SerException {
        List<EquityTransactRecordBO> equityTransactRecordBOS = new ArrayList<>();
        List<EquityTransactRecord> equityTransactRecords = super.findAll();
        equityTransactRecordBOS = BeanTransform.copyProperties(equityTransactRecords, EquityTransactRecordBO.class);
        EquityTransactRecordBO equityTransactRecordBO = new EquityTransactRecordBO();
        Integer holdNum = equityTransactRecordBOS.stream().mapToInt(e-> e.getHoldNum()).sum();
        Double totalAmount = equityTransactRecordBOS.stream().mapToDouble(e->e.getAmount()).sum();
        equityTransactRecordBO.setShareholderName("合计");
        equityTransactRecordBO.setHoldNum(holdNum);
        equityTransactRecordBO.setAmount(totalAmount);
        equityTransactRecordBOS.add(equityTransactRecordBO);
        return equityTransactRecordBOS;
    }
    @Override
    public List<String> findEquityType() throws SerException {
        List<EquityTransactRecord> list = super.findAll();
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (EquityTransactRecord model : list) {
            String equityType = model.getEquityType();
            if (StringUtils.isNotBlank(model.getEquityType())) {
                set.add(equityType);
            }
        }
        return new ArrayList<>(set);
    }

    public void reinstate(EquityTransactRecordBO equityTransactRecordBO,EquityTransactRecordDetailBO equityTransactRecordDetailBO)throws SerException{
        EquityTransactRecordTO equityTransactRecordTO = new EquityTransactRecordTO();
        equityTransactRecordTO.setId(equityTransactRecordBO.getId());
        equityTransactRecordTO.setHoldNum(equityTransactRecordBO.getHoldNum()-equityTransactRecordDetailBO.getHoldNum());
        equityTransactRecordTO.setAmount(equityTransactRecordBO.getAmount()-equityTransactRecordDetailBO.getAmount());
        updateTrans(equityTransactRecordTO);
    }

    @Override
    public List<EquityTransactRecordBO> getByEquityType(String equityType) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("equityType",equityType));
        List<EquityTransactRecord> equityTransactRecords = super.findByCis(equityTransactRecordDTO);
        return BeanTransform.copyProperties(equityTransactRecords,EquityTransactRecordBO.class);
    }

    @Override
    public List<String> getNameByEquityType(String equityType) throws SerException {
        EquityTransactRecordDTO equityTransactRecordDTO = new EquityTransactRecordDTO();
        equityTransactRecordDTO.getConditions().add(Restrict.eq("equityType",equityType));
        List<EquityTransactRecord> list = super.findByCis(equityTransactRecordDTO);
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        }
        Set<String> set = new HashSet<>();
        for (EquityTransactRecord model : list) {
            String name = model.getShareholderName();
            if (StringUtils.isNotBlank(model.getShareholderName())) {
                set.add(name);
            }
        }
        return new ArrayList<>(set);
    }

    @Override
    public List<ShareAndTypeBO> getNameAndType() throws SerException {
        List<EquityTransactRecord> list = super.findAll();
        return BeanTransform.copyProperties(list,ShareAndTypeBO.class);
    }
}