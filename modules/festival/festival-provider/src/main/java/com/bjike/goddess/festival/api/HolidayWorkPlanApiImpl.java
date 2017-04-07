package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.HolidayWorkPlanBO;
import com.bjike.goddess.festival.dto.HolidayWorkPlanDTO;
import com.bjike.goddess.festival.service.HolidayWorkPlanSer;
import com.bjike.goddess.festival.to.HolidayWorkPlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 节假日工作安排业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:08 ]
 * @Description: [ 节假日工作安排业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("holidayWorkPlanApiImpl")
public class HolidayWorkPlanApiImpl implements HolidayWorkPlanAPI {

    @Autowired
    private HolidayWorkPlanSer holidayWorkPlanSer;

    @Override
    public Long countHolidayWorkPlan(HolidayWorkPlanDTO holidayWorkPlanDTO) throws SerException {
        return holidayWorkPlanSer.countHolidayWorkPlan(holidayWorkPlanDTO);
    }

    @Override
    public List<HolidayWorkPlanBO> listHolidayWorkPlan(HolidayWorkPlanDTO holidayWorkPlanDTO) throws SerException {
        return holidayWorkPlanSer.listHolidayWorkPlan(holidayWorkPlanDTO);
    }

    @Override
    public HolidayWorkPlanBO addHolidayWorkPlan(HolidayWorkPlanTO holidayWorkPlanTO) throws SerException {
        return holidayWorkPlanSer.addHolidayWorkPlan(holidayWorkPlanTO);
    }

    @Override
    public HolidayWorkPlanBO editHolidayWorkPlan(HolidayWorkPlanTO holidayWorkPlanTO) throws SerException {
        return holidayWorkPlanSer.editHolidayWorkPlan(holidayWorkPlanTO);
    }

    @Override
    public void deleteHolidayWorkPlan(String id) throws SerException {
        holidayWorkPlanSer.deleteHolidayWorkPlan(id);
    }

    @Override
    public List<HolidayWorkPlanBO> getHolidayWorkPlan(String holidayProgrammeId) throws SerException {
        return holidayWorkPlanSer.getHolidayWorkPlan(holidayProgrammeId);
    }
}