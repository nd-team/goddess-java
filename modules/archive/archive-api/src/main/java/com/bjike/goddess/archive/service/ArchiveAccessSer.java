package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.entity.ArchiveAccess;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 档案调阅业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:03 ]
 * @Description: [ 档案调阅业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ArchiveAccessSer extends Ser<ArchiveAccess, ArchiveAccessDTO> {

    default ArchiveAccessBO save(ArchiveAccessTO to) throws SerException {
        return null;
    }

    default ArchiveAccessBO update(ArchiveAccessTO to) throws SerException {
        return null;
    }

    default ArchiveAccessBO delete(String id) throws SerException {
        return null;
    }

    default ArchiveAccessBO audit(ArchiveAccessTO to) throws SerException {
        return null;
    }

    default List<ArchiveAccessBO> maps(ArchiveAccessDTO dto) throws SerException {
        return null;
    }

}