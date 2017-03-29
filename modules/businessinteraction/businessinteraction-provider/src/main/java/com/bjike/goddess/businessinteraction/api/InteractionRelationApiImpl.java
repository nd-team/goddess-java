package com.bjike.goddess.businessinteraction.api;

import com.bjike.goddess.businessinteraction.bo.InteractionRelationBO;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.service.InteractionRelationSer;
import com.bjike.goddess.businessinteraction.to.InteractionRelationTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商业能力互动联系业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-03-28 03:06 ]
 * @Description: [ 商业能力互动联系业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("interactionRelationApiImpl")
public class InteractionRelationApiImpl implements InteractionRelationAPI {

    @Autowired
    private InteractionRelationSer interactionRelationSer;

    @Override
    public List<InteractionRelationBO> listInteractionRelation(InteractionRelationDTO interactionRelationDTO) throws SerException {
        return interactionRelationSer.listInteractionRelation(interactionRelationDTO);
    }

    @Override
    public InteractionRelationBO addInteractionRelation(InteractionRelationTO interactionRelationTO) throws SerException {
        return interactionRelationSer.addInteractionRelation(interactionRelationTO);
    }

    @Override
    public InteractionRelationBO editInteractionRelation(InteractionRelationTO interactionRelationTO) throws SerException {
        return interactionRelationSer.editInteractionRelation(interactionRelationTO);
    }

    @Override
    public void deleteInteractionRelation(String id) throws SerException {
        interactionRelationSer.deleteInteractionRelation(id);
    }

    @Override
    public List<InteractionRelationBO> searchInteractionRelation(InteractionRelationDTO interactionRelationDTO) throws SerException {
        return interactionRelationSer.searchInteractionRelation(interactionRelationDTO);
    }
}