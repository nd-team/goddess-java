package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.QualificationsCollectBO;
import com.bjike.goddess.qualifications.dto.QualificationsCollectDTO;
import com.bjike.goddess.qualifications.entity.QualificationsCollect;
import com.bjike.goddess.qualifications.excel.SonPermissionObject;
import com.bjike.goddess.qualifications.to.GuidePermissionTO;
import com.bjike.goddess.qualifications.to.QualificationsCollectFilterTO;
import com.bjike.goddess.qualifications.to.QualificationsCollectTO;

import java.util.List;

/**
 * 资质办理进度汇总业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-20 10:39 ]
 * @Description: [ 资质办理进度汇总业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QualificationsCollectSer extends Ser<QualificationsCollect, QualificationsCollectDTO> {

    /**
     * 保存
     *
     * @param to 资质办理进度汇总传输对象
     * @return
     * @throws SerException
     */
    default QualificationsCollectBO save(QualificationsCollectTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 资质办理进度汇总传输对象
     * @return
     * @throws SerException
     */
    default QualificationsCollectBO update(QualificationsCollectTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 资质办理进度汇总数据id
     * @return
     * @throws SerException
     */
    default QualificationsCollectBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 根据条件查询
     *
     * @param to 资质办理进度汇总查询条件传输对象
     * @return
     * @throws SerException
     */
    default List<QualificationsCollectBO> findByFilter(QualificationsCollectFilterTO to) throws SerException {
        return null;
    }

    /**
     * 列表
     *
     * @param dto 资质办理进度汇总数据传输对象
     * @return
     * @throws SerException
     */
    default List<QualificationsCollectBO> maps(QualificationsCollectDTO dto) throws SerException {
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
    default QualificationsCollectBO getById(String id) throws SerException {
        return null;
    }

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
     * 获取所有的资质名称
     */
    default List<String> findAllQualifications() throws SerException {
        return null;
    }
}