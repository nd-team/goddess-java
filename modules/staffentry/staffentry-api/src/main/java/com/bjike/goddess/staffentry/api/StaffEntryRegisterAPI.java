package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.StaffEntryRegisterBO;
import com.bjike.goddess.staffentry.dto.StaffEntryRegisterDTO;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterEmailTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterTO;
import com.bjike.goddess.staffentry.vo.SonPermissionObject;

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
public interface StaffEntryRegisterAPI {


    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {

        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

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

    /**
     * 获取注册的员工编号
     *
     */
    default String  maxEmpNumber( ) throws SerException {
        return null;
    }

    /**
     * 账号密码告知
     *
     * @param staffEntryRegisterEmailTO 账号密码告知
     */
    default void sendAccountToEmp(StaffEntryRegisterEmailTO staffEntryRegisterEmailTO) throws SerException {
    }


}