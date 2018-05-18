package com.bjike.goddess.projectprocing.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.projectprocing.bo.CommunicationTempleBO;
import com.bjike.goddess.projectprocing.dto.CommunicationTempleDTO;
import com.bjike.goddess.projectprocing.service.CommunicationTempleSer;
import com.bjike.goddess.projectprocing.to.CommunicationTempleTO;
import com.bjike.goddess.projectprocing.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各类沟通交流模板业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-25 05:41 ]
 * @Description: [ 各类沟通交流模板业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("communicationTempleApiImpl")
public class CommunicationTempleApiImpl implements CommunicationTempleAPI {
    @Autowired
    private CommunicationTempleSer communicationTempleSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return communicationTempleSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return communicationTempleSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countCommuni(CommunicationTempleDTO communicationTempleDTO) throws SerException {
        return communicationTempleSer.countCommuni(communicationTempleDTO);
    }

    @Override
    public CommunicationTempleBO getOneById(String id) throws SerException {
        return communicationTempleSer.getOneById(id);
    }

    @Override
    public List<CommunicationTempleBO> listCommuni(CommunicationTempleDTO communicationTempleDTO) throws SerException {
        return communicationTempleSer.listCommuni(communicationTempleDTO);
    }

    @Override
    public CommunicationTempleBO addCommuni(CommunicationTempleTO communicationTempleTO) throws SerException {
        return communicationTempleSer.addCommuni(communicationTempleTO);
    }

    @Override
    public CommunicationTempleBO editCommuni(CommunicationTempleTO communicationTempleTO) throws SerException {
        return communicationTempleSer.editCommuni(communicationTempleTO);
    }

    @Override
    public void deleteNode(String id) throws SerException {
        communicationTempleSer.deleteNode(id);
    }
}