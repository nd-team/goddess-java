package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.CheckIndexBO;
import com.bjike.goddess.recruit.dao.CheckIndexRep;
import com.bjike.goddess.recruit.dto.CheckIndexDTO;
import com.bjike.goddess.recruit.entity.CheckIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.ToDoubleBiFunction;

/**
 * 考核指标业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:26 ]
 * @Description: [ 考核指标业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class CheckIndexSerImpl extends ServiceImpl<CheckIndex, CheckIndexDTO> implements CheckIndexSer {
    @Autowired
    private CheckIndexRep checkIndexRep;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<CheckIndexBO> list() throws SerException {
        List<CheckIndexBO> checkIndexBOS = BeanTransform.wanycopyProperties(checkIndexRep.findAll(), CheckIndexBO.class);
        return checkIndexBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void add(CheckIndexBO bo) throws SerException {
        CheckIndex checkIndex = BeanTransform.wanycopyProperties(bo,CheckIndex.class);
        super.save(checkIndex);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        checkIndexRep.deleteById(id);

    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public CheckIndexBO edit(String id) throws SerException {
        CheckIndexBO checkIndexBO = BeanTransform.wanycopyProperties(super.findById(id),CheckIndexBO.class);
        return checkIndexBO;
    }
}