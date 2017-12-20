package com.bjike.goddess.supplier.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.supplier.bo.QualificationLevelSetBO;
import com.bjike.goddess.supplier.dto.QualificationLevelSetDTO;
import com.bjike.goddess.supplier.entity.QualificationLevelSet;
import com.bjike.goddess.supplier.to.QualificationLevelSetTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 资质等级设置业务实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-15 04:08 ]
 * @Description: [ 资质等级设置业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "supplierSerCache")
@Service
public class QualificationLevelSetSerImpl extends ServiceImpl<QualificationLevelSet, QualificationLevelSetDTO> implements QualificationLevelSetSer {
    @Override
    public Long countLevelSet(QualificationLevelSetDTO qualificationLevelSetDTO) throws SerException {
        Long count = super.count(qualificationLevelSetDTO);
        return count;
    }

    @Override
    public QualificationLevelSetBO getOneById(String id) throws SerException {
        QualificationLevelSet qualificationLevelSet = super.findById(id);
        return BeanTransform.copyProperties(qualificationLevelSet,QualificationLevelSet.class);
    }

    @Override
    public List<QualificationLevelSetBO> listLevelSet(QualificationLevelSetDTO qualificationLevelSetDTO) throws SerException {
        List<QualificationLevelSet> qualificationLevelSetList = super.findByCis(qualificationLevelSetDTO,true);
        return BeanTransform.copyProperties(qualificationLevelSetList,QualificationLevelSetBO.class);
    }

    @Override
    public QualificationLevelSetBO addLevelSet(QualificationLevelSetTO qualificationLevelSetTO) throws SerException {
        QualificationLevelSet qualificationLevelSet = BeanTransform.copyProperties(qualificationLevelSetTO,QualificationLevelSet.class);
        qualificationLevelSet.setCreateTime(LocalDateTime.now());
        super.save(qualificationLevelSet);
        return BeanTransform.copyProperties(qualificationLevelSet,QualificationLevelSetBO.class);
    }

    @Override
    public QualificationLevelSetBO editLevelSet(QualificationLevelSetTO qualificationLevelSetTO) throws SerException {
        QualificationLevelSet qualificationLevelSet = super.findById(qualificationLevelSetTO.getId());
        LocalDateTime dateTime = qualificationLevelSet.getCreateTime();
        qualificationLevelSet = BeanTransform.copyProperties(qualificationLevelSetTO,QualificationLevelSet.class,true);
        qualificationLevelSet.setCreateTime(dateTime);
        qualificationLevelSet.setModifyTime(LocalDateTime.now());
        super.update(qualificationLevelSet);
        return BeanTransform.copyProperties(qualificationLevelSet,QualificationLevelSetBO.class);
    }

    @Override
    public void deleteLevelSet(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public List<String> findAllLevel() throws SerException {
        List<QualificationLevelSet> qualificationLevelSetList = super.findAll();
        if(CollectionUtils.isEmpty(qualificationLevelSetList)){
            return Collections.emptyList();
        }
        return qualificationLevelSetList.stream().map(QualificationLevelSet::getQualificationLevel).distinct().collect(Collectors.toList());
    }
}