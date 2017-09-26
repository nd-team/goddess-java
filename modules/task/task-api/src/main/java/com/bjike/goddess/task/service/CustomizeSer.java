package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.bo.CustomizeBO;
import com.bjike.goddess.task.dto.CustomizeDTO;
import com.bjike.goddess.task.entity.Customize;
import com.bjike.goddess.task.to.CustomizeTO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-25 17:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface CustomizeSer extends Ser<Customize, CustomizeDTO> {
    default List<CustomizeBO> list(CustomizeDTO dto) throws SerException {
        return null;
    }
    default Long count(CustomizeDTO dto) throws SerException {
        return null;
    }

    default void add(CustomizeTO to) throws SerException {

    }

    default void enable(String id, boolean enable) throws SerException {

    }


}
