package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.YearPlanBO;
import com.bjike.goddess.marketdevelopment.bo.YearPlanChoiceBO;
import com.bjike.goddess.marketdevelopment.dto.YearPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.YearPlan;
import com.bjike.goddess.marketdevelopment.to.YearPlanTO;

import java.util.List;

/**
 * 年计划业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 05:57 ]
 * @Description: [ 年计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface YearPlanSer extends Ser<YearPlan, YearPlanDTO> {

    /**
     * 保存年计划数据
     *
     * @param to 年计划传输对象
     * @return
     * @throws SerException
     */
    default YearPlanBO save(YearPlanTO to) throws SerException {
        return null;
    }

    /**
     * 修改年计划数据
     *
     * @param to 年计划传输对象
     * @return
     * @throws SerException
     */
    default YearPlanBO update(YearPlanTO to) throws SerException {
        return null;
    }

    /**
     * 删除年计划对象
     *
     * @param to 年计划传输对象
     * @return
     * @throws SerException
     */
    default YearPlanBO delete(YearPlanTO to) throws SerException {
        return null;
    }

    /**
     * 查询本年年计划数据
     *
     * @return
     * @throws SerException
     */
    default List<YearPlanBO> findThisYear() throws SerException {
        return null;
    }

    /**
     * 根据年份查询年计划数据
     *
     * @param year 年份
     * @return
     * @throws SerException
     */
    default List<YearPlanBO> findByYear(Integer year) throws SerException {
        return null;
    }

    /**
     * 获取年计划选择对象
     *
     * @return
     * @throws SerException
     */
    default List<YearPlanChoiceBO> getChoice() throws SerException {
        return null;
    }

}