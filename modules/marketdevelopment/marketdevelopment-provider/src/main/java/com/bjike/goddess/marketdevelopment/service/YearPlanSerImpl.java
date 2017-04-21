package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.YearPlanBO;
import com.bjike.goddess.marketdevelopment.bo.YearPlanChoiceBO;
import com.bjike.goddess.marketdevelopment.dto.YearPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.YearPlan;
import com.bjike.goddess.marketdevelopment.to.YearPlanTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                YearPlan entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                super.update(entity);
                return BeanTransform.copyProperties(entity, YearPlanBO.class);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }
        } else
            throw new SerException("数据ID不能为空");
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

    @Override
    public List<YearPlanChoiceBO> getChoice() throws SerException {
        YearPlanDTO dto = new YearPlanDTO();
        dto.getConditions().add(Restrict.eq("year", LocalDate.now().getYear()));
        List<YearPlanChoiceBO> list = new ArrayList<>(0);
        String format = "%d年 业务类型:%s 科目:%s";
        for (YearPlan entity : super.findByCis(dto)) {
            YearPlanChoiceBO choiceBO = new YearPlanChoiceBO();
            choiceBO.setId(entity.getId());
            choiceBO.setShowValue(String.format(format, entity.getYear(), entity.getType(), entity.getCourse()));
            list.add(choiceBO);
        }
        return list;
    }
}