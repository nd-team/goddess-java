package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ForeignStaffingSetBO;
import com.bjike.goddess.archive.dto.ForeignStaffingSetDTO;
import com.bjike.goddess.archive.entity.ForeignStaffingSet;
import com.bjike.goddess.archive.to.ForeignStaffingSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * 对外人员基本信息设置业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:28 ]
 * @Description: [ 对外人员基本信息设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ForeignStaffingSetSer extends Ser<ForeignStaffingSet, ForeignStaffingSetDTO> {

    default ForeignStaffingSetBO save(ForeignStaffingSetTO to) throws SerException {
        return null;
    }

    default ForeignStaffingSetBO update(ForeignStaffingSetTO to) throws SerException {
        return null;
    }

    default ForeignStaffingSetBO delete(String id) throws SerException {
        return null;
    }

    default ForeignStaffingSetBO congeal(String id) throws SerException {
        return null;
    }

    default ForeignStaffingSetBO thaw(String id) throws SerException {
        return null;
    }

    default List<ForeignStaffingSetBO> findByStatus(Status status) throws SerException {
        return null;
    }

    default List<ForeignStaffingSetBO> maps(ForeignStaffingSetDTO dto) throws SerException {
        return null;
    }

}