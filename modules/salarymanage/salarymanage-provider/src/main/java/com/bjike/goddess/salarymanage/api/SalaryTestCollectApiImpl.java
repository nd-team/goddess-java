package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salarymanage.bo.AreaBO;
import com.bjike.goddess.salarymanage.dto.SalaryTestCollectDTO;
import com.bjike.goddess.salarymanage.service.SalaryTestCollectSer;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryTestCollectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 薪资测算汇总表业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-18 11:50 ]
* @Description:	[ 薪资测算汇总表业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("salaryTestCollectApiImpl")
public class SalaryTestCollectApiImpl implements SalaryTestCollectAPI  {

    @Autowired
    private SalaryTestCollectSer salaryTestCollectSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return salaryTestCollectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return salaryTestCollectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<AreaBO> pageList(SalaryTestCollectDTO dto) throws SerException {
        return salaryTestCollectSer.pageList(dto);
    }

    @Override
    public void makeSalary(SalaryTestCollectTO to) throws SerException {
        salaryTestCollectSer.makeSalary(to);
    }
}