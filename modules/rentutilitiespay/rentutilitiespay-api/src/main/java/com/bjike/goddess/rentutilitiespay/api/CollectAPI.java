package com.bjike.goddess.rentutilitiespay.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.rentutilitiespay.bo.AreaCollectBO;

import java.util.List;

/**
* 汇总业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-20 03:59 ]
* @Description:	[ 汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectAPI  {
    /**
     * 月汇总
     */
    List<AreaCollectBO> monthCollect(Integer year,Integer month) throws SerException;

    /**
     * 年汇总
     */
    List<AreaCollectBO> yeareCollect(Integer year) throws SerException;

    /**
     * 累计汇总
     */
    List<AreaCollectBO> allCollect(String day) throws SerException;

}