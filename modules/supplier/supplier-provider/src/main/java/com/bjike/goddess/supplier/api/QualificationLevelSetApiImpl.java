package com.bjike.goddess.supplier.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.supplier.bo.QualificationLevelSetBO;
import com.bjike.goddess.supplier.dto.QualificationLevelSetDTO;
import com.bjike.goddess.supplier.service.QualificationLevelSetSer;
import com.bjike.goddess.supplier.to.QualificationLevelSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资质等级设置业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:08 ]
 * @Description: [ 资质等级设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("qualificationLevelSetApiImpl")
public class QualificationLevelSetApiImpl implements QualificationLevelSetAPI {
    @Autowired
    private QualificationLevelSetSer qualificationLevelSetSer;

    @Override
    public Long countLevelSet(QualificationLevelSetDTO qualificationLevelSetDTO) throws SerException {
        return qualificationLevelSetSer.countLevelSet(qualificationLevelSetDTO);
    }

    @Override
    public QualificationLevelSetBO getOneById(String id) throws SerException {
        return qualificationLevelSetSer.getOneById(id);
    }

    @Override
    public List<QualificationLevelSetBO> listLevelSet(QualificationLevelSetDTO qualificationLevelSetDTO) throws SerException {
        return qualificationLevelSetSer.listLevelSet(qualificationLevelSetDTO);
    }

    @Override
    public QualificationLevelSetBO addLevelSet(QualificationLevelSetTO qualificationLevelSetTO) throws SerException {
        return qualificationLevelSetSer.addLevelSet(qualificationLevelSetTO);
    }

    @Override
    public QualificationLevelSetBO editLevelSet(QualificationLevelSetTO qualificationLevelSetTO) throws SerException {
        return qualificationLevelSetSer.editLevelSet(qualificationLevelSetTO);
    }

    @Override
    public void deleteLevelSet(String id) throws SerException {
        qualificationLevelSetSer.deleteLevelSet(id);
    }

    @Override
    public List<String> findAllLevel() throws SerException {
        return qualificationLevelSetSer.findAllLevel();
    }
}