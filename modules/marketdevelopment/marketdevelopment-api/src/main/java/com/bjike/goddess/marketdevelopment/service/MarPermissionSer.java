package com.bjike.goddess.marketdevelopment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.marketdevelopment.bo.MarPermissionBO;
import com.bjike.goddess.marketdevelopment.dto.MarPermissionDTO;
import com.bjike.goddess.marketdevelopment.entity.MarPermission;
import com.bjike.goddess.marketdevelopment.to.MarPermissionTO;
import com.bjike.goddess.organize.bo.OpinionBO;

import java.util.List;

/**
 * 市场计划进度管理权限配置业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-31 11:37 ]
 * @Description: [ 市场计划进度管理权限配置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface MarPermissionSer extends Ser<MarPermission, MarPermissionDTO> {

    /**
     * 市场计划进度管理权限列表总条数
     */
    default Long countPermission(MarPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 市场计划进度管理权限列表
     *
     * @param cusPermissionDTO 市场计划进度管理权限数据
     * @return MarPermissionBO
     * @throws SerException
     */
    default List<MarPermissionBO> list(MarPermissionDTO cusPermissionDTO) throws SerException {
        return null;
    }

    /**
     * 一个市场计划进度管理权限
     *
     * @param id 市场计划进度管理权限id
     * @return MarPermissionBO
     * @throws SerException
     */
    default MarPermissionBO getOneById(String id) throws SerException {
        return null;
    }


    /**
     * 市场计划进度管理权限操作者
     *
     * @param id 市场计划进度管理权限id
     * @return OpinionBO 返回数据
     * @throws SerException
     */
    default List<OpinionBO> listOperateById(String id) throws SerException {
        return null;
    }

    /**
     * 添加市场计划进度管理权限
     *
     * @param cusPermissionTO 市场计划进度管理权限数据
     * @return MarPermissionBO
     * @throws SerException
     */
    default MarPermissionBO add(List<MarPermissionTO> cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 编辑市场计划进度管理权限
     *
     * @param cusPermissionTO 市场计划进度管理权限数据
     * @return MarPermissionBO
     * @throws SerException
     */
    default MarPermissionBO edit(MarPermissionTO cusPermissionTO) throws SerException {
        return null;
    }

    /**
     * 检查权限
     *
     * @param idFlag 辅助id
     * @return
     * @throws SerException
     */
    default Boolean getMarPermission(String idFlag) throws SerException {
        return null;
    }

}