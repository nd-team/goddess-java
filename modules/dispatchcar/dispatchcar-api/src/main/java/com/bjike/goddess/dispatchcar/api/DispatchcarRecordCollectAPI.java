package com.bjike.goddess.dispatchcar.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.dispatchcar.bo.AreaCollectBO;
import com.bjike.goddess.dispatchcar.to.GuidePermissionTO;

import java.util.List;

/**
* 出车记录管理汇总业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-27 05:16 ]
* @Description:	[ 出车记录管理汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface DispatchcarRecordCollectAPI  {

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
     * 出车记录管理日汇总
     */
    List<AreaCollectBO> dayCollect(String day) throws SerException;


    /**
     * 出车管理周汇总
     */
    List<AreaCollectBO> weekCollect(Integer year,Integer month,Integer week) throws SerException;

    /**
     * 出车管理月汇总
     */
    List<AreaCollectBO> monthCollect(Integer year,Integer month) throws SerException;

    /**
     * 出车管理累计汇总
     */
    List<AreaCollectBO> allCollect(String day) throws SerException;

}