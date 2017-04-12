package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.EnclosureTypeBO;
import com.bjike.goddess.archive.dto.EnclosureTypeDTO;
import com.bjike.goddess.archive.entity.EnclosureType;
import com.bjike.goddess.archive.to.EnclosureTypeTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;

import java.util.List;

/**
 * 附件类型业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 11:08 ]
 * @Description: [ 附件类型业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface EnclosureTypeSer extends Ser<EnclosureType, EnclosureTypeDTO> {

    default EnclosureTypeBO save(EnclosureTypeTO to) throws SerException {
        return null;
    }

    default EnclosureTypeBO update(EnclosureTypeTO to) throws SerException {
        return null;
    }

    default EnclosureTypeBO delete(String id) throws SerException {
        return null;
    }

    default EnclosureTypeBO congeal(String id) throws SerException {
        return null;
    }

    default EnclosureTypeBO thaw(String id) throws SerException {
        return null;
    }

    default List<EnclosureTypeBO> findByStatus(Status status) throws SerException {
        return null;
    }

    default List<EnclosureTypeBO> maps(EnclosureTypeDTO dto) throws SerException {
        return null;
    }

}