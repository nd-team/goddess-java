package com.bjike.goddess.feedback.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.feedback.bo.ResponsibleIdeaBO;
import com.bjike.goddess.feedback.dto.ResponsibleIdeaDTO;
import com.bjike.goddess.feedback.entity.ReceivedFeedback;
import com.bjike.goddess.feedback.entity.ResponsibleIdea;
import com.bjike.goddess.feedback.enums.AdoptStatus;
import com.bjike.goddess.feedback.to.ResponsibleIdeaTO;
import com.bjike.goddess.organize.api.PositionDetailUserAPI;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 非责任相关人意见业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-02 06:47 ]
 * @Description: [ 非责任相关人意见业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "feedbackSerCache")
@Service
public class ResponsibleIdeaSerImpl extends ServiceImpl<ResponsibleIdea, ResponsibleIdeaDTO> implements ResponsibleIdeaSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private PositionDetailUserAPI positionDetailUserAPI;

    @Override
    public Long count(ResponsibleIdeaDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public List<ResponsibleIdeaBO> list(ResponsibleIdeaDTO dto) throws SerException {
        List<ResponsibleIdea> responsibleIdeas = super.findByCis(dto);
        ResponsibleIdea responsibleIdea = new ResponsibleIdea();
        String name = responsibleIdea.getResponsibleIdea();
        List<PositionDetailBO> positionDetailBOS = positionDetailUserAPI.getPositionDetail(name);

        for (PositionDetailBO positionDetailBO : positionDetailBOS) {
            responsibleIdea.setArea(positionDetailBO.getArea());
            responsibleIdea.setProjectGroup(positionDetailBO.getDepartmentName());
        }
        List<ResponsibleIdeaBO> responsibleIdeaBOS = BeanTransform.copyProperties(responsibleIdeas, ResponsibleIdeaBO.class);
        return responsibleIdeaBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ResponsibleIdeaBO insert(ResponsibleIdeaTO to) throws SerException {
        ResponsibleIdea responsibleIdea = BeanTransform.copyProperties(to, ReceivedFeedback.class);
        responsibleIdea.setCreateTime(LocalDateTime.now());
        UserBO userBO = userAPI.currentUser();
        responsibleIdea.setResponsibleIdea(userBO.getUsername());
        responsibleIdea.setIdeaDate(LocalDateTime.now());
        super.save(responsibleIdea);
        return BeanTransform.copyProperties(responsibleIdea, ResponsibleIdeaBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public ResponsibleIdeaBO adopt(ResponsibleIdeaTO to) throws SerException {
        ResponsibleIdea responsibleIdea = super.findById(to.getId());
        responsibleIdea.setAdopt(AdoptStatus.ADOPT);
        super.update(responsibleIdea);
        return BeanTransform.copyProperties(responsibleIdea, ResponsibleIdeaBO.class);
    }
}