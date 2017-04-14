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

    /**
     * 保存
     *
     * @param to 档案收集传输对象
     * @return
     * @throws SerException
     */
    default ArchiveGatherBO save(ArchiveGatherTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 档案收集传输对象
     * @return
     * @throws SerException
     */
    default ArchiveGatherBO update(ArchiveGatherTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 档案收集传输对象
     * @return
     * @throws SerException
     */
    default ArchiveGatherBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 档案收集数据传输对象
     * @return
     * @throws SerException
     */
    default List<ArchiveGatherBO> maps(ArchiveGatherDTO dto) throws SerException {
        return null;
    }


}