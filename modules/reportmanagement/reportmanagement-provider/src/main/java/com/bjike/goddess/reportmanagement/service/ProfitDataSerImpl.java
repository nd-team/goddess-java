package com.bjike.goddess.reportmanagement.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.reportmanagement.dto.CashFlowDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitDTO;
import com.bjike.goddess.reportmanagement.dto.ProfitDataDTO;
import com.bjike.goddess.reportmanagement.entity.CashFlow;
import com.bjike.goddess.reportmanagement.entity.ProfitData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: [caiwenxian]
 * @Date: [2018-03-26 14:18]
 * @Description: [ ]
 * @Version: [1.0.0]
 */

@CacheConfig(cacheNames ="reportmanagementSerCache")
@Service
public class ProfitDataSerImpl extends ServiceImpl<ProfitData, ProfitDataDTO> implements ProfitDataSer {

    @Override
    public void save(List<ProfitData> profitDataList) throws SerException {
        for (ProfitData data : profitDataList) {
            if (null == data.getProject()) {
                continue;
            }
            ProfitDataDTO dto = new ProfitDataDTO();
            dto.getConditions().add(Restrict.eq("startTime", data.getStartTime()));
            dto.getConditions().add(Restrict.eq("endTime", data.getEndTime()));
            dto.getConditions().add(Restrict.eq("project", data.getProject()));
            List<ProfitData> list = super.findByCis(dto);
            if (list == null || list.size() < 1) {
                super.save(data);
                continue;
            }
            ProfitData entity = list.get(0);
            if (entity.getMonthMoney().equals(data.getMonthMoney()) && entity.getYearMoney().equals(data.getYearMoney())) {
                continue;
            }
            entity.setMonthMoney(data.getMonthMoney());
            entity.setYearMoney(data.getYearMoney());
            entity.setModifyTime(LocalDateTime.now());
            super.update(entity);

        }
    }
}
