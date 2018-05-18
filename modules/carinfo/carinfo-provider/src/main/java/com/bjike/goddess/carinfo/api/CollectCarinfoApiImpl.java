package com.bjike.goddess.carinfo.api;

import com.bjike.goddess.carinfo.bo.AreaBO;
import com.bjike.goddess.carinfo.bo.OptionBO;
import com.bjike.goddess.carinfo.service.CollectCarinfoSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 司机信息管理汇总业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-10-10 05:56 ]
* @Description:	[ 司机信息管理汇总业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("collectCarinfoApiImpl")
public class CollectCarinfoApiImpl implements CollectCarinfoAPI  {

    @Autowired
    private CollectCarinfoSer collectCarinfoSer;

    @Override
    public List<AreaBO> dayCollect(String day) throws SerException {
        return collectCarinfoSer.dayCollect(day);
    }

    @Override
    public List<AreaBO> weekCollect(Integer year,Integer month,Integer week) throws SerException {
        return collectCarinfoSer.weekCollect(year,month,week);
    }

    @Override
    public List<AreaBO> monthCollect(Integer year, Integer month) throws SerException {
        return collectCarinfoSer.monthCollect(year,month);
    }

    @Override
    public List<AreaBO> allCollect(String endDate) throws SerException {
        return collectCarinfoSer.allCollect(endDate);
    }

    @Override
    public OptionBO figureShowDay(String day) throws SerException {
        return collectCarinfoSer.figureShowDay(day);
    }

    @Override
    public OptionBO figureShowWeek(Integer year, Integer month, Integer week) throws SerException {
        return collectCarinfoSer.figureShowWeek(year,month,week);
    }

    @Override
    public OptionBO figureShowMonth(Integer year, Integer month) throws SerException {
        return collectCarinfoSer.figureShowMonth(year,month);
    }

    @Override
    public OptionBO figrureShowTotal(String day) throws SerException {
        return collectCarinfoSer.figrureShowTotal(day);
    }
}