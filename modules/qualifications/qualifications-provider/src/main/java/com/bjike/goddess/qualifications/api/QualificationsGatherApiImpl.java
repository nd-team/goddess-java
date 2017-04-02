package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.QualificationsGatherBO;
import com.bjike.goddess.qualifications.dto.QualificationsGatherDTO;
import com.bjike.goddess.qualifications.service.QualificationsGatherSer;
import com.bjike.goddess.qualifications.to.QualificationsGatherTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资质办理信息采集业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 04:33 ]
 * @Description: [ 资质办理信息采集业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("qualificationsGatherApiImpl")
public class QualificationsGatherApiImpl implements QualificationsGatherAPI {

    @Autowired
    private QualificationsGatherSer qualificationsGatherSer;

    @Override
    public QualificationsGatherBO save(QualificationsGatherTO to) throws SerException {
        return qualificationsGatherSer.save(to);
    }

    @Override
    public QualificationsGatherBO update(QualificationsGatherTO to) throws SerException {
        return qualificationsGatherSer.update(to);
    }

    @Override
    public QualificationsGatherBO delete(String id) throws SerException {
        return qualificationsGatherSer.delete(id);
    }

    @Override
    public List<QualificationsGatherBO> findByType(String type) throws SerException {
        return qualificationsGatherSer.findByType(type);
    }

    @Override
    public List<QualificationsGatherBO> maps(QualificationsGatherDTO dto) throws SerException {
        return qualificationsGatherSer.maps(dto);
    }
}