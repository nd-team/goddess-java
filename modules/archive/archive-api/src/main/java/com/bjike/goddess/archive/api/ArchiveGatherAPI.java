package com.bjike.goddess.archive.api;

import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.bo.ArchiveGatherBO;
import com.bjike.goddess.archive.dto.ArchiveGatherDTO;
import com.bjike.goddess.archive.to.ArchiveGatherTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;

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
public interface ArchiveGatherAPI {


    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
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

    /**
     * 根据id获取档案收集数据
     *
     * @param id 档案收集数据id
     * @return
     * @throws SerException
     */
    default ArchiveGatherBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTotal() throws SerException {
        return null;
    }
}