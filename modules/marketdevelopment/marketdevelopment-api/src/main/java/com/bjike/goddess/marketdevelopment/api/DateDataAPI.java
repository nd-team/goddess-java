package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.DateDataBO;
import com.bjike.goddess.marketdevelopment.bo.MonthBusinessDataBO;
import com.bjike.goddess.marketdevelopment.bo.MonthMoneyBO;
import com.bjike.goddess.marketdevelopment.dto.DateDataDTO;
import com.bjike.goddess.marketdevelopment.to.DateDataTO;
import com.bjike.goddess.marketdevelopment.to.DateDataUpdateTO;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;

import java.util.List;

/**
 * 日期数据业务接口
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-30 04:08 ]
 * @Description: [ 日期数据业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DateDataAPI {

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
    default List<MonthMoneyBO> maps(DateDataDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(DateDataTO to) throws SerException {
        return;
    }

    /**
     * 修改外出单数据
     *
     * @param to
     * @throws SerException
     */
    default void update(DateDataUpdateTO to) throws SerException {
        return;
    }

    /**
     * 删除外出单数据
     *
     * @throws SerException
     */
    default void delete(String dateDataId) throws SerException {
        return;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(DateDataDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取日汇总数据
     *
     * @param dateDataId
     * @return
     * @throws SerException
     */
    default DateDataBO getById(String dateDataId) throws SerException {
        return null;
    }

    /**
     * 根据年份月份获取目标计划实际差异金额
     *
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    default MonthMoneyBO findMoneyData(String year, String month) throws SerException {
        return null;
    }

    /**
     * 根据年份月份业务方向类型获取权重金额
     *
     * @param year
     * @param month
     * @param businessType
     * @return
     * @throws SerException
     */
    default MonthBusinessDataBO findBusinessData(String year, String month, String businessType) throws SerException {
        return null;
    }

    /**
     * 获取当前周的日期时间
     *
     * @param year
     * @param month
     * @param cycle
     * @return
     * @throws SerException
     */
    default List<String> findDate(String year, String month, String cycle) throws SerException {
        return null;
    }

    /**
     * 获取当月的周数
     *
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    default Integer weeks(Integer year, Integer month) throws SerException {
        return null;
    }
}