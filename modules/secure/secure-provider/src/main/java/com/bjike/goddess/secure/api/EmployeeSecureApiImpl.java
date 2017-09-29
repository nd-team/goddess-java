package com.bjike.goddess.secure.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.secure.bo.EmployeeSecureBO;
import com.bjike.goddess.secure.dto.EmployeeSecureDTO;
import com.bjike.goddess.secure.service.EmployeeSecureSer;
import com.bjike.goddess.secure.to.EmployeeSecureTO;
import com.bjike.goddess.secure.to.GuidePermissionTO;
import com.bjike.goddess.secure.to.NameTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 员工社保基本信息业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-04-24 10:19 ]
 * @Description: [ 员工社保基本信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("employeeSecureApiImpl")
public class EmployeeSecureApiImpl implements EmployeeSecureAPI {
    @Autowired
    private EmployeeSecureSer employeeSecureSer;

    @Override
    public EmployeeSecureBO save(EmployeeSecureTO to) throws SerException {
        return employeeSecureSer.save(to);
    }

//    @Override
//    public List<EmployeeSecureBO> findBySql(String[] employeeId) throws SerException {
//        return employeeSecureSer.findBySql(employeeId);
//    }

    @Override
    public void remove(EmployeeSecureTO to) throws SerException {
        employeeSecureSer.remove(to);
    }

    @Override
    public EmployeeSecureBO edit(EmployeeSecureTO to) throws SerException {
        return employeeSecureSer.edit(to);
    }

    @Override
    public List<EmployeeSecureBO> find(EmployeeSecureDTO dto) throws SerException {
        return employeeSecureSer.find(dto);
    }

    @Override
    public EmployeeSecureBO findByID(String id) throws SerException {
        return employeeSecureSer.findByID(id);
    }

    @Override
    public EmployeeSecureBO delete(String id) throws SerException {
        return employeeSecureSer.delete(id);
    }

    @Override
    public Long count(EmployeeSecureDTO dto) throws SerException {
        return employeeSecureSer.count(dto);
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return employeeSecureSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return employeeSecureSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<EmployeeSecureBO> byName(NameTO to) throws SerException {
        return employeeSecureSer.byName(to);
    }

    @Override
    public Set<String> allName() throws SerException {
        return employeeSecureSer.allName();
    }
}