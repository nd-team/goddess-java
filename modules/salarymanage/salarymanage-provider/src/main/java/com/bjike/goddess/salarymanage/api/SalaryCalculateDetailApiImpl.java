package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.recruit.bo.InterviewInforBO;
import com.bjike.goddess.salarymanage.bo.SalaryCalculateDetailBO;
import com.bjike.goddess.salarymanage.bo.SalaryConfirmRecordBO;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateDetailDTO;
import com.bjike.goddess.salarymanage.dto.SalaryConfirmRecordDTO;
import com.bjike.goddess.salarymanage.excel.SonPermissionObject;
import com.bjike.goddess.salarymanage.service.SalaryCalculateDetailSer;
import com.bjike.goddess.salarymanage.to.ExportSalaryCalculateTO;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryCalculateDetailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 薪资测算明细表业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-16 10:45 ]
* @Description:	[ 薪资测算明细表业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("salaryCalculateDetailApiImpl")
public class SalaryCalculateDetailApiImpl implements SalaryCalculateDetailAPI  {

    @Autowired
    private SalaryCalculateDetailSer salaryCalculateDetailSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return salaryCalculateDetailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return salaryCalculateDetailSer.guidePermission(guidePermissionTO);
    }

    @Override
    public void add(SalaryCalculateDetailTO to) throws SerException {
        salaryCalculateDetailSer.add(to);
    }

    @Override
    public void delete(String id) throws SerException {
        salaryCalculateDetailSer.delete(id);
    }

    @Override
    public void modify(SalaryCalculateDetailTO to) throws SerException {
        salaryCalculateDetailSer.modify(to);
    }

    @Override
    public List<SalaryCalculateDetailBO> findList(SalaryCalculateDetailDTO dto) throws SerException {
        return salaryCalculateDetailSer.findList(dto);
    }

    @Override
    public List<InterviewInforBO> findInteview() throws SerException {
        return salaryCalculateDetailSer.findInteview();
    }

    @Override
    public SalaryCalculateDetailBO findOne(String id) throws SerException {
        return salaryCalculateDetailSer.findOne(id);
    }

    @Override
    public Long count(SalaryCalculateDetailDTO dto) throws SerException {
        return salaryCalculateDetailSer.count(dto);
    }

    @Override
    public void leadExcel(List<SalaryCalculateDetailTO> toList) throws SerException {
        salaryCalculateDetailSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(ExportSalaryCalculateTO to) throws SerException {
        return salaryCalculateDetailSer.exportExcel(to);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return salaryCalculateDetailSer.templateExport();
    }
}