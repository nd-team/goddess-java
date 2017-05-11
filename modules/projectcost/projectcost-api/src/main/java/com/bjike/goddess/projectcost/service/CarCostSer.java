package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectcost.bo.ArtificialCostBO;
import com.bjike.goddess.projectcost.bo.CarCostBO;
import com.bjike.goddess.projectcost.dto.CarCostDTO;
import com.bjike.goddess.projectcost.entity.CarCost;
import com.bjike.goddess.projectcost.to.CarCostTO;
import com.bjike.goddess.projectcost.to.FindTO;

import java.util.List;

/**
 * 车辆费用业务接口
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:14 ]
 * @Description: [ 车辆费用业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CarCostSer extends Ser<CarCost, CarCostDTO> {

    /**
     * 保存
     *
     * @param to 车辆费用传输对象
     * @return
     * @throws SerException
     */
    default CarCostBO save(CarCostTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 车辆费用传输对象
     * @return
     * @throws SerException
     */
    default CarCostBO update(CarCostTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 车辆费用数据id
     * @return
     * @throws SerException
     */
    default CarCostBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 车辆费用数据传输对象
     * @return
     * @throws SerException
     */
    default List<CarCostBO> maps(CarCostDTO dto) throws SerException {
        return null;
    }

    /**
     * 修改实际车次
     *
     * @param to 车辆费用传输对象
     * @return
     * @throws SerException
     */
    default CarCostBO updateActual(CarCostTO to) throws SerException {
        return null;
    }

    /**
     * 根据id查询数据
     *
     * @param id 车辆费用数据id
     * @return
     * @throws SerException
     */
    default CarCostBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }

    /**
     * 根据查询条件查询数据
     *
     * @param to 查询条件传输对象
     * @return
     * @throws SerException
     */
    default List<CarCostBO> findByTO(FindTO to) throws SerException {
        return null;
    }
}