package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.CashFlowDTO;
import com.bjike.goddess.reportmanagement.dto.CashFlowDatumDTO;
import com.bjike.goddess.reportmanagement.entity.CashFlow;
import com.bjike.goddess.reportmanagement.entity.CashFlowDatum;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 补充资料业务实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-22 11:54 ]
 * @Description: [ 补充资料业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "reportmanagementSerCache")
@Service
public class CashFlowDatumSerImpl extends ServiceImpl<CashFlowDatum, CashFlowDatumDTO> implements CashFlowDatumSer {

    @Override
    public CashFlow save(List<CashFlowDatum> cashFlows) throws SerException {

        for (CashFlowDatum cashFlow : cashFlows) {
            if (null == cashFlow.getData()) {
                continue;
            }
            CashFlowDatumDTO dto = new CashFlowDatumDTO();
            dto.getConditions().add(Restrict.eq("startTime", cashFlow.getStartTime()));
            dto.getConditions().add(Restrict.eq("endTime", cashFlow.getEndTime()));
            dto.getConditions().add(Restrict.eq("data", cashFlow.getData()));
            List<CashFlowDatum> list = super.findByCis(dto);
            if (list == null || list.size() < 1) {
                super.save(cashFlow);
                continue;
            }
            CashFlowDatum entity = list.get(0);
            if (entity.getMoney().equals(cashFlow.getMoney())) {
                continue;
            }
            entity.setMoney(cashFlow.getMoney());
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);

        }
        return null;
    }
}