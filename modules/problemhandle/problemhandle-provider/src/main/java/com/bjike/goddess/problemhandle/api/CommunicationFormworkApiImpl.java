package com.bjike.goddess.problemhandle.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.problemhandle.bo.CommunicationFormworkBO;
import com.bjike.goddess.problemhandle.dto.CommunicationFormworkDTO;
import com.bjike.goddess.problemhandle.service.CommunicationFormworkSer;
import com.bjike.goddess.problemhandle.to.CommunicationFormworkTO;
import com.bjike.goddess.problemhandle.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各类沟通模板业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-12-09 10:17 ]
 * @Description: [ 各类沟通模板业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("communicationFormworkApiImpl")
public class CommunicationFormworkApiImpl implements CommunicationFormworkAPI {
    @Autowired
    private CommunicationFormworkSer communicationFormworkSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return communicationFormworkSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return communicationFormworkSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countCommuni(CommunicationFormworkDTO communicationFormworkDTO) throws SerException {
        return communicationFormworkSer.countCommuni(communicationFormworkDTO);
    }

    @Override
    public CommunicationFormworkBO getOne(String id) throws SerException {
        return communicationFormworkSer.getOne(id);
    }

    @Override
    public List<CommunicationFormworkBO> findListCommuni(CommunicationFormworkDTO communicationFormworkDTO) throws SerException {
        return communicationFormworkSer.findListCommuni(communicationFormworkDTO);
    }

    @Override
    public CommunicationFormworkBO insertCommuni(CommunicationFormworkTO communicationFormworkTO) throws SerException {
        return communicationFormworkSer.insertCommuni(communicationFormworkTO);
    }

    @Override
    public CommunicationFormworkBO editCommuni(CommunicationFormworkTO communicationFormworkTO) throws SerException {
        return communicationFormworkSer.editCommuni(communicationFormworkTO);
    }

    @Override
    public void removeCommuni(String id) throws SerException {
        communicationFormworkSer.removeCommuni(id);
    }

    @Override
    public CommunicationFormworkBO findByClassification(String classification) throws SerException {
        return communicationFormworkSer.findByClassification(classification);
    }

    @Override
    public List<String> findAllType() throws SerException {
        return communicationFormworkSer.findAllType();
    }

    @Override
    public void notification(String classification, String emailModule) throws SerException {
        communicationFormworkSer.notification(classification, emailModule);
    }
}