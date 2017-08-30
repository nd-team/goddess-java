package com.bjike.goddess.archive.service;

import com.bjike.goddess.archive.bo.ArchiveAccessBO;
import com.bjike.goddess.archive.dto.ArchiveAccessDTO;
import com.bjike.goddess.archive.entity.ArchiveAccess;
import com.bjike.goddess.archive.excel.SonPermissionObject;
import com.bjike.goddess.archive.to.AccessAuditTO;
import com.bjike.goddess.archive.to.ArchiveAccessTO;
import com.bjike.goddess.archive.to.GuidePermissionTO;
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

    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 工能导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 保存
     *
     * @param to 档案调阅传输对象
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO save(ArchiveAccessTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 档案调阅传输对象
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO update(ArchiveAccessTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 档案调阅数据对象
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 审核
     *
     * @param to 档案调阅审核传输对象
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO audit(AccessAuditTO to) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 档案调阅数据传输对象
     * @return
     * @throws SerException
     */
    default List<ArchiveAccessBO> maps(ArchiveAccessDTO dto) throws SerException {
        return null;
    }

    /**
     * 根据id获取档案调阅数据
     *
     * @param id 档案调阅数据id
     * @return
     * @throws SerException
     */
    default ArchiveAccessBO getById(String id) throws SerException {
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