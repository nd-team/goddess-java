package com.bjike.goddess.qualifications.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.qualifications.bo.QualificationsHandleBO;
import com.bjike.goddess.qualifications.dto.QualificationsHandleDTO;
import com.bjike.goddess.qualifications.entity.QualificationsHandle;
import com.bjike.goddess.qualifications.to.QualificationsHandleTO;

import java.util.List;

/**
 * 资质办理管理业务接口
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:15 ]
 * @Description: [ 资质办理管理业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface QualificationsHandleSer extends Ser<QualificationsHandle, QualificationsHandleDTO> {

    default QualificationsHandleBO save(QualificationsHandleTO to) throws SerException {
        return null;
    }

    default QualificationsHandleBO update(QualificationsHandleTO to) throws SerException {
        return null;
    }

    default QualificationsHandleBO delete(String id) throws SerException {
        return null;
    }

    default List<QualificationsHandleBO> findStatus() throws SerException {
        return null;
    }

    default List<QualificationsHandleBO> maps(QualificationsHandleDTO dto) throws SerException {
        return null;
    }
}