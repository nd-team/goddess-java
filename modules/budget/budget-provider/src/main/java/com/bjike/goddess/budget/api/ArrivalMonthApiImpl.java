package com.bjike.goddess.budget.api;

import com.bjike.goddess.budget.bo.ArrivalMonthBO;
import com.bjike.goddess.budget.bo.ArrivalMonthCountBO;
import com.bjike.goddess.budget.bo.ArrivalWeekBO;
import com.bjike.goddess.budget.dto.ArrivalMonthDTO;
import com.bjike.goddess.budget.service.ArrivalMonthSer;
import com.bjike.goddess.budget.to.ArrivalMonthTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 地区收入月业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-02 04:06 ]
 * @Description: [ 地区收入月业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("arrivalMonthApiImpl")
public class ArrivalMonthApiImpl implements ArrivalMonthAPI {
    @Autowired
    private ArrivalMonthSer arrivalMonthSer;

    @Override
    public ArrivalMonthBO save(ArrivalMonthTO to) throws SerException {
        return arrivalMonthSer.save(to);
    }

    @Override
    public void edit(ArrivalMonthTO to) throws SerException {
        arrivalMonthSer.edit(to);
    }

    @Override
    public void deleteAll() throws SerException {
        arrivalMonthSer.deleteAll();
    }

    @Override
    public List<ArrivalMonthBO> list(ArrivalMonthDTO dto) throws SerException {
        return arrivalMonthSer.list(dto);
    }

    @Override
    public ArrivalMonthBO findByID(String id) throws SerException {
        return arrivalMonthSer.findByID(id);
    }

    @Override
    public List<ArrivalMonthCountBO> count() throws SerException {
        return arrivalMonthSer.count();
    }

    @Override
    public List<ArrivalMonthCountBO> conditionsCount(String[] arrivals) throws SerException {
        return arrivalMonthSer.conditionsCount(arrivals);
    }

    @Override
    public List<ArrivalWeekBO> findDetail(String id) throws SerException {
        return arrivalMonthSer.findDetail(id);
    }
}