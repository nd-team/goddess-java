package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.ArrivalMonthBO;
import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.bo.ArrivalWeekCountBO;
import com.bjike.goddess.budget.dto.ArrivalMonthDTO;
import com.bjike.goddess.budget.dto.ArrivalWeekDTO;
import com.bjike.goddess.budget.service.ArrivalWeekSer;
import com.bjike.goddess.budget.to.ArrivalWeekTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区收入周业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:03 ]
 * @Description: [ 地区收入周业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("arrivalWeekApiImpl")
public class ArrivalWeekApiImpl implements ArrivalWeekAPI {
    @Autowired
    private ArrivalWeekSer arrivalWeekSer;

    @Override
    public ArrivalWeekBO save(ArrivalWeekTO to) throws SerException {
        return arrivalWeekSer.save(to);
    }

    @Override
    public void edit(ArrivalWeekTO to) throws SerException {
        arrivalWeekSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        arrivalWeekSer.delete(id);
    }

    @Override
    public List<ArrivalWeekBO> list(ArrivalWeekDTO dto) throws SerException {
        return arrivalWeekSer.list(dto);
    }

    @Override
    public ArrivalWeekBO findByID(String id) throws SerException {
        return arrivalWeekSer.findByID(id);
    }

    @Override
    public List<ArrivalWeekCountBO> count() throws SerException {
        return arrivalWeekSer.count();
    }

    @Override
    public List<ArrivalWeekCountBO> conditionsCount(String[] arrivals) throws SerException {
        return arrivalWeekSer.conditionsCount(arrivals);
    }

    @Override
    public Long countNum(ArrivalWeekDTO dto) throws SerException {
        return arrivalWeekSer.countNum(dto);
    }
}