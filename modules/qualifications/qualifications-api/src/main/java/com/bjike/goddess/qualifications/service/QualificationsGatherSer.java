package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.AuditMaterialBO;
import com.bjike.goddess.qualifications.bo.QualificationsGatherBO;
import com.bjike.goddess.qualifications.dto.QualificationsGatherDTO;
import com.bjike.goddess.qualifications.entity.QualificationsGather;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.qualifications.to.QualificationsGatherTO;

import java.util.List;

/**
 * 资质办理信息采集业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:33 ]
 * @Description: [ 资质办理信息采集业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QualificationsGatherSer extends Ser<QualificationsGather, QualificationsGatherDTO> {

    /**
     * 保存
     *
     * @param to 资质办理传输对象
     * @return
     * @throws SerException
     */
    default QualificationsGatherBO save(QualificationsGatherTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 资质办理传输对象
     * @return
     * @throws SerException
     */
    default QualificationsGatherBO update(QualificationsGatherTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 资质办理传输对象
     * @return
     * @throws SerException
     */
    default QualificationsGatherBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据类型查询资质办理数据
     *
     * @param type 资质类型
     * @return
     * @throws SerException
     */
    default List<QualificationsGatherBO> findByType(String type) throws SerException {
        return null;
    }

    /**
     * 列表数据
     *
     * @param dto 资质办理信息采集数据传输对象
     * @return
     * @throws SerException
     */
    default List<QualificationsGatherBO> maps(QualificationsGatherDTO dto) throws SerException {
        return null;
    }

    /**
     * 获取总条数
     *
     * @return
     * @throws SerException
     */
    default Integer getTotal() throws SerException {
        return null;
    }

    /**
     * 根据id获取数据
     *
     * @param id 数据id
     * @return
     * @throws SerException
     */
    default QualificationsGatherBO getById(String id) throws SerException {
        return null;
    }

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
}