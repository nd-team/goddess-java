package com.bjike.goddess.projectcost.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectcost.bo.CostControlBO;
import com.bjike.goddess.projectcost.dto.CostControlDTO;
import com.bjike.goddess.projectcost.entity.CostControl;
import com.bjike.goddess.projectcost.to.CostControlTO;

import java.util.List;

/**
 * 项目成本控制业务接口
 *
 * @Author: [ 邓钧仁 ]
 * @Date: [ 2017-05-04 05:56 ]
 * @Description: [ 项目成本控制业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CostControlSer extends Ser<CostControl, CostControlDTO> {

    default CostControlBO save(CostControlTO to) throws SerException {
        return null;
    }

    default CostControlBO update(CostControlTO to) throws SerException {
        return null;
    }

    default CostControlBO delete(String id) throws SerException {
        return null;
    }

    default CostControlBO updateActual(CostControlTO to) throws SerException {
        return null;
    }

    default List<CostControlBO> maps(CostControlDTO dto) throws SerException {
        return null;
    }

    default CostControlBO getById(String id) throws SerException {
        return null;
    }

    default Long getTotal() throws SerException {
        return null;
    }

}