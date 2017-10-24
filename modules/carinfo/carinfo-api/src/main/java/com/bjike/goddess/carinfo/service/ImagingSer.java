package com.bjike.goddess.carinfo.service;

import com.bjike.goddess.carinfo.bo.ImagingTopBO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.carinfo.entity.Imaging;
import com.bjike.goddess.carinfo.dto.ImagingDTO;

import java.util.List;

/**
* 图形化业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-12 02:30 ]
* @Description:	[ 图形化业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface ImagingSer extends Ser<Imaging, ImagingDTO> {
    /**
     * 图形化司机管理日汇总
     */
    ImagingTopBO dayCollect(String day) throws SerException;

    /**
     * 图形化司机管理周汇总
     */
    ImagingTopBO weekCollect(Integer year,Integer month,Integer week) throws SerException;


    /**
     * 图形化司机管理月汇总
     */
    ImagingTopBO monthCollect(Integer year,Integer month) throws SerException;

    /**
     * 图形化管理累计汇总
     */
    ImagingTopBO allCollect(Integer year) throws SerException;
 }