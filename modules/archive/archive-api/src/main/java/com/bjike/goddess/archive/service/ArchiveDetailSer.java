package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveDetailBO;
import com.bjike.goddess.archive.dto.ArchiveDetailDTO;
import com.bjike.goddess.archive.entity.ArchiveDetail;
import com.bjike.goddess.archive.to.ArchiveDetailTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 档案明细业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:05 ]
 * @Description: [ 档案明细业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ArchiveDetailSer extends Ser<ArchiveDetail, ArchiveDetailDTO> {

    default ArchiveDetailBO save(ArchiveDetailTO to) throws SerException {
        return null;
    }

    default ArchiveDetailBO update(ArchiveDetailTO to) throws SerException {
        return null;
    }

    default ArchiveDetailBO delete(String id) throws SerException {
        return null;
    }

    default ArchiveDetailBO findByUsername(String username) throws SerException {
        return null;
    }

    default List<ArchiveDetailBO> maps(ArchiveDetailDTO dto) throws SerException {
        return null;
    }

}