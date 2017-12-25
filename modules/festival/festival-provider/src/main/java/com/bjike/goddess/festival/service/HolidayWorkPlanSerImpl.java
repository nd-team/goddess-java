package com.bjike.goddess.festival.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.festival.bo.HolidayWorkPlanBO;
import com.bjike.goddess.festival.dto.HolidayWorkPlanDTO;
import com.bjike.goddess.festival.dto.HolidayWorkPlanDTO;
import com.bjike.goddess.festival.entity.HolidayWorkPlan;
import com.bjike.goddess.festival.entity.HolidayProgramme;
import com.bjike.goddess.festival.entity.HolidayWorkPlan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节假日工作安排业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:08 ]
 * @Description: [ 节假日工作安排业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "festivalSerCache")
@Service
public class HolidayWorkPlanSerImpl extends ServiceImpl<HolidayWorkPlan, HolidayWorkPlanDTO> implements HolidayWorkPlanSer {

    @Override
    public Long countHolidayWorkPlan(HolidayWorkPlanDTO holidayWorkPlanDTO) throws SerException {
        if(StringUtils.isBlank( holidayWorkPlanDTO.getHolidayProgrammeId())){
            throw new SerException("节假日方案id不能为空");
        }
        String holidayId = holidayWorkPlanDTO.getHolidayProgrammeId();
        holidayWorkPlanDTO.getConditions().add(Restrict.eq("holidayProgramme.id",holidayId));
        Long count = super.count( holidayWorkPlanDTO );
        return count;
    }

    @Override
    public List<HolidayWorkPlanBO> listHolidayWorkPlan(HolidayWorkPlanDTO holidayWorkPlanDTO) throws SerException {
        if(StringUtils.isBlank( holidayWorkPlanDTO.getHolidayProgrammeId())){
            throw new SerException("节假日方案id不能为空");
        }
        String holidayId = holidayWorkPlanDTO.getHolidayProgrammeId();
        holidayWorkPlanDTO.getConditions().add(Restrict.eq("holidayProgramme.id",holidayId));
        List<HolidayWorkPlan> holidayWorkPlanList = super.findByCis( holidayWorkPlanDTO ,true);
        return BeanTransform.copyProperties(holidayWorkPlanList,HolidayWorkPlanBO.class);
    }

    @Override
    public List<HolidayWorkPlanBO> getHolidayWorkPlan(String holidayProgrammeId) throws SerException {
        HolidayWorkPlanDTO dto = new HolidayWorkPlanDTO();

        List<HolidayWorkPlan> holidayWorkPlanList = super.findByCis( dto );
        return BeanTransform.copyProperties(holidayWorkPlanList,HolidayWorkPlanBO.class);
    }
}