package com.bjike.goddess.marketdevelopment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.marketdevelopment.bo.WeekPlanBO;
import com.bjike.goddess.marketdevelopment.dto.WeekPlanDTO;
import com.bjike.goddess.marketdevelopment.service.WeekPlanSer;
import com.bjike.goddess.marketdevelopment.to.WeekPlanTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 周计划业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:49 ]
 * @Description: [ 周计划业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("weekPlanApiImpl")
public class WeekPlanApiImpl implements WeekPlanAPI {

    @Autowired
    private WeekPlanSer weekPlanSer;

    @Override
    public WeekPlanBO save(WeekPlanTO to) throws SerException {
        return weekPlanSer.save(to);
    }

    @Override
    public WeekPlanBO update(WeekPlanTO to) throws SerException {
        return weekPlanSer.update(to);
    }

    @Override
    public WeekPlanBO delete(WeekPlanTO to) throws SerException {
        return weekPlanSer.delete(to);
    }

    @Override
    public List<WeekPlanBO> findByMonth(String month_id) throws SerException {
        return weekPlanSer.findByMonth(month_id);
    }

    @Override
    public List<WeekPlanBO> findByDate(String startDate, String endDate) throws SerException {
        return weekPlanSer.findByDate(startDate, endDate);
    }

    @Override
    public WeekPlanBO getById(String id) throws SerException {
        return weekPlanSer.getById(id);
    }

    @Override
    public List<WeekPlanBO> maps(WeekPlanDTO dto) throws SerException {
        return weekPlanSer.maps(dto);
    }

    @Override
    public Integer getTotal() throws SerException {
        return weekPlanSer.findAll().size();
    }
}