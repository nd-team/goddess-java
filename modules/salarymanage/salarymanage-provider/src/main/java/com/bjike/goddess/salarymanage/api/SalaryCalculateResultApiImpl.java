package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.salarymanage.bo.ResultAreaBO;
import com.bjike.goddess.salarymanage.dto.SalaryCalculateResultDTO;
import com.bjike.goddess.salarymanage.entity.SalaryCalculateResult;
import com.bjike.goddess.salarymanage.service.SalaryCalculateResultSer;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import com.bjike.goddess.salarymanage.to.SalaryCalculateResultTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
* 薪资测算结果业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 01:59 ]
* @Description:	[ 薪资测算结果业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("salaryCalculateResultApiImpl")
public class SalaryCalculateResultApiImpl implements SalaryCalculateResultAPI  {

    @Autowired
    private SalaryCalculateResultSer salaryCalculateResultSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return salaryCalculateResultSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return salaryCalculateResultSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<ResultAreaBO> pageList() throws SerException {
        return salaryCalculateResultSer.pageList();
    }

    @Override
    public void makeShare(SalaryCalculateResultTO to) throws SerException {
        salaryCalculateResultSer.makeShare(to);
    }
}