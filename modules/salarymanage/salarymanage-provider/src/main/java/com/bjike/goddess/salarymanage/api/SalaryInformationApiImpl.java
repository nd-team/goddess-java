package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.archive.bo.StaffRecords1BO;
import com.bjike.goddess.archive.bo.StaffRecordsBO;
import com.bjike.goddess.assistance.bo.AgeAssistBO;
import com.bjike.goddess.assistance.bo.ComputerAssistBO;
import com.bjike.goddess.assistance.bo.HotAssistBO;
import com.bjike.goddess.assistance.bo.HouseAssistBO;
import com.bjike.goddess.assistance.dto.AgeAssistDTO;
import com.bjike.goddess.assistance.dto.ComputerAssistDTO;
import com.bjike.goddess.assistance.dto.HotAssistDTO;
import com.bjike.goddess.assistance.dto.HouseAssistDTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managementpromotion.entity.LevelShow;
import com.bjike.goddess.managepromotion.bo.OverviewSkillLevelBO;
import com.bjike.goddess.salaryconfirm.bo.SalaryconfirmBO;
import com.bjike.goddess.salarymanage.bo.SalaryBasicBO;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryBasicDTO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.entity.SalaryInformation;
import com.bjike.goddess.salarymanage.service.SalaryInformationSer;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryBasicTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
import com.bjike.goddess.secure.bo.AttachedBO;
import com.bjike.goddess.staffentry.bo.EntryBasicInfoBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 薪资管理业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 01:45 ]
* @Description:	[ 薪资管理业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("salaryInformationApiImpl")
public class SalaryInformationApiImpl implements SalaryInformationAPI  {

    @Autowired
    private SalaryInformationSer salaryInformationSer;

    @Override
    public List<SalaryInformationBO> pageList(SalaryInformationDTO dto) throws SerException {
        return salaryInformationSer.pageList(dto);
    }

    @Override
    public SalaryInformationBO addSalaryInformation(SalaryInformationTO to) throws SerException {
        return salaryInformationSer.addSalaryInformation(to);
    }

    @Override
    public SalaryInformationBO editSalaryInformation(SalaryInformationTO to) throws SerException {
        return salaryInformationSer.editSalaryInformation(to);
    }

    @Override
    public void deleteSalaryInformation(String id) throws SerException {
         salaryInformationSer.deleteSalaryInformation(id);
    }

    @Override
    public void leadExcel(List<SalaryInformationTO> toList) throws SerException {
        salaryInformationSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(ExportSalaryInformationTO to) throws SerException {
        return salaryInformationSer.exportExcel(to);
    }


    @Override
    public List<String> findTime() throws SerException {
        return salaryInformationSer.findTime();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return salaryInformationSer.templateExport();
    }

    @Override
    public Boolean sonPermission() throws SerException {
        return salaryInformationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return salaryInformationSer.guidePermission(guidePermissionTO);
    }

    @Override
    public LevelShow findByEmployeeId(String employeeId) throws SerException {
        return salaryInformationSer.findByEmployeeId(employeeId);
    }

    @Override
    public List<EntryBasicInfoBO> getByEmpNumber(String employeeId) throws SerException {
        return salaryInformationSer.getByEmpNumber(employeeId);
    }

    @Override
    public Long count(SalaryInformationDTO dto) throws SerException {
        return salaryInformationSer.count(dto);
    }

    @Override
    public HotAssistBO findHotAssist(SalaryInformationDTO dto) throws SerException {
        return salaryInformationSer.findHotAssist(dto);
    }

    @Override
    public HouseAssistBO findHouseAssist(SalaryInformationDTO dto) throws SerException {
        return salaryInformationSer.findHouseAssist(dto);
    }

    @Override
    public ComputerAssistBO findComputerAssist(SalaryInformationDTO dto) throws SerException {
        return salaryInformationSer.findComputerAssist(dto);
    }

    @Override
    public AgeAssistBO findAgeAssist(SalaryInformationDTO dto) throws SerException {
        return salaryInformationSer.findAgeAssist(dto);
    }


    @Override
    public SalaryInformationBO findOne(String id) throws SerException {
        return salaryInformationSer.findOne(id);
    }

    @Override
    public StaffRecordsBO findStaff(String employeeNumber) throws SerException {
        return salaryInformationSer.findStaff(employeeNumber);
    }

    @Override
    public String findPositiveDate(String employeeId) throws SerException {
        return salaryInformationSer.findPositiveDate(employeeId);
    }

    @Override
    public OverviewSkillLevelBO findSkill(String employeeName) throws SerException {
        return salaryInformationSer.findSkill(employeeName);
    }

    @Override
    public SalaryconfirmBO findSalaryConfirm(SalaryInformationDTO dto) throws SerException {
        return salaryInformationSer.findSalaryConfirm(dto);
    }

    @Override
    public AttachedBO findAttached(SalaryInformationDTO dto) throws SerException {
        return salaryInformationSer.findAttached(dto);
    }

    @Override
    public SalaryInformationBO findByName(String name) throws SerException {
        return salaryInformationSer.findByName(name);
    }
}