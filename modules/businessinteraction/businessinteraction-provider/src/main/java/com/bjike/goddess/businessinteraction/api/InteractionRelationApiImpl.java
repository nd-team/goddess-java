package com.bjike.goddess.businessinteraction.api;

import com.bjike.goddess.businessinteraction.bo.InteractionRelationBO;
import com.bjike.goddess.businessinteraction.dto.InteractionRelationDTO;
import com.bjike.goddess.businessinteraction.entity.InteractionRelation;
import com.bjike.goddess.businessinteraction.service.InteractionRelationSer;
import com.bjike.goddess.businessinteraction.to.GuidePermissionTO;
import com.bjike.goddess.businessinteraction.to.InteractionRelationTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公司信息业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 08:58 ]
 * @Description: [ 公司信息业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("interactionRelationApiImpl")
public class InteractionRelationApiImpl implements InteractionRelationAPI {
    @Autowired
    InteractionRelationSer interactionRelationSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return interactionRelationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return interactionRelationSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countInter(InteractionRelationDTO interactionRelationDTO) throws SerException {
        return interactionRelationSer.countInter(interactionRelationDTO);
    }

    @Override
    public InteractionRelationBO getOneById(String id) throws SerException {
        return interactionRelationSer.getOneById(id);
    }

    @Override
    public List<InteractionRelationBO> listIntera(InteractionRelationDTO interactionRelationDTO) throws SerException {
        return interactionRelationSer.listIntera(interactionRelationDTO);
    }

    @Override
    public InteractionRelationBO addIntera(InteractionRelationTO interactionRelationTO) throws SerException {
        return interactionRelationSer.addIntera(interactionRelationTO);
    }

    @Override
    public InteractionRelationBO editIntera(InteractionRelationTO interactionRelationTO) throws SerException {
        return interactionRelationSer.editIntera(interactionRelationTO);
    }

    @Override
    public void deleteIntera(String id) throws SerException {
        interactionRelationSer.deleteIntera(id);
    }

    @Override
    public byte[] exportExcel() throws SerException {
        return interactionRelationSer.exportExcel();
    }

    @Override
    public byte[] templateExport() throws SerException {
        return interactionRelationSer.templateExport();
    }

    @Override
    public void importExcel(List<InteractionRelationTO> interactionRelationTOS) throws SerException {
        interactionRelationSer.importExcel(interactionRelationTOS);
    }
}