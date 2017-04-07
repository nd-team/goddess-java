package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.AttainmentWayBO;
import com.bjike.goddess.attainment.dto.AttainmentWayDTO;
import com.bjike.goddess.attainment.entity.AttainmentWay;
import com.bjike.goddess.attainment.to.AttainmentWayTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 调研方式业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 09:51 ]
 * @Description: [ 调研方式业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AttainmentWaySer extends Ser<AttainmentWay, AttainmentWayDTO> {

    default AttainmentWayBO save(AttainmentWayTO to) throws SerException {
        return null;
    }

    default AttainmentWayBO update(AttainmentWayTO to) throws SerException {
        return null;
    }

    default AttainmentWayBO delete(String id) throws SerException {
        return null;
    }

    default AttainmentWayBO congeal(String id) throws SerException {
        return null;
    }

    default AttainmentWayBO thaw(String id) throws SerException {
        return null;
    }

    default List<AttainmentWayBO> findThaw() throws SerException {
        return null;
    }

}