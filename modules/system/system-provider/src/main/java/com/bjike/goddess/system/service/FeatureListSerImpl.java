package com.bjike.goddess.system.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.system.bo.FeatureListBO;
import com.bjike.goddess.system.dto.FeatureListDTO;
import com.bjike.goddess.system.entity.FeatureList;
import com.bjike.goddess.system.to.FeatureListTO;
import com.bjike.goddess.system.to.QuestionTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能列表业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-11 04:44 ]
 * @Description: [ 功能列表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "systemSerCache")
@Service
public class FeatureListSerImpl extends ServiceImpl<FeatureList, FeatureListDTO> implements FeatureListSer {
    @Override
    public Long count(FeatureListDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public FeatureListBO getOne(String id) throws SerException {
        FeatureList featureList = super.findById(id);
        return BeanTransform.copyProperties(featureList, FeatureListBO.class);
    }

    @Override
    public List<FeatureListBO> list(FeatureListDTO dto) throws SerException {
        List<FeatureList> featureLists = super.findByCis(dto);
        List<FeatureListBO> featureListBOS = BeanTransform.copyProperties(featureLists, FeatureListBO.class);
        return featureListBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FeatureListBO insert(FeatureListTO to) throws SerException {
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public FeatureListBO edit(FeatureListTO to) throws SerException {
        return null;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {

    }


    @Override
    public void ask(String id, QuestionTO questionTO) throws SerException {

    }

    @Override
    public String findDetail(String id) throws SerException {
        return null;
    }
}