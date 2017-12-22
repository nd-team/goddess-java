package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.MonthMoneyProvincialBO;
import com.bjike.goddess.marketdevelopment.bo.MonthSubjectProvincialUpdateBO;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectProvincialDTO;
import com.bjike.goddess.marketdevelopment.entity.MonthSubjectProvincial;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectProvincialADDTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectProvincialUpdateTO;

import java.util.List;

/**
* 月计划省市方向业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-07 10:29 ]
* @Description:	[ 月计划省市方向业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MonthSubjectProvincialSer extends Ser<MonthSubjectProvincial, MonthSubjectProvincialDTO> {

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
    default List<MonthMoneyProvincialBO> maps(MonthSubjectProvincialDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(MonthSubjectProvincialADDTO to) throws SerException {
        return;
    }

    /**
     * 修改月计划省市方向数据
     *
     * @param to
     * @throws SerException
     */
    default void update(MonthSubjectProvincialUpdateTO to) throws SerException {
        return;
    }

    /**
     * 删除月计划省市方向数据
     *
     * @param provincialAreaId
     * @throws SerException
     */
    default void delete(String provincialAreaId) throws SerException {
        return;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(MonthSubjectProvincialDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取月计划省市方向数据
     *
     * @param provincialAreaId
     * @return
     * @throws SerException
     */
    default MonthSubjectProvincialUpdateBO getById(String provincialAreaId) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param monthmoneyId
     * @throws SerException
     */
    default void congeal(String monthmoneyId) throws SerException {
        return;
    }

    /**
     * 冻结
     *
     * @param monthmoneyId
     * @throws SerException
     */
    default void thaw(String monthmoneyId) throws SerException {
        return;
    }

    /**
     * 根据年月份获取实际金额
     *
     * @param year
     * @param month
     * @return
     * @throws SerException
     */
    default Double findActualMoney(String year, String month) throws SerException {
        return null;
    }

    /**
     * 根据年月份和业务类型获取实际金额
     *
     * @param year
     * @param month
     * @param businessType
     * @return
     * @throws SerException
     */
    default Double findActualMoney1(String year, String month, String businessType) throws SerException {
        return null;
    }
}