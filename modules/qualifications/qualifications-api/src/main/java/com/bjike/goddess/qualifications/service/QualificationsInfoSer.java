package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.QualificationsInfoBO;
import com.bjike.goddess.qualifications.dto.QualificationsInfoDTO;
import com.bjike.goddess.qualifications.entity.QualificationsInfo;
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

    default QualificationsInfoBO save(QualificationsInfoTO to) throws SerException {
        return null;
    }

    default QualificationsInfoBO update(QualificationsInfoTO to) throws SerException {
        return null;
    }

    default QualificationsInfoBO delete(String id) throws SerException{
        return null;
    }

    default List<QualificationsInfoBO> findByType(String type) throws SerException{
        return null;
    }

    default List<QualificationsInfoBO> maps(QualificationsInfoDTO dto) throws SerException{
        return null;
    }

}