package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.CollectNameBO;
import com.bjike.goddess.checkhost.bo.StayDaysBO;
import com.bjike.goddess.checkhost.dto.StayDaysDTO;
import com.bjike.goddess.checkhost.entity.StayDays;
import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.checkhost.to.GuidePermissionTO;
import com.bjike.goddess.checkhost.to.StayDaysTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 员工住宿天数汇总业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:03 ]
 * @Description: [ 员工住宿天数汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StayDaysSer extends Ser<StayDays, StayDaysDTO> {
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
     * 员工住宿天数汇总列表总条数
     */
    default Long countStayDays(StayDaysDTO stayDaysDTO) throws SerException {
        return null;
    }

    /**
     * 一个员工住宿天数汇总
     *
     * @return class StayDaysBO
     */
    default StayDaysBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 获取员工住宿天数汇总
     *
     * @param stayDaysDTO 员工住宿天数汇总dto
     * @return class StayDaysBO
     * @throws SerException
     */
    default List<StayDaysBO> findListStayDays(StayDaysDTO stayDaysDTO) throws SerException {
        return null;
    }

    /**
     * 添加员工住宿天数汇总
     *
     * @param stayDaysTO 员工住宿天数汇总数据to
     * @return class StayDaysBO
     * @throws SerException
     */
    default StayDaysBO insertStayDays(StayDaysTO stayDaysTO) throws SerException {
        return null;
    }

    /**
     * 编辑员工住宿天数汇总
     *
     * @param stayDaysTO 员工住宿天数汇总数据to
     * @return class StayDaysBO
     * @throws SerException
     */
    default StayDaysBO editStayDays(StayDaysTO stayDaysTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除员工住宿天数汇总
     *
     * @param id
     * @throws SerException
     */
    default void removeStayDays(String id) throws SerException {

    }

    /**
     * 审核
     *
     * @param id          id
     * @param checkStatus 审核状态
     * @return class StayDaysBO
     */
    StayDaysBO auditStayDays(String id, CheckStatus checkStatus) throws SerException;

    /**
     * 汇总
     *
     * @param names names
     * @return class CollectNameBO
     * @throws SerException
     */
    default List<CollectNameBO> collectName(String[] names) throws SerException {
        return null;
    }

    /**
     * 获取名字
     *
     * @return class String
     */
    default List<String> getNames() throws SerException {
        return null;
    }


}