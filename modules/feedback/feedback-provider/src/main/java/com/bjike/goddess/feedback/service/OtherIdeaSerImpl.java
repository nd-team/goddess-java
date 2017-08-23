package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.OtherIdeaBO;
import com.bjike.goddess.feedback.dto.OtherIdeaDTO;
import com.bjike.goddess.feedback.entity.OtherIdea;
import com.bjike.goddess.feedback.to.OtherIdeaTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 其他模块意见业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:48 ]
 * @Description: [ 其他模块意见业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class OtherIdeaSerImpl extends ServiceImpl<OtherIdea, OtherIdeaDTO> implements OtherIdeaSer {
    @Override
    public Long count(OtherIdeaDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<OtherIdeaBO> list(OtherIdeaDTO dto) throws SerException {
        List<OtherIdea> otherIdeas = super.findByCis(dto);
        List<OtherIdeaBO> otherIdeaBOS = BeanTransform.copyProperties(otherIdeas,OtherIdeaBO.class);
        return otherIdeaBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public OtherIdeaBO insert(OtherIdeaTO to) throws SerException {
        OtherIdea otherIdea = BeanTransform.copyProperties(to,OtherIdea.class,true);
        otherIdea.setCreateTime(LocalDateTime.now());
        super.save(otherIdea);
        return BeanTransform.copyProperties(otherIdea,OtherIdeaBO.class);
    }
}