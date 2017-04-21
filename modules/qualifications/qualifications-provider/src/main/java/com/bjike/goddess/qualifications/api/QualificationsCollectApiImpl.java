package com.bjike.goddess.qualifications.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.qualifications.bo.QualificationsCollectBO;
import com.bjike.goddess.qualifications.dto.QualificationsCollectDTO;
import com.bjike.goddess.qualifications.service.QualificationsCollectSer;
import com.bjike.goddess.qualifications.to.QualificationsCollectFilterTO;
import com.bjike.goddess.qualifications.to.QualificationsCollectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资质办理进度汇总业务接口实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-20 10:39 ]
 * @Description: [ 资质办理进度汇总业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("qualificationsCollectApiImpl")
public class QualificationsCollectApiImpl implements QualificationsCollectAPI {

    @Autowired
    private QualificationsCollectSer qualificationsCollectSer;

    @Override
    public QualificationsCollectBO save(QualificationsCollectTO to) throws SerException {
        return qualificationsCollectSer.save(to);
    }

    @Override
    public QualificationsCollectBO update(QualificationsCollectTO to) throws SerException {
        return qualificationsCollectSer.update(to);
    }

    @Override
    public QualificationsCollectBO delete(String id) throws SerException {
        return qualificationsCollectSer.delete(id);
    }

    @Override
    public List<QualificationsCollectBO> findByFilter(QualificationsCollectFilterTO to) throws SerException {
        return qualificationsCollectSer.findByFilter(to);
    }

    @Override
    public List<QualificationsCollectBO> maps(QualificationsCollectDTO dto) throws SerException {
        return qualificationsCollectSer.maps(dto);
    }

    @Override
    public Integer getTotal() throws SerException {
        return qualificationsCollectSer.getTotal();
    }

    @Override
    public QualificationsCollectBO getById(String id) throws SerException {
        return qualificationsCollectSer.getById(id);
    }
}