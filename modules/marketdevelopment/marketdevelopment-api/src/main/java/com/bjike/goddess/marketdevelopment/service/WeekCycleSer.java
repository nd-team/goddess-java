package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.WeekCycleBO;
import com.bjike.goddess.marketdevelopment.bo.WeekMonthMoneyBO;
import com.bjike.goddess.marketdevelopment.dto.WeekCycleDTO;
import com.bjike.goddess.marketdevelopment.entity.WeekCycle;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.WeekCycleTO;
import com.bjike.goddess.marketdevelopment.to.WeekCycleUpdateTO;

import java.util.List;

/**
* 周计划的周期业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 05:37 ]
* @Description:	[ 周计划的周期业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface WeekCycleSer extends Ser<WeekCycle, WeekCycleDTO> {

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

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default List<WeekMonthMoneyBO> maps(WeekCycleDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(WeekCycleTO to) throws SerException {
        return;
    }

    /**
     * 修改周计划数据
     *
     * @param to
     * @throws SerException
     */
    default void update(WeekCycleUpdateTO to) throws SerException {
        return;
    }

    /**
     * 删除周计划数据
     *
     * @param weekCycleId
     * @throws SerException
     */
    default void delete(String weekCycleId) throws SerException {
        return;
    }

    /**
     * 根据id获取周计划数据
     *
     * @param weekCycleId
     * @return
     * @throws SerException
     */
    default List<WeekCycleBO> getById(String weekCycleId) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(WeekCycleDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取当前月分的周期
     *
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    default List<String> getCycle(String year, String month) throws SerException {
        return null;
    }
}