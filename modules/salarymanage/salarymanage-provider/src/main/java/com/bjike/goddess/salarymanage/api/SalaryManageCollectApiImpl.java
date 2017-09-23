package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salarymanage.bo.ManageAreaBO;
import com.bjike.goddess.salarymanage.entity.SalaryManageCollect;
import com.bjike.goddess.salarymanage.service.SalaryManageCollectSer;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 薪资管理汇总业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 09:36 ]
* @Description:	[ 薪资管理汇总业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("salaryManageCollectApiImpl")
public class SalaryManageCollectApiImpl implements SalaryManageCollectAPI  {
    @Autowired
    private SalaryManageCollectSer salaryManageCollectSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return salaryManageCollectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return salaryManageCollectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<ManageAreaBO> dayCollect(String theDay) throws SerException {
        return salaryManageCollectSer.dayCollect(theDay);
    }

    @Override
    public List<ManageAreaBO> weekCollect(String theWeek) throws SerException {
        return salaryManageCollectSer.weekCollect(theWeek);
    }

    @Override
    public List<ManageAreaBO> monthCollect(String theMonth) throws SerException {
        return salaryManageCollectSer.monthCollect(theMonth);
    }

    @Override
    public List<ManageAreaBO> allCollect(String allDay) throws SerException {
        return salaryManageCollectSer.allCollect(allDay);
    }
}