package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.*;
import com.bjike.goddess.managepromotion.dto.EmployeePromotedDTO;
import com.bjike.goddess.managepromotion.service.EmployeePromotedSer;
import com.bjike.goddess.managepromotion.to.*;
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
    public Boolean sonPermission() throws SerException {
        return employeePromotedSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return employeePromotedSer.guidePermission( guidePermissionTO );
    }
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
    public List<CollectBO> collect(CollectTO to) throws SerException {
        return employeePromotedSer.collect(to);
    }
    @Override
    public List<String> getName() throws SerException {
        return employeePromotedSer.getName();
    }
    @Override
    public List<String> getStatus() throws SerException {
        return employeePromotedSer.getStatus();
    }
    @Override
    public SkillPromotionDetailCollectABO detailWeekCollect(SkillPromotionDetailCollectTO to) throws SerException {
        return employeePromotedSer.detailWeekCollect(to);
    }
    @Override
    public SkillPromotionDetailCollectABO detailMonthCollect(SkillPromotionDetailCollectTO to) throws SerException {
        return employeePromotedSer.detailMonthCollect(to);
    }

    @Override
    public SkillPromotionDetailCollectABO detailTotalCollect(SkillPromotionDetailCollectTO to) throws SerException {
        return employeePromotedSer.detailTotalCollect(to);
    }

    @Override
    public List<ProfessionalSkillCollectBO> dayProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        return employeePromotedSer.dayProfessionalCollect(to);
    }

    @Override
    public List<ProfessionalSkillCollectBO> weekProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        return employeePromotedSer.weekProfessionalCollect(to);
    }

    @Override
    public List<ProfessionalSkillCollectBO> monthProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        return employeePromotedSer.monthProfessionalCollect(to);
    }

    @Override
    public List<ProfessionalSkillCollectBO> totalProfessionalCollect(ProfessionalSkillTO to) throws SerException {
        return employeePromotedSer.totalProfessionalCollect(to);
    }

    @Override
    public List<StaffSkillCollectBO> dayStaffCollect(StaffSkillCollectTO to) throws SerException {
        return employeePromotedSer.dayStaffCollect(to);
    }

    @Override
    public List<StaffSkillCollectBO> weekStaffCollect(StaffSkillCollectTO to) throws SerException {
        return employeePromotedSer.weekStaffCollect(to);
    }

    @Override
    public List<StaffSkillCollectBO> monthStaffCollect(StaffSkillCollectTO to) throws SerException {
        return employeePromotedSer.monthStaffCollect(to);
    }

    @Override
    public List<StaffSkillCollectBO> totalStaffCollect(StaffSkillCollectTO to) throws SerException {
        return employeePromotedSer.totalStaffCollect(to);
    }

    @Override
    public void leadExcel(List<EmployeePromotedTO> toList) throws SerException {
        employeePromotedSer.leadExcel(toList);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return employeePromotedSer.templateExport();
    }
}