package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectcost.bo.CarCostBO;
import com.bjike.goddess.projectcost.dto.CarCostDTO;
import com.bjike.goddess.projectcost.entity.CarCost;
import com.bjike.goddess.projectcost.to.CarCostTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 车辆费用业务实现
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:14 ]
 * @Description: [ 车辆费用业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectcostSerCache")
@Service
public class CarCostSerImpl extends ServiceImpl<CarCost, CarCostDTO> implements CarCostSer {

//    @Autowired
//    private DispatchCarInfoAPI

    @Override
    public CarCostBO save(CarCostTO to) throws SerException {
        CarCost entity = BeanTransform.copyProperties(to, CarCost.class);
        this.countCost(entity);
        super.save(entity);
        return null;
    }

    private void countCost(CarCost entity) throws SerException {
        entity.setBalanceDegree(entity.getTargetDegree() - entity.getActualDegree() + 0d);
        entity.setTargetCost(entity.getTargetDegree() * entity.getUnivalent());
        entity.setActualCost(entity.getActualDegree() * entity.getUnivalent());
        entity.setBalanceCost(entity.getTargetCost() - entity.getActualCost());
    }

    @Override
    public CarCostBO update(CarCostTO to) throws SerException {
        return null;
    }

    @Override
    public CarCostBO delete(String id) throws SerException {
        return null;
    }

    @Override
    public List<CarCostBO> maps(CarCostDTO dto) throws SerException {
        return null;
    }

    @Override
    public CarCostBO updateActual(CarCostTO to) throws SerException {
        return null;
    }

    @Override
    public CarCostBO getById(String id) throws SerException {
        return null;
    }

    @Override
    public Long getTotal() throws SerException {
        return null;
    }
}