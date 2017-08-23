package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.StaffEntryRegisterBO;
import com.bjike.goddess.staffentry.dto.StaffEntryRegisterDTO;
import com.bjike.goddess.staffentry.service.StaffEntryRegisterSer;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterEmailTO;
import com.bjike.goddess.staffentry.to.StaffEntryRegisterTO;
import com.bjike.goddess.staffentry.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工入职注册业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-05-15 07:03 ]
 * @Description: [ 员工入职注册业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("staffEntryRegisterApiImpl")
public class StaffEntryRegisterApiImpl implements StaffEntryRegisterAPI {

    @Autowired
    private StaffEntryRegisterSer staffEntryRegisterSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return staffEntryRegisterSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return staffEntryRegisterSer.guidePermission( guidePermissionTO );
    }

    @Override
    public Long countStaffEntryRegister(StaffEntryRegisterDTO staffEntryRegisterDTO) throws SerException {
        return staffEntryRegisterSer.countStaffEntryRegister(staffEntryRegisterDTO);
    }

    @Override
    public StaffEntryRegisterBO getOne(String id) throws SerException {
        return staffEntryRegisterSer.getOne(id);
    }

    @Override
    public List<StaffEntryRegisterBO> listStaffEntryRegister(StaffEntryRegisterDTO staffEntryRegisterDTO) throws SerException {
        return staffEntryRegisterSer.listStaffEntryRegister(staffEntryRegisterDTO);
    }

    @Override
    public StaffEntryRegisterBO addStaffEntryRegister(StaffEntryRegisterTO staffEntryRegisterTO) throws SerException {
        return staffEntryRegisterSer.addStaffEntryRegister(staffEntryRegisterTO);
    }

    @Override
    public StaffEntryRegisterBO editStaffEntryRegister(StaffEntryRegisterTO staffEntryRegisterTO) throws SerException {
        return staffEntryRegisterSer.editStaffEntryRegister(staffEntryRegisterTO);
    }

    @Override
    public void delete(String id) throws SerException {
        staffEntryRegisterSer.delete(id);
    }


    @Override
    public String maxEmpNumber() throws SerException {
        return staffEntryRegisterSer.maxEmpNumber();
    }

    @Override
    public void sendAccountToEmp(StaffEntryRegisterEmailTO staffEntryRegisterEmailTO) throws SerException {
        staffEntryRegisterSer.sendAccountToEmp( staffEntryRegisterEmailTO );
    }
}