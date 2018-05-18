package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.WeekCycleBO;
import com.bjike.goddess.marketdevelopment.bo.WeekMonthMoneyBO;
import com.bjike.goddess.marketdevelopment.dto.WeekCycleDTO;
import com.bjike.goddess.marketdevelopment.service.WeekCycleSer;
import com.bjike.goddess.marketdevelopment.to.GuidePermissionTO;
import com.bjike.goddess.marketdevelopment.to.WeekCycleTO;
import com.bjike.goddess.marketdevelopment.to.WeekCycleUpdateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 周计划的周期业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-02 05:37 ]
 * @Description: [ 周计划的周期业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("weekCycleApiImpl")
public class WeekCycleApiImpl implements WeekCycleAPI {
    @Autowired
    private WeekCycleSer weekCycleSer;

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return weekCycleSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<WeekMonthMoneyBO> maps(WeekCycleDTO dto) throws SerException {
        return weekCycleSer.maps(dto);
    }

    @Override
    public void save(WeekCycleTO to) throws SerException {
        weekCycleSer.save(to);
    }

    @Override
    public void update(WeekCycleUpdateTO to) throws SerException {
        weekCycleSer.update(to);
    }

    @Override
    public void delete(String weekCycleId) throws SerException {
        weekCycleSer.delete(weekCycleId);
    }

    @Override
    public List<WeekCycleBO> getById(String weekCycleId) throws SerException {
        return weekCycleSer.getById(weekCycleId);
    }

    @Override
    public Long getTotal(WeekCycleDTO dto) throws SerException {
        return weekCycleSer.getTotal(dto);
    }

    @Override
    public List<String> getCycle(String year, String month) throws SerException {
        return weekCycleSer.getCycle(year, month);
    }
}