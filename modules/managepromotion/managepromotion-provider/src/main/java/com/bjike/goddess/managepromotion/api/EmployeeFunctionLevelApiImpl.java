package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.EmployeeFunctionLevelBO;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.managepromotion.dto.EmployeeFunctionLevelDTO;
import com.bjike.goddess.managepromotion.service.EmployeeFunctionLevelSer;
import com.bjike.goddess.managepromotion.to.EmployeeFunctionLevelTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 员工职能定级业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-22 04:53 ]
 * @Description: [ 员工职能定级业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("employeeFunctionLevelApiImpl")
public class EmployeeFunctionLevelApiImpl implements EmployeeFunctionLevelAPI {

    @Autowired
    private EmployeeFunctionLevelSer employeeFunctionLevelSer;

    @Override
    public Long countEmployeeFunctionLevel(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws SerException {
        return employeeFunctionLevelSer.countEmployeeFunctionLevel(employeeFunctionLevelDTO);
    }

    @Override
    public EmployeeFunctionLevelBO getOne(String id) throws SerException {
        return employeeFunctionLevelSer.getOne(id);
    }

    @Override
    public List<EmployeeFunctionLevelBO> findListEmployeeFunctionLevel(EmployeeFunctionLevelDTO employeeFunctionLevelDTO) throws SerException {
        return employeeFunctionLevelSer.findListEmployeeFunctionLevel(employeeFunctionLevelDTO);
    }

    @Override
    public EmployeeFunctionLevelBO insertSkillGrading(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        return employeeFunctionLevelSer.insertSkillGrading(employeeFunctionLevelTO);
    }

    @Override
    public EmployeeFunctionLevelBO editEmployeeFunctionLevel(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        return employeeFunctionLevelSer.editEmployeeFunctionLevel(employeeFunctionLevelTO);
    }

    @Override
    public void removeEmployeeFunctionLevel(String id) throws SerException {
        employeeFunctionLevelSer.removeEmployeeFunctionLevel(id);

    }

    @Override
    public OverviewSkillLevelBO skill(EmployeeFunctionLevelTO employeeFunctionLevelTO) throws SerException {
        return employeeFunctionLevelSer.skill(employeeFunctionLevelTO);
    }
}