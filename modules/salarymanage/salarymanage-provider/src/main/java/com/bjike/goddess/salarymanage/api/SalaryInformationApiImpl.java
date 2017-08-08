package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salarymanage.bo.SalaryBasicBO;
import com.bjike.goddess.salarymanage.bo.SalaryInformationBO;
import com.bjike.goddess.salarymanage.dto.SalaryBasicDTO;
import com.bjike.goddess.salarymanage.dto.SalaryInformationDTO;
import com.bjike.goddess.salarymanage.service.SalaryInformationSer;
import com.bjike.goddess.salarymanage.to.ExportSalaryInformationTO;
import com.bjike.goddess.salarymanage.to.SalaryBasicTO;
import com.bjike.goddess.salarymanage.to.SalaryInformationTO;
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
}