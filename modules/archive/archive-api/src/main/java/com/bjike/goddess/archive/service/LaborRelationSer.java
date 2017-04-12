package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.LaborRelationBO;
import com.bjike.goddess.archive.dto.LaborRelationDTO;
import com.bjike.goddess.archive.entity.LaborRelation;
import com.bjike.goddess.archive.to.LaborRelationTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * 劳动关系类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:05 ]
 * @Description: [ 劳动关系类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface LaborRelationSer extends Ser<LaborRelation, LaborRelationDTO> {

    default LaborRelationBO save(LaborRelationTO to) throws SerException {
        return null;
    }

    default LaborRelationBO update(LaborRelationTO to) throws SerException {
        return null;
    }

    default LaborRelationBO delete(String id) throws SerException {
        return null;
    }

    default LaborRelationBO congeal(String id) throws SerException {
        return null;
    }

    default LaborRelationBO thaw(String id) throws SerException {
        return null;
    }

    default List<LaborRelationBO> findByStatus(Status status) throws SerException {
        return null;
    }

    default List<LaborRelationBO> maps(LaborRelationDTO dto) throws SerException {
        return null;
    }

}