package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.LifeOGBO;
import com.bjike.goddess.recruit.dao.LifeOGRep;
import com.bjike.goddess.recruit.dto.LifeOGDTO;
import com.bjike.goddess.recruit.entity.LifeOG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工作对赌业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:11 ]
 * @Description: [ 工作对赌业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class LifeOGSerImpl extends ServiceImpl<LifeOG, LifeOGDTO> implements LifeOGSer {

    @Autowired
    private LifeOGRep lifeOGRep;

    @Transactional
    @Override
    public List<LifeOGBO> getlist(String name) throws SerException {
        return BeanTransform.wanycopyProperties(lifeOGRep.findByScoringOB(name), LifeOGBO.class);
    }

    @Override
    public void add(LifeOG lifeOG) throws SerException {
        super.save(lifeOG);
    }
}