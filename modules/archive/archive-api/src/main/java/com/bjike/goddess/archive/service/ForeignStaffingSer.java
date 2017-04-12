package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ForeignStaffingBO;
import com.bjike.goddess.archive.dto.ForeignStaffingDTO;
import com.bjike.goddess.archive.entity.ForeignStaffing;
import com.bjike.goddess.archive.to.ForeignStaffingTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 对外人员信息业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 03:09 ]
 * @Description: [ 对外人员信息业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ForeignStaffingSer extends Ser<ForeignStaffing, ForeignStaffingDTO> {

    default ForeignStaffingBO save(ForeignStaffingTO to) throws SerException {
        return null;
    }

    default ForeignStaffingBO update(ForeignStaffingTO to) throws SerException {
        return null;
    }

    default ForeignStaffingBO delete(String id) throws SerException {
        return null;
    }

    default List<ForeignStaffingBO> maps(ForeignStaffingDTO dto) throws SerException {
        return null;
    }

}