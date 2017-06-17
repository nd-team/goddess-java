package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.DayPlanBO;
import com.bjike.goddess.marketdevelopment.dto.DayPlanDTO;
import com.bjike.goddess.marketdevelopment.entity.DayPlan;
import com.bjike.goddess.marketdevelopment.entity.SonPermissionObject;
import com.bjike.goddess.marketdevelopment.to.CollectTO;
import com.bjike.goddess.marketdevelopment.to.DayPlanTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;

import java.util.List;

/**
 * 天计划业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:08 ]
 * @Description: [ 天计划业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DayPlanSer extends Ser<DayPlan, DayPlanDTO> {

    /**
     * 保存天计划数据
     *
     * @param to 天计划传输对象
     * @return
     * @throws SerException
     */
    default DayPlanBO save(DayPlanTO to) throws SerException {
        return null;
    }

    /**
     * 修改天计划数据
     *
     * @param to 天计划传输对象
     * @return
     * @throws SerException
     */
    default DayPlanBO update(DayPlanTO to) throws SerException {
        return null;
    }

    /**
     * 删除天计划数据
     *
     * @param to 天计划传输对象
     * @return
     * @throws SerException
     */
    default DayPlanBO delete(DayPlanTO to) throws SerException {
        return null;
    }

    /**
     * 根据时间范围查询天计划数据
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return
     * @throws SerException
     */
    default List<DayPlanBO> findByDate(String start, String end) throws SerException {
        return null;
    }

    /**
     * 根据时间查询天计划数据
     *
     * @param date 时间
     * @return
     * @throws SerException
     */
    default List<DayPlanBO> findByDate(String date) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 天计划数据传输对象
     * @return
     * @throws SerException
     */
    default List<DayPlanBO> maps(DayPlanDTO dto) throws SerException {
        return null;
    }

    /**
     * 导出
     *
     * @param to 导出查询条件传输对象
     * @return
     * @throws SerException
     */
    default byte[] exportExcel(CollectTO to) throws SerException {
        return null;
    }

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
}