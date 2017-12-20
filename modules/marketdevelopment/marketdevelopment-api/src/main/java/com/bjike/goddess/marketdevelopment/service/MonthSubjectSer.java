package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.MonthMonthMoneyBO;
import com.bjike.goddess.marketdevelopment.bo.MonthSubjectBO;
import com.bjike.goddess.marketdevelopment.dto.MonthSubjectDTO;
import com.bjike.goddess.marketdevelopment.entity.MonthSubject;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectTO;
import com.bjike.goddess.marketdevelopment.to.MonthSubjectUpdateTO;

import java.util.List;

/**
* 月计划的业务科目业务接口
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-06 05:04 ]
* @Description:	[ 月计划的业务科目业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface MonthSubjectSer extends Ser<MonthSubject, MonthSubjectDTO> {

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
    default List<MonthMonthMoneyBO> maps(MonthSubjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param to
     * @throws SerException
     */
    default void save(MonthSubjectTO to) throws SerException {
        return;
    }

    /**
     * 修改月计划数据
     *
     * @param to
     * @throws SerException
     */
    default void update(MonthSubjectUpdateTO to) throws SerException {
        return;
    }

    /**
     * 删除月计划数据
     *
     * @param monthSubjectId
     * @throws SerException
     */
    default void delete(String monthSubjectId) throws SerException {
        return;
    }

    /**
     * 根据id获取月计划数据
     *
     * @param monthSubjectId
     * @return
     * @throws SerException
     */
    default MonthSubjectBO getById(String monthSubjectId) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    default Long getTotal(MonthSubjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 冻结
     *
     * @param monthSubjectId
     * @throws SerException
     */
    default void congeal(String monthSubjectId) throws SerException {
        return;
    }

    /**
     * 解冻
     *
     * @param monthSubjectId
     * @throws SerException
     */
    default void thaw(String monthSubjectId) throws SerException {
        return;
    }
}