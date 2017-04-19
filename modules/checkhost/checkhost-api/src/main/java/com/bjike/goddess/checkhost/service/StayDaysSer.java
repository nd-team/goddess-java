package com.bjike.goddess.checkhost.service;

import com.bjike.goddess.checkhost.bo.StayDaysBO;
import com.bjike.goddess.checkhost.to.StayDaysTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.checkhost.entity.StayDays;
import com.bjike.goddess.checkhost.dto.StayDaysDTO;

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
     * @param stayDaysTO 员工住宿天数汇总
     * @return class StayDaysBO
     */
    default StayDaysBO auditStayDays(StayDaysTO stayDaysTO) throws SerException {
        return null;
    }


}