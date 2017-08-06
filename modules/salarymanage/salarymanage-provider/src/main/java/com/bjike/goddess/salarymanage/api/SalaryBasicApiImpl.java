package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.HierarchyBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.salarymanage.bo.SalaryBasicBO;
import com.bjike.goddess.salarymanage.dto.SalaryBasicDTO;
import com.bjike.goddess.salarymanage.service.SalaryBasicSer;
import com.bjike.goddess.salarymanage.to.ExportSalaryBasicTO;
import com.bjike.goddess.salarymanage.to.SalaryBasicTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 薪资管理业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-07-31 09:50 ]
* @Description:	[ 薪资管理业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("salaryBasicApiImpl")
public class SalaryBasicApiImpl implements SalaryBasicAPI  {
    @Autowired
    private SalaryBasicSer salaryBasicSer;
    @Override
    public List<AreaBO> findArea() throws SerException {
        return salaryBasicSer.findArea();
    }

    @Override
    public List<OpinionBO> findThawOpinion() throws SerException {
        return salaryBasicSer.findThawOpinion();
    }

    @Override
    public List<HierarchyBO> findStatus() throws SerException {
        return salaryBasicSer.findStatus();
    }

    @Override
    public List<PositionDetailBO> findPosition() throws SerException {
        return salaryBasicSer.findPosition();
    }

    @Override
    public List<SalaryBasicBO> findSalaryBasic() throws SerException {
        return salaryBasicSer.findSalaryBasic();
    }

    @Override
    public SalaryBasicBO findSalary(SalaryBasicDTO dto) throws SerException {
        return salaryBasicSer.findSalary(dto);
    }

    @Override
    public SalaryBasicBO add(SalaryBasicTO to) throws SerException {
        return salaryBasicSer.add(to);
    }

    @Override
    public SalaryBasicBO edit(SalaryBasicTO to) throws SerException {
        return salaryBasicSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        salaryBasicSer.delete(id);
    }

    @Override
    public void leadExcel(List<SalaryBasicTO> toList) throws SerException {
        salaryBasicSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcel(ExportSalaryBasicTO to) throws SerException {
        return salaryBasicSer.exportExcel(to);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return salaryBasicSer.templateExport();
    }
}