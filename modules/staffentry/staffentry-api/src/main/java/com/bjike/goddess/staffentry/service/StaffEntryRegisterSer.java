package com.bjike.goddess.staffentry.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.staffentry.bo.StaffEntryRegisterBO;
import com.bjike.goddess.staffentry.entity.StaffEntryRegister;
import com.bjike.goddess.staffentry.dto.StaffEntryRegisterDTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterTO;

import java.util.List;

/**
 * 员工入职注册业务接口
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface StaffEntryRegisterSer extends Ser<StaffEntryRegister, StaffEntryRegisterDTO> {

    /**
     * 员工入职注册列表总条数
     */
    default Long countStaffEntryRegister(StaffEntryRegisterDTO staffEntryRegisterDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取员工入职注册
     *
     * @return class StaffEntryRegisterBO
     */
    default StaffEntryRegisterBO getOne(String id ) throws SerException {
        return null;
    }
    /**
     * 员工入职注册列表
     *
     * @return class StaffEntryRegisterBO
     */
    default List<StaffEntryRegisterBO> listStaffEntryRegister(StaffEntryRegisterDTO staffEntryRegisterDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param staffEntryRegisterTO 员工入职注册信息
     * @return class StaffEntryRegisterBO
     */
    default StaffEntryRegisterBO addStaffEntryRegister(StaffEntryRegisterTO staffEntryRegisterTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param staffEntryRegisterTO 员工入职注册信息
     * @return class StaffEntryRegisterBO
     */
    default StaffEntryRegisterBO editStaffEntryRegister(StaffEntryRegisterTO staffEntryRegisterTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 员工入职注册信息id
     */
    default void delete(String id) throws SerException {
    }

}