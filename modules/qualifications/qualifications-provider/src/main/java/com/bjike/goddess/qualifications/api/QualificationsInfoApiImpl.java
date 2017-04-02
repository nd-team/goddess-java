package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.QualificationsInfoBO;
import com.bjike.goddess.qualifications.dto.QualificationsInfoDTO;
import com.bjike.goddess.qualifications.service.QualificationsInfoSer;
import com.bjike.goddess.qualifications.to.QualificationsInfoStatusTO;
import com.bjike.goddess.qualifications.to.QualificationsInfoTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资质信息管理业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:15 ]
 * @Description: [ 资质信息管理业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("qualificationsInfoApiImpl")
public class QualificationsInfoApiImpl implements QualificationsInfoAPI {

    @Autowired
    private QualificationsInfoSer qualificationsInfoSer;

    @Override
    public QualificationsInfoBO save(QualificationsInfoTO to) throws SerException {
        return qualificationsInfoSer.save(to);
    }

    @Override
    public QualificationsInfoBO update(QualificationsInfoTO to) throws SerException {
        return qualificationsInfoSer.update(to);
    }

    @Override
    public QualificationsInfoBO delete(String id) throws SerException {
        return qualificationsInfoSer.delete(id);
    }

    @Override
    public QualificationsInfoBO updateStatus(QualificationsInfoStatusTO to) throws SerException {
        return qualificationsInfoSer.updateStatus(to);
    }

    @Override
    public List<QualificationsInfoBO> findByType(String type) throws SerException {
        return qualificationsInfoSer.findByType(type);
    }

    @Override
    public List<QualificationsInfoBO> maps(QualificationsInfoDTO dto) throws SerException {
        return qualificationsInfoSer.maps(dto);
    }
}