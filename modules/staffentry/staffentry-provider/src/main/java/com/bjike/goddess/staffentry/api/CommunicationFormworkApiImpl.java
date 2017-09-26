package com.bjike.goddess.staffentry.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.staffentry.bo.CommunicationFormworkBO;
import com.bjike.goddess.staffentry.dto.CommunicationFormworkDTO;
import com.bjike.goddess.staffentry.service.CommunicationFormworkSer;
import com.bjike.goddess.staffentry.to.CommunicationFormworkTO;
import com.bjike.goddess.staffentry.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 各类交流沟通模块业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-25 09:48 ]
 * @Description: [ 各类交流沟通模块业务接口实现 ]
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
    public Long countComm(CommunicationFormworkDTO dto) throws SerException {
        return communicationFormworkSer.countComm(dto);
    }

    @Override
    public CommunicationFormworkBO getOne(String id) throws SerException {
        return communicationFormworkSer.getOne(id);
    }

    @Override
    public List<CommunicationFormworkBO> list(CommunicationFormworkDTO dto) throws SerException {
        return communicationFormworkSer.list(dto);
    }

    @Override
    public CommunicationFormworkBO save(CommunicationFormworkTO to) throws SerException {
        return communicationFormworkSer.save(to);
    }

    @Override
    public void remove(String id) throws SerException {
        communicationFormworkSer.remove(id);
    }

    @Override
    public void update(CommunicationFormworkTO to) throws SerException {
        communicationFormworkSer.update(to);
    }

    @Override
    public void delete(String id) throws SerException {
        communicationFormworkSer.delete(id);
    }
}