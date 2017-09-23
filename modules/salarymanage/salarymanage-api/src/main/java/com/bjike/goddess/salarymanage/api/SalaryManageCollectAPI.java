package com.bjike.goddess.salarymanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.salarymanage.bo.ManageAreaBO;
import com.bjike.goddess.salarymanage.to.GuidePermissionTO;

import java.util.List;

/**
* 薪资管理汇总业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-19 09:36 ]
* @Description:	[ 薪资管理汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface SalaryManageCollectAPI  {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 日汇总
     */
    List<ManageAreaBO> dayCollect(String theDay) throws SerException;

    /**
     * 周汇总
     */
    List<ManageAreaBO> weekCollect(String theWeek) throws SerException;

    /**
     * 月汇总
     */
    List<ManageAreaBO> monthCollect(String theMonth) throws SerException;

    /**
     * 累计汇总
     */
    List<ManageAreaBO> allCollect(String allDay) throws SerException;

}