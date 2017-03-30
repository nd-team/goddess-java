package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.DayPlanBO;
import com.bjike.goddess.marketdevelopment.dto.DayPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.DayPlan;
import com.bjike.goddess.marketdevelopment.to.DayPlanTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 天计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:08 ]
 * @Description: [ 天计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class DayPlanSerImpl extends ServiceImpl<DayPlan, DayPlanDTO> implements DayPlanSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DayPlanBO save(DayPlanTO to) throws SerException {
        DayPlan entity = BeanTransform.copyProperties(to, DayPlan.class, true);
        super.save(entity);
        return BeanTransform.copyProperties(entity, DayPlanBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DayPlanBO update(DayPlanTO to) throws SerException {
        DayPlan entity = BeanTransform.copyProperties(to, DayPlan.class, true);
        super.update(entity);
        return BeanTransform.copyProperties(entity, DayPlanBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DayPlanBO delete(DayPlanTO to) throws SerException {
        DayPlan entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, DayPlanBO.class, true);
    }

    @Override
    public List<DayPlanBO> findByDate(String start, String end) throws SerException {
        LocalDate startDate = LocalDate.parse(start), endDate = LocalDate.parse(end);
        DayPlanDTO dto = new DayPlanDTO();
        dto.getConditions().add(Restrict.gt_eq("time", startDate));
        dto.getConditions().add(Restrict.lt_eq("time", endDate));
        List<DayPlan> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DayPlanBO.class);
    }

    @Override
    public List<DayPlanBO> findByDate(String date) throws SerException {
        LocalDate time = LocalDate.parse(date);
        DayPlanDTO dto = new DayPlanDTO();
        dto.getConditions().add(Restrict.eq("time", time));
        List<DayPlan> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, DayPlanBO.class);
    }
}