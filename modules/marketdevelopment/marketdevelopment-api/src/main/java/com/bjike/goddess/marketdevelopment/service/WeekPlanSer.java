package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.WeekPlanBO;
import com.bjike.goddess.marketdevelopment.dto.WeekPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.WeekPlan;
import com.bjike.goddess.marketdevelopment.to.WeekPlanTO;

import java.util.List;

/**
 * 周计划业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:49 ]
 * @Description: [ 周计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface WeekPlanSer extends Ser<WeekPlan, WeekPlanDTO> {

    /**
     * 保存周计划数据
     *
     * @param to 周计划传输对象
     * @return
     * @throws SerException
     */
    default WeekPlanBO save(WeekPlanTO to) throws SerException {
        return null;
    }

    /**
     * 修改周计划数据
     *
     * @param to 周计划传输对象
     * @return
     * @throws SerException
     */
    default WeekPlanBO update(WeekPlanTO to) throws SerException {
        return null;
    }

    /**
     * 删除周计划数据
     *
     * @param to 周计划传输对象
     * @return
     * @throws SerException
     */
    default WeekPlanBO delete(WeekPlanTO to) throws SerException {
        return null;
    }

    /**
     * 根据月计划ID查询周计划数据
     *
     * @param month_id 月计划ID
     * @return
     * @throws SerException
     */
    default List<WeekPlanBO> findByMonth(String month_id) throws SerException {
        return null;
    }

    /**
     * 根据时间范围查询周计划数据
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     * @throws SerException
     */
    default List<WeekPlanBO> findByDate(String startDate, String endDate) throws SerException {
        return null;
    }


}