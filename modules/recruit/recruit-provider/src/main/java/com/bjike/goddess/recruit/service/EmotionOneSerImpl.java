package com.bjike.goddess.recruit.service;
import com.alibaba.druid.sql.visitor.functions.If;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.CheckIndexBO;
import com.bjike.goddess.recruit.bo.EmotionOneBO;
import com.bjike.goddess.recruit.dao.EmotionOneRep;
import com.bjike.goddess.recruit.dto.EmotionOneDTO;
import com.bjike.goddess.recruit.entity.EmotionOne;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 情感标签二级业务实现
 *
 * @Author: [ wanyi ]
 * @Date: [ 2018-01-11 03:57 ]
 * @Description: [ 情感标签二级业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "recruitSerCache")
@Service
public class EmotionOneSerImpl extends ServiceImpl<EmotionOne, EmotionOneDTO> implements EmotionOneSer {


    @Autowired
    private EmotionOneRep emotionOneRep;

    @Transactional(rollbackFor = SerException.class)
    @Override
    public List<EmotionOneBO> list() throws SerException {
        List<EmotionOneBO> emotionOneBOS = BeanTransform.wanycopyProperties(emotionOneRep.findAll(),EmotionOneBO.class);
        return emotionOneBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void add(EmotionOneBO bo) throws SerException {
        EmotionOne emotionOne = BeanTransform.wanycopyProperties(bo,EmotionOne.class);
        super.save(emotionOne);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        emotionOneRep.deleteById(id);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EmotionOneBO edit(String id) throws SerException {
        EmotionOneBO bo = BeanTransform.wanycopyProperties(super.findById(id),EmotionOneBO.class);
        return bo;
    }
}