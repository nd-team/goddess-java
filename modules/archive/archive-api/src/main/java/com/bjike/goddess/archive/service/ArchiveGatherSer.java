package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveGatherBO;
import com.bjike.goddess.archive.dto.ArchiveGatherDTO;
import com.bjike.goddess.archive.entity.ArchiveGather;
import com.bjike.goddess.archive.to.ArchiveGatherTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 档案收集业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 02:22 ]
 * @Description: [ 档案收集业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ArchiveGatherSer extends Ser<ArchiveGather, ArchiveGatherDTO> {

    default ArchiveGatherBO save(ArchiveGatherTO to) throws SerException {
        return null;
    }

    default ArchiveGatherBO update(ArchiveGatherTO to) throws SerException {
        return null;
    }

    default ArchiveGatherBO delete(String id) throws SerException {
        return null;
    }

    default List<ArchiveGatherBO> maps(ArchiveGatherDTO dto) throws SerException {
        return null;
    }


}