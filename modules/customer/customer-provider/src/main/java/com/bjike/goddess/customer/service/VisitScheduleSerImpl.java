package com.bjike.goddess.customer.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.customer.bo.VisitScheduleBO;
import com.bjike.goddess.customer.dto.VisitScheduleDTO;
import com.bjike.goddess.customer.entity.VisitSchedule;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * 拜访日程表业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-03 03:13 ]
 * @Description: [ 拜访日程表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "customerSerCache")
@Service
public class VisitScheduleSerImpl extends ServiceImpl<VisitSchedule, VisitScheduleDTO> implements VisitScheduleSer {
    @Override
    public VisitScheduleBO findVisit(VisitScheduleDTO visitScheduleDTO) throws SerException {
        if(visitScheduleDTO.getYear()==null || visitScheduleDTO.getMonth()==null||visitScheduleDTO.getWeek()==null ){
            visitScheduleDTO.setYear(LocalDateTime.now().getYear());
            visitScheduleDTO.setMonth(LocalDateTime.now().getMonthValue());
            Calendar c = Calendar.getInstance();
            int week = c.get(Calendar.WEEK_OF_MONTH);//获取是本月的第几周
            visitScheduleDTO.setWeek(week);
        }
        visitScheduleDTO.getConditions().add(Restrict.eq("year", visitScheduleDTO.getYear()));
        visitScheduleDTO.getConditions().add(Restrict.eq("month", visitScheduleDTO.getMonth()));
        visitScheduleDTO.getConditions().add(Restrict.eq("week", visitScheduleDTO.getWeek()));
        VisitSchedule visitSchedule = super.findOne(visitScheduleDTO);
        return BeanTransform.copyProperties(visitSchedule,VisitScheduleBO.class);
    }

}