package com.bjike.goddess.projectcost.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectcost.bo.CarCostBO;
import com.bjike.goddess.projectcost.dto.CarCostDTO;
import com.bjike.goddess.projectcost.service.CarCostSer;
import com.bjike.goddess.projectcost.to.CarCostTO;
import com.bjike.goddess.projectcost.to.FindTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆费用业务接口实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:14 ]
 * @Description: [ 车辆费用业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("carCostApiImpl")
public class CarCostApiImpl implements CarCostAPI {

    @Autowired
    private CarCostSer carCostSer;

    @Override
    public CarCostBO save(CarCostTO to) throws SerException {
        return carCostSer.save(to);
    }

    @Override
    public CarCostBO update(CarCostTO to) throws SerException {
        return carCostSer.update(to);
    }

    @Override
    public CarCostBO delete(String id) throws SerException {
        return carCostSer.delete(id);
    }

    @Override
    public List<CarCostBO> maps(CarCostDTO dto) throws SerException {
        return carCostSer.maps(dto);
    }

    @Override
    public CarCostBO updateActual(CarCostTO to) throws SerException {
        return carCostSer.updateActual(to);
    }

    @Override
    public CarCostBO getById(String id) throws SerException {
        return carCostSer.getById(id);
    }

    @Override
    public Long getTotal() throws SerException {
        return carCostSer.getTotal();
    }

    @Override
    public List<CarCostBO> findByTO(FindTO to) throws SerException {
        return carCostSer.findByTO(to);
    }
}