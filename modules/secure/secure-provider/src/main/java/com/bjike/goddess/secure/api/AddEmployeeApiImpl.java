package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.AddEmployeeBO;
import com.bjike.goddess.secure.dto.AddEmployeeDTO;
import com.bjike.goddess.secure.service.AddEmployeeSer;
import com.bjike.goddess.secure.to.AddEmployeeTO;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 社保增员信息名单业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-21 03:02 ]
 * @Description: [ 社保增员信息名单业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("addEmployeeApiImpl")
public class AddEmployeeApiImpl implements AddEmployeeAPI {
    @Autowired
    private AddEmployeeSer addEmployeeSer;

    @Override
    public List<AddEmployeeBO> find(AddEmployeeDTO dto) throws SerException {
        return addEmployeeSer.find(dto);
    }

    @Override
    public AddEmployeeBO findByID(String id) throws SerException {
        return addEmployeeSer.findByID(id);
    }


    @Override
    public AddEmployeeBO delete(String id) throws SerException {
        return addEmployeeSer.delete(id);
    }

    @Override
    public AddEmployeeBO save(AddEmployeeTO to) throws SerException {
        return addEmployeeSer.save(to);
    }

    @Override
    public List<AddEmployeeBO> findAll() throws SerException {
        return addEmployeeSer.findALL();
    }

    @Override
    public void edit(AddEmployeeTO to) throws SerException {
        addEmployeeSer.edit(to);
    }

    @Override
    public void commerceAudit(AddEmployeeTO to) throws SerException {
        addEmployeeSer.commerceAudit(to);
    }

    @Override
    public void confirmAdd(String id) throws SerException {
        addEmployeeSer.confirmAdd(id);
    }

    @Override
    public Long count(AddEmployeeDTO dto) throws SerException {
        return addEmployeeSer.count(dto);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return addEmployeeSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return addEmployeeSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void managerConfirmAdd(AddEmployeeDTO dto, String id) throws SerException {
        addEmployeeSer.managerConfirmAdd(dto,id);
    }
}