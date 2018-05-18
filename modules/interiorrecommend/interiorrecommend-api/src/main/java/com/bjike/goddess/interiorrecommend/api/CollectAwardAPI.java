package com.bjike.goddess.interiorrecommend.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.interiorrecommend.bo.CollectAwardBO;
import com.bjike.goddess.interiorrecommend.excel.SonPermissionObject;
import com.bjike.goddess.interiorrecommend.to.GuidePermissionTO;

import java.util.List;

/**
* 内部推荐奖管理汇总业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-09-12 11:43 ]
* @Description:	[ 内部推荐奖管理汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectAwardAPI  {

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 根据月份来汇总
     */
    CollectAwardBO collectByMonth(String year,String month) throws SerException;

    /**
     * 累计汇总
     */
    CollectAwardBO allCollect(String today) throws SerException;
}