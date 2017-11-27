package com.bjike.goddess.projectprocing.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectprocing.bo.SettlementProcessBO;
import com.bjike.goddess.projectprocing.dto.SettlementProcessDTO;
import com.bjike.goddess.projectprocing.entity.SettlementProcess;
import com.bjike.goddess.projectprocing.to.SettlementProcessTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 结算流程存储记录业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-18 10:16 ]
 * @Description: [ 结算流程存储记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectprocingSerCache")
@Service
public class SettlementProcessSerImpl extends ServiceImpl<SettlementProcess, SettlementProcessDTO> implements SettlementProcessSer {
    @Override
    public Long countSetProcess(SettlementProcessDTO settlementProcessDTO) throws SerException {
        searchCondi(settlementProcessDTO);
        Long count = super.count(settlementProcessDTO);
        return count;
    }

    @Override
    public SettlementProcessBO getOneById(String id) throws SerException {
        SettlementProcess settlementProcess = super.findById(id);
        return BeanTransform.copyProperties(settlementProcess, SettlementProcessBO.class);
    }

    @Override
    public List<SettlementProcessBO> listSetProcess(SettlementProcessDTO settlementProcessDTO) throws SerException {
        searchCondi(settlementProcessDTO);
        List<SettlementProcess> settlementProcessList = super.findByCis(settlementProcessDTO, true);
        return BeanTransform.copyProperties(settlementProcessList, SettlementProcessBO.class);
    }

    public void searchCondi(SettlementProcessDTO settlementProcessDTO) throws SerException {
        if (StringUtils.isNotBlank(settlementProcessDTO.getOutUnit())) {
            settlementProcessDTO.getConditions().add(Restrict.eq("outUnit", settlementProcessDTO.getOutUnit()));
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettlementProcessBO addSetProcess(SettlementProcessTO settlementProcessTO) throws SerException {
        SettlementProcess settlementProcess = BeanTransform.copyProperties(settlementProcessTO, SettlementProcess.class, true);
        settlementProcess.setUpdateDate(LocalDate.now());
        super.save(settlementProcess);
        return BeanTransform.copyProperties(settlementProcess, SettlementProcessBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SettlementProcessBO editSetProcess(SettlementProcessTO settlementProcessTO) throws SerException {
        SettlementProcess settlementProcess = super.findById(settlementProcessTO.getId());
        LocalDateTime dateTime = settlementProcess.getCreateTime();
        settlementProcess = BeanTransform.copyProperties(settlementProcessTO, SettlementProcess.class, true);
        settlementProcess.setCreateTime(dateTime);
        settlementProcess.setModifyTime(LocalDateTime.now());
        settlementProcess.setUpdateDate(LocalDate.now());
        super.update(settlementProcess);
        return BeanTransform.copyProperties(settlementProcess, SettlementProcessBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteSetProcess(String id) throws SerException {
        super.remove(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void updateSettProceAttach(String id) throws SerException {
        SettlementProcess settlementProcess = super.findById(id);
        if (settlementProcess != null) {
            if (!settlementProcess.getSettProceAttach()) {
                settlementProcess.setSettProceAttach(true);
                super.update(settlementProcess);
            }
        }
    }

    @Override
    public List<String> findOutUnit() throws SerException {
        List<SettlementProcess> settlementProcessList = super.findAll();
        if(CollectionUtils.isEmpty(settlementProcessList)){
            return Collections.emptyList();
        }
        return settlementProcessList.stream().distinct().map(SettlementProcess::getOutUnit).collect(Collectors.toList());
    }
}