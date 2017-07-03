package com.bjike.goddess.festival.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.festival.bo.HolidayWorkPlanBO;
import com.bjike.goddess.festival.dto.HolidayWorkPlanDTO;
import com.bjike.goddess.festival.to.HolidayWorkPlanTO;

import java.util.List;

/**
 * 节假日工作安排业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-01 09:08 ]
 * @Description: [ 节假日工作安排业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface HolidayWorkPlanAPI {

    /**
     * 节假日工作安排列表总条数
     *
     */
    default Long countHolidayWorkPlan(HolidayWorkPlanDTO holidayWorkPlanDTO) throws SerException {
        return null;
    }
    /**
     * 节假日工作安排列表
     * @return class HolidayWorkPlanBO
     */
    default List<HolidayWorkPlanBO> listHolidayWorkPlan(HolidayWorkPlanDTO holidayWorkPlanDTO) throws SerException {return null;}
    /**
     *  添加
     * @param holidayWorkPlanTO 节假日工作安排信息
     * @return class HolidayWorkPlanBO
     */
    default HolidayWorkPlanBO addHolidayWorkPlan(HolidayWorkPlanTO holidayWorkPlanTO) throws SerException { return null;}

    /**
     *  编辑
     * @param holidayWorkPlanTO 节假日工作安排信息
     * @return class HolidayWorkPlanBO
     */
    default HolidayWorkPlanBO editHolidayWorkPlan(HolidayWorkPlanTO holidayWorkPlanTO) throws SerException { return null;}

    /**
     * 删除
     * @param id id
     */
    default void deleteHolidayWorkPlan(String id ) throws SerException {return;};

    /**
     * 根据节日方案查询节日工作安排
     * @return class HolidayWorkPlanBO
     */
    default List<HolidayWorkPlanBO> getHolidayWorkPlan(String holidayProgrammeId) throws SerException {return null;}


}