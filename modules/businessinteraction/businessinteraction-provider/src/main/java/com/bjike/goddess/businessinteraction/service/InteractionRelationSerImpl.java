package com.bjike.goddess.businessinteraction.service;

import com.bjike.goddess.businessinteraction.bo.InteractionRelationBO;
import com.bjike.goddess.businessinteraction.dto.LeavingMessageDTO;
import com.bjike.goddess.businessinteraction.entity.LeavingMessage;
import com.bjike.goddess.businessinteraction.to.InteractionRelationTO;
import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 商业能力互动联系业务实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:06 ]
 * @Description: [ 商业能力互动联系业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "businessinteractionSerCache")
@Service
public class InteractionRelationSerImpl extends ServiceImpl<InteractionRelation, InteractionRelationDTO> implements InteractionRelationSer {

    @Autowired
    private LeavingMessageSer leavingMessageSer;

    @Override
    public Long countInter(InteractionRelationDTO interactionRelationDTO) throws SerException {
        if(StringUtils.isNoneBlank(interactionRelationDTO.getCompanyName())){
            interactionRelationDTO.getConditions().add(Restrict.like("companyName",interactionRelationDTO.getCompanyName()));
        }
        Long count = super.count( interactionRelationDTO );
        return count;
    }

    @Override
    public InteractionRelationBO getOneById(String id) throws SerException {
        if(StringUtils.isBlank(id)){
            throw new SerException("id部嫩嗯为空");
        }

        InteractionRelation interactionRelation = super.findById( id );
        return BeanTransform.copyProperties(interactionRelation, InteractionRelationBO.class );
    }

    @Override
    public List<InteractionRelationBO> listInteractionRelation(InteractionRelationDTO interactionRelationDTO) throws SerException {
        if(StringUtils.isNoneBlank(interactionRelationDTO.getCompanyName())){
            interactionRelationDTO.getConditions().add(Restrict.like("companyName",interactionRelationDTO.getCompanyName()));
        }
        List<InteractionRelation> list = super.findByCis(interactionRelationDTO, true);

        return BeanTransform.copyProperties(list, InteractionRelationBO.class );
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InteractionRelationBO addInteractionRelation(InteractionRelationTO interactionRelationTO) throws SerException {
        InteractionRelation interactionRelation = null;
        try {
            interactionRelation = BeanTransform.copyProperties(interactionRelationTO,InteractionRelation.class,true);
            interactionRelation.setArea( interactionRelation.getArea().trim());
            interactionRelation.setCreateTime(LocalDateTime.now());
            super.save( interactionRelation );
        } catch (SerException e) {
            throw new SerException("互动联系添加失败");
        }
        return BeanTransform.copyProperties(interactionRelation, InteractionRelationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public InteractionRelationBO editInteractionRelation(InteractionRelationTO interactionRelationTO) throws SerException {
        InteractionRelation interact = null;
        try {
            InteractionRelation interactionRelation = BeanTransform.copyProperties(interactionRelationTO,InteractionRelation.class,true);

            interact = super.findById( interactionRelationTO.getId() );
            BeanUtils.copyProperties(interactionRelation,interact,"id","createTime");
            interact.setArea( interact.getArea().trim());

            interact.setModifyTime(LocalDateTime.now());
            super.save(interact );
        } catch (SerException e) {
            throw new SerException("互动联系更新失败");
        }
        return BeanTransform.copyProperties(interact,InteractionRelationBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void deleteInteractionRelation(String id) throws SerException {
        if (StringUtils.isNotBlank(id)){
            LeavingMessageDTO leavingMessageDTO = new LeavingMessageDTO();
            leavingMessageDTO.getConditions().add(Restrict.eq("interactionRelation.id",id));
            List<LeavingMessage> list = leavingMessageSer.findByCis( leavingMessageDTO);
            if( list!= null && list.size()>0){
                leavingMessageSer.remove( list );
            }
            super.remove(id);
        }else{
            throw new SerException("互动联系id不能为空");
        }
    }

    @Override
    public List<InteractionRelationBO> searchInteractionRelation(InteractionRelationDTO interactionRelationDTO) throws SerException {
        if (StringUtils.isNotBlank(interactionRelationDTO.getCompanyName())) {
            interactionRelationDTO.getConditions().add(Restrict.like("companyName",interactionRelationDTO.getCompanyName()));
        }
        List<InteractionRelation> list = super.findByCis(interactionRelationDTO, true);

        return BeanTransform.copyProperties(list, InteractionRelationBO.class );
    }
}