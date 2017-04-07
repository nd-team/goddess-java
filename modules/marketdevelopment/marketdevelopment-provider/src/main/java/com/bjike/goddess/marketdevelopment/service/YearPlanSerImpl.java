package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.YearPlanBO;
import com.bjike.goddess.marketdevelopment.dto.YearPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.YearPlan;
import com.bjike.goddess.marketdevelopment.to.YearPlanTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * 年计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 05:57 ]
 * @Description: [ 年计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class YearPlanSerImpl extends ServiceImpl<YearPlan, YearPlanDTO> implements YearPlanSer {

    @Transactional(rollbackFor = SerException.class)
    @Override
    public YearPlanBO save(YearPlanTO to) throws SerException {
        YearPlan entity = BeanTransform.copyProperties(to, YearPlan.class);
        super.save(entity);
        return BeanTransform.copyProperties(entity, YearPlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public YearPlanBO update(YearPlanTO to) throws SerException {
        YearPlan entity = BeanTransform.copyProperties(to, YearPlan.class);
        super.update(entity);
        return BeanTransform.copyProperties(entity, YearPlanBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public YearPlanBO delete(YearPlanTO to) throws SerException {
        YearPlan entity = super.findById(to.getId());
        super.remove(entity);
        return BeanTransform.copyProperties(entity, YearPlanBO.class);
    }

    @Override
    public List<YearPlanBO> findThisYear() throws SerException {
        return this.findByYear(LocalDate.now().getYear());
    }

    @Override
    public List<YearPlanBO> findByYear(Integer year) throws SerException {
        YearPlanDTO dto = new YearPlanDTO();
        dto.getConditions().add(Restrict.eq("year", year));
        List<YearPlan> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, YearPlanBO.class);
    }
}