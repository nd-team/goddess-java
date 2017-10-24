package com.bjike.goddess.carinfo.api;

import com.bjike.goddess.carinfo.bo.ImagingTopBO;
import com.bjike.goddess.carinfo.service.ImagingSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 图形化业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-12 02:30 ]
* @Description:	[ 图形化业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("imagingApiImpl")
public class ImagingApiImpl implements ImagingAPI  {

    @Autowired
    private ImagingSer imagingSer;

    @Override
    public ImagingTopBO dayCollect(String day) throws SerException {
        return imagingSer.dayCollect(day);
    }

    @Override
    public ImagingTopBO weekCollect(Integer year, Integer month, Integer week) throws SerException {
        return imagingSer.weekCollect(year,month,week);
    }

    @Override
    public ImagingTopBO monthCollect(Integer year, Integer month) throws SerException {
        return imagingSer.monthCollect(year,month);
    }

    @Override
    public ImagingTopBO allCollect(Integer year) throws SerException {
        return imagingSer.allCollect(year);
    }
}