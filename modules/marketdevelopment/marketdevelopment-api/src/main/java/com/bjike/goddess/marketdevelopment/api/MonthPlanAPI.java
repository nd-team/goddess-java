package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.BusinessCourseBO;
import com.bjike.goddess.marketdevelopment.bo.MonthPlanBO;
import com.bjike.goddess.marketdevelopment.dto.MonthPlanDTO;
import com.bjike.goddess.marketdevelopment.to.MonthPlanTO;

import java.util.List;

/**
 * 月计划业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:41 ]
 * @Description: [ 月计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MonthPlanAPI {

    /**
     * 保存月计划数据
     *
     * @param to 月计划传输对象
     * @return
     * @throws SerException
     */
    default MonthPlanBO save(MonthPlanTO to) throws SerException {
        return null;
    }

    /**
     * 修改月计划数据
     *
     * @param to 月计划传输对象
     * @return
     * @throws SerException
     */
    default MonthPlanBO update(MonthPlanTO to) throws SerException {
        return null;
    }

    /**
     * 删除月计划数据
     *
     * @param to 月计划传输书对象
     * @return
     * @throws SerException
     */
    default MonthPlanBO delete(MonthPlanTO to) throws SerException {
        return null;
    }

    /**
     * 根据年计划ID查询月计划数据
     *
     * @param year_id 年计划ID
     * @return
     * @throws SerException
     */
    default List<MonthPlanBO> findByYearID(String year_id) throws SerException {
        return null;
    }

    /**
     * 根据年份查询月计划数据
     *
     * @param year 年份
     * @return
     * @throws SerException
     */
    default List<MonthPlanBO> findByYear(Integer year) throws SerException {
        return null;
    }

    /**
     * 根据id获取月计划
     * @param id 月计划数据id
     * @return
     * @throws SerException
     */
    default MonthPlanBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 月计划数据传输对象
     * @return
     * @throws SerException
     */
    default List<MonthPlanBO> maps(MonthPlanDTO dto) throws SerException {
        return null;
    }

}