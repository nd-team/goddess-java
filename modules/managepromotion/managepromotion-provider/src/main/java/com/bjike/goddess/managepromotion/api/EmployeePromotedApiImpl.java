package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.EmployeePromotedBO;
import com.bjike.goddess.managepromotion.dto.EmployeePromotedDTO;
import com.bjike.goddess.managepromotion.service.EmployeePromotedSer;
import com.bjike.goddess.managepromotion.to.EmployeePromotedTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工已晋升情况业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:20 ]
 * @Description: [ 员工已晋升情况业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("employeePromotedApiImpl")
public class EmployeePromotedApiImpl implements EmployeePromotedAPI {
    @Autowired
    private EmployeePromotedSer employeePromotedSer;

    @Override
    public Long countEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        return employeePromotedSer.countEmployeePromoted(employeePromotedDTO);
    }

    @Override
    public EmployeePromotedBO getOne(String id) throws SerException {
        return employeePromotedSer.getOne(id);
    }

    @Override
    public List<EmployeePromotedBO> findListEmployeePromoted(EmployeePromotedDTO employeePromotedDTO) throws SerException {
        return employeePromotedSer.findListEmployeePromoted(employeePromotedDTO);
    }

    @Override
    public EmployeePromotedBO insertEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        return employeePromotedSer.insertEmployeePromoted(employeePromotedTO);
    }

    @Override
    public EmployeePromotedBO editEmployeePromoted(EmployeePromotedTO employeePromotedTO) throws SerException {
        return employeePromotedSer.editEmployeePromoted(employeePromotedTO);
    }

    @Override
    public void removeEmployeePromoted(String id) throws SerException {
        employeePromotedSer.removeEmployeePromoted(id);
    }
    @Override
    public List<EmployeePromotedBO> seach(EmployeePromotedDTO  employeePromotedDTO) throws SerException {
        return employeePromotedSer.seach(employeePromotedDTO);
    }
}