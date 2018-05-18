package com.bjike.goddess.businessprojectmanage.api;

import com.bjike.goddess.businessprojectmanage.bo.CommunicationTemplateBO;
import com.bjike.goddess.businessprojectmanage.dto.CommunicationTemplateDTO;
import com.bjike.goddess.businessprojectmanage.service.CommunicationTemplateSer;
import com.bjike.goddess.businessprojectmanage.to.CommunicationTemplateTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各类沟通交流模板业务接口实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-09 02:31 ]
 * @Description: [ 各类沟通交流模板业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("communicationTemplateApiImpl")
public class CommunicationTemplateApiImpl implements CommunicationTemplateAPI {


    @Autowired
    private CommunicationTemplateSer communicationTempleSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return communicationTempleSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return communicationTempleSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countCommuni(CommunicationTemplateDTO communicationTempleDTO) throws SerException {
        return communicationTempleSer.countCommuni(communicationTempleDTO);
    }

    @Override
    public CommunicationTemplateBO getOneById(String id) throws SerException {
        return communicationTempleSer.getOneById(id);
    }

    @Override
    public List<CommunicationTemplateBO> listCommuni(CommunicationTemplateDTO communicationTempleDTO) throws SerException {
        return communicationTempleSer.listCommuni(communicationTempleDTO);
    }

    @Override
    public CommunicationTemplateBO addCommuni(CommunicationTemplateTO communicationTempleTO) throws SerException {
        return communicationTempleSer.addCommuni(communicationTempleTO);
    }

    @Override
    public CommunicationTemplateBO editCommuni(CommunicationTemplateTO communicationTempleTO) throws SerException {
        try {
            return communicationTempleSer.editCommuni(communicationTempleTO);
        } catch (SerException e) {
            throw new SerException(e.getMessage());
        }

    }

    @Override
    public void deleteNode(String id) throws SerException {
        communicationTempleSer.deleteNode(id);
    }
}