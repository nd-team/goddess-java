package com.bjike.goddess.staffwelfare.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffwelfare.bo.StaffBirthDayBO;
import com.bjike.goddess.staffwelfare.dto.StaffBirthDayDTO;
import com.bjike.goddess.staffwelfare.entity.StaffBirthDay;

import java.util.List;

/**
 * Created by haikuang on 17-8-17.
 */
public interface StaffBirthDaySer extends Ser<StaffBirthDay,StaffBirthDayDTO> {

    /**
     * 根据月份来查询员工生日信息
     * @param dto
     * @throws SerException
     */
    List<StaffBirthDayBO> findBirthDay(StaffBirthDayDTO dto) throws SerException;

    /**
     * 列表总条数
     */
    Long count(StaffBirthDayDTO dto) throws SerException;
}
