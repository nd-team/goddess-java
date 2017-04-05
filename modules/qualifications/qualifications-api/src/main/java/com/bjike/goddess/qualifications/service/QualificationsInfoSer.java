package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.QualificationsInfoBO;
import com.bjike.goddess.qualifications.dto.QualificationsInfoDTO;
import com.bjike.goddess.qualifications.entity.QualificationsInfo;
import com.bjike.goddess.qualifications.to.QualificationsInfoStatusTO;
import com.bjike.goddess.qualifications.to.QualificationsInfoTO;

import java.util.List;

/**
 * 资质信息管理业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:15 ]
 * @Description: [ 资质信息管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QualificationsInfoSer extends Ser<QualificationsInfo, QualificationsInfoDTO> {

    /**
     * 保存
     *
     * @param to 资质信息管理传输对象
     * @return
     * @throws SerException
     */
    default QualificationsInfoBO save(QualificationsInfoTO to) throws SerException {
        return null;
    }

    /**
     * 修改
     *
     * @param to 资质信息管理传输对象
     * @return
     * @throws SerException
     */
    default QualificationsInfoBO update(QualificationsInfoTO to) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id 资质信息管理id
     * @return
     * @throws SerException
     */
    default QualificationsInfoBO delete(String id) throws SerException {
        return null;
    }

    /**
     * 修改资质信息状态
     *
     * @param to 资质信息管理资质状态传输对象
     * @return
     * @throws SerException
     */
    default QualificationsInfoBO updateStatus(QualificationsInfoStatusTO to) throws SerException {
        return null;
    }

    /**
     * 根据纸质类型查询资质信息
     *
     * @param type 资质类型
     * @return
     * @throws SerException
     */
    default List<QualificationsInfoBO> findByType(String type) throws SerException {
        return null;
    }

    /**
     * 列表数据
     *
     * @param dto 资质信息管理数据传输对象
     * @return
     * @throws SerException
     */
    default List<QualificationsInfoBO> maps(QualificationsInfoDTO dto) throws SerException {
        return null;
    }

}