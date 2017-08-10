package com.bjike.goddess.projectroyalty.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.projectroyalty.bo.TargetAuotaBO;
import com.bjike.goddess.projectroyalty.dto.TargetAuotaDTO;
import com.bjike.goddess.projectroyalty.entity.TargetAuota;
import com.bjike.goddess.projectroyalty.to.GuidePermissionTO;
import com.bjike.goddess.projectroyalty.to.TargetAuotaTO;

import java.util.List;

/**
 * 项目提成目标定额业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:27 ]
 * @Description: [ 项目提成目标定额业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TargetAuotaSer extends Ser<TargetAuota, TargetAuotaDTO> {

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
     * 保存项目提成目标定额
     *
     * @param to 项目提成目标定额传输对象
     * @return
     * @throws SerException
     */
    default TargetAuotaBO targetSave(TargetAuotaTO to) throws SerException {
        return null;
    }

    /**
     * 保存项目提成实际定额
     *
     * @param to 项目提成目标定额传输对象
     * @return
     * @throws SerException
     */
    default TargetAuotaBO actualSave(TargetAuotaTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 项目提成目标定额传输对象
     * @return
     * @throws SerException
     */
    default TargetAuotaBO update(TargetAuotaTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 项目提成目标定额数据id
     * @return
     * @throws SerException
     */
    default TargetAuotaBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据id获取业务提成定额数据
     *
     * @param id 项目提成目标定额数据id
     * @return
     * @throws SerException
     */
    default TargetAuotaBO getById(String id) throws SerException {
        return null;
    }

    /**
     * 项目提成目标定额列表
     *
     * @param dto 项目提成目标定额数据传输对象
     * @return
     * @throws SerException
     */
    default List<TargetAuotaBO> targetMaps(TargetAuotaDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目提成实际定额列表
     *
     * @param dto 项目提成目标定额数据传输对象
     * @return
     * @throws SerException
     */
    default List<TargetAuotaBO> actualMaps(TargetAuotaDTO dto) throws SerException {
        return null;
    }

    /**
     * 项目提成目标定额总条数
     *
     * @return
     * @throws SerException
     */
    default Long getTargetTotal() throws SerException {
        return null;
    }

    /**
     * 项目提成实际定额总条数
     *
     * @return
     * @throws SerException
     */
    default Long getActualTotal() throws SerException {
        return null;
    }

    /**
     * 查询是否有权重依赖
     */
    Boolean isDependent(String id) throws SerException;

}