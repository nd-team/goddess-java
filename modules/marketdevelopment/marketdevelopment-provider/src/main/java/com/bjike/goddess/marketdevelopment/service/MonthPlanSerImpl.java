package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.marketdevelopment.bo.MonthPlanBO;
import com.bjike.goddess.marketdevelopment.bo.YearPlanBO;
import com.bjike.goddess.marketdevelopment.dto.MonthPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.MonthPlan;
import com.bjike.goddess.marketdevelopment.to.MonthPlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 月计划业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:41 ]
 * @Description: [ 月计划业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "marketdevelopmentSerCache")
@Service
public class MonthPlanSerImpl extends ServiceImpl<MonthPlan, MonthPlanDTO> implements MonthPlanSer {

    @Autowired
    private YearPlanSer yearPlanSer;

    /**
     * 转换月计划传输对象
     *
     * @param entity 月计划实体
     * @return
     */
    private MonthPlanBO transformBO(MonthPlan entity) {
        MonthPlanBO bo = BeanTransform.copyProperties(entity, MonthPlanBO.class);
        bo.setYear_id(entity.getYear().getId());
        bo.setYearNumber(entity.getYear().getYear());
        bo.setType(entity.getYear().getType());
        bo.setWorkloadWeight(entity.getYear().getWorkloadWeight());
        bo.setCourse(entity.getYear().getCourse());
        bo.setDevelopment(entity.getYear().getDevelopment());
        bo.setBusinessAccounted(entity.getYear().getBusinessAccounted());
        bo.setYearCourseAccounted(entity.getYear().getCourseAccounted());
        bo.setYearQuota(entity.getYear().getQuota());
        return bo;
    }

    /**
     * 转换月计划传输对象集合
     *
     * @param list 月计划实体集合
     * @return
     */
    private List<MonthPlanBO> transformBOList(List<MonthPlan> list) {
        List<MonthPlanBO> bos = new ArrayList<>(list.size());
        for (MonthPlan entity : list)
            bos.add(this.transformBO(entity));
        return bos;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MonthPlanBO save(MonthPlanTO to) throws SerException {
        MonthPlan entity = BeanTransform.copyProperties(to, MonthPlan.class);
        entity.setYear(yearPlanSer.findById(to.getYear_id()));
        entity.setTotal(to.getQuota() + entity.getYear().getQuota() * entity.getAccounted());
        super.save(entity);
        return this.transformBO(entity);
    }


    @Transactional(rollbackFor = SerException.class)
    @Override
    public MonthPlanBO update(MonthPlanTO to) throws SerException {
        MonthPlan entity = BeanTransform.copyProperties(to, MonthPlan.class);
        entity.setYear(yearPlanSer.findById(to.getYear_id()));
        entity.setTotal(to.getQuota() + entity.getYear().getQuota() * entity.getAccounted() / 100);
        super.update(entity);
        return this.transformBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public MonthPlanBO delete(MonthPlanTO to) throws SerException {
        MonthPlan entity = super.findById(to.getId());
        super.remove(entity);
        return this.transformBO(entity);
    }

    @Override
    public List<MonthPlanBO> findByYearID(String year_id) throws SerException {
        MonthPlanDTO dto = new MonthPlanDTO();
        dto.getConditions().add(Restrict.eq("year.id", year_id));
        List<MonthPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }

    @Override
    public List<MonthPlanBO> findByYear(Integer year) throws SerException {
        List<String> yearPlanIdList = yearPlanSer.findByYear(year).stream().map(YearPlanBO::getId).collect(Collectors.toList());
        MonthPlanDTO dto = new MonthPlanDTO();
        dto.getConditions().add(Restrict.eq("year.id", yearPlanIdList));
        List<MonthPlan> list = super.findByCis(dto);
        return this.transformBOList(list);
    }
}