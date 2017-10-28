package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.*;
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

    @Override
    public String getMaxEmpNumberByName(String name) throws SerException {
        return staffEntryRegisterSer.getMaxEmpNumberByName(name);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return staffEntryRegisterSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return staffEntryRegisterSer.templateExport();
    }

    @Override
    public void importExcel(List<StaffEntryRegisterTO> staffEntryRegisterTOS) throws SerException {
        staffEntryRegisterSer.importExcel(staffEntryRegisterTOS);
    }

    @Override
    public String findNotisDate(String[] ids) throws SerException {
        return staffEntryRegisterSer.findNotisDate(ids);
    }

    @Override
    public void notis(String content, String[] emails, String[] ids) throws SerException {
        staffEntryRegisterSer.notis(content,emails,ids);
    }

    @Override
    public List<String> findEmpNum() throws SerException {
        return staffEntryRegisterSer.findEmpNum();
    }

    @Override
    public LinkDateStaffEntryBO findByEmpNum(String empNum) throws SerException {
        return staffEntryRegisterSer.findByEmpNum(empNum);
    }

    @Override
    public List<EntrySummaryBO> summaDay(String summationDate) throws SerException {
        return staffEntryRegisterSer.summaDay(summationDate);
    }

    @Override
    public List<EntrySummaryBO> summaWeek(Integer year, Integer month, Integer week) throws SerException {
        return staffEntryRegisterSer.summaWeek(year,month,week);
    }

    @Override
    public List<EntrySummaryBO> summaMonth(Integer year, Integer month) throws SerException {
        return staffEntryRegisterSer.summaMonth(year,month);
    }

    @Override
    public List<EntrySummaryBO> summaTotal(String endDate) throws SerException {
        return staffEntryRegisterSer.summaTotal(endDate);
    }

    @Override
    public OptionBO figureShowDay(String summationDate) throws SerException {
        return staffEntryRegisterSer.figureShowDay(summationDate);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return staffEntryRegisterSer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return staffEntryRegisterSer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figureShowTotal(String endDate) throws SerException {
        return staffEntryRegisterSer.figureShowTotal(endDate);
    }

    @Override
    public BrokenOptionBO brokenShowYear(Integer year) throws SerException {
        return staffEntryRegisterSer.brokenShowYear(year);
    }
}