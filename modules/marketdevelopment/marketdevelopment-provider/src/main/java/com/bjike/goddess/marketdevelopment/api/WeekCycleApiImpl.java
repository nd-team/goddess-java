package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.WeekMonthMoneyBO;
import com.bjike.goddess.marketdevelopment.dto.WeekCycleDTO;
import com.bjike.goddess.marketdevelopment.service.WeekCycleSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 周计划的周期业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-12-02 05:37 ]
* @Description:	[ 周计划的周期业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("weekCycleApiImpl")
public class WeekCycleApiImpl implements WeekCycleAPI  {
    @Autowired
    private WeekCycleSer weekCycleSer;

    @Override
    public List<WeekMonthMoneyBO> maps(WeekCycleDTO dto) throws SerException {
        return weekCycleSer.maps(dto);
    }
}