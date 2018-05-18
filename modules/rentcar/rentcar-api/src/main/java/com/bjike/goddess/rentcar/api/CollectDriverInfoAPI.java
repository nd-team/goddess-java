package com.bjike.goddess.rentcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rentcar.bo.AreaBO;
import com.bjike.goddess.rentcar.bo.CollectDriverInfoBO;
import com.bjike.goddess.rentcar.bo.DepartmentBO;
import com.bjike.goddess.rentcar.bo.OptionBO;
import com.bjike.goddess.rentcar.to.GuidePermissionTO;

import java.util.List;

/**
* 租车协议管理汇总业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-07 11:56 ]
* @Description:	[ 租车协议管理汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectDriverInfoAPI  {

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
     * 月租车协议管理汇总
     */
    List<AreaBO> monthCollect(Integer year, Integer month) throws SerException;

    /**
     * 累计租车协议管理汇总
     */
    List<AreaBO> allCollect(String endDate) throws SerException;

    /**
     * 图形化月租车汇总
     */
    OptionBO figureShowMonth(Integer year,Integer month) throws SerException;

    /**
     * 图形化累计租车汇总
     */
    OptionBO figureShowTotal(String endDate) throws SerException;

}