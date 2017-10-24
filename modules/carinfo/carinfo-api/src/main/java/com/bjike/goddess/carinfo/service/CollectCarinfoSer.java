package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.AreaBO;
import com.bjike.goddess.carinfo.bo.OptionBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.carinfo.entity.CollectCarinfo;
import com.bjike.goddess.carinfo.dto.CollectCarinfoDTO;

import java.util.List;

/**
* 司机信息管理汇总业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-10 05:56 ]
* @Description:	[ 司机信息管理汇总业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CollectCarinfoSer extends Ser<CollectCarinfo, CollectCarinfoDTO> {
    /**
     * 司机信息日汇总
     */
    List<AreaBO> dayCollect(String day) throws SerException;


    /**
     * 司机信息周汇总
     */
    List<AreaBO> weekCollect(Integer year,Integer month,Integer week) throws SerException;

    /**
     * 司机信息月汇总
     */
    List<AreaBO> monthCollect(Integer year,Integer month) throws SerException;

    /**
     * 司机信息累计汇总
     */
    List<AreaBO> allCollect(Integer year) throws SerException;

    /**
     * 图形化司机信息日汇总
     */
    OptionBO figureShowDay(String day) throws SerException;

    /**
     * 图形化司机信息周汇总
     */
    OptionBO figureShowWeek(Integer year,Integer month,Integer week) throws SerException;

    /**
     * 图形化司机信息月汇总
     */
    OptionBO figureShowMonth(Integer year,Integer month) throws SerException;

    /**
     * 图形化累计四季信息汇总
     */
    OptionBO figrureShowTotal(String day) throws SerException;

 }