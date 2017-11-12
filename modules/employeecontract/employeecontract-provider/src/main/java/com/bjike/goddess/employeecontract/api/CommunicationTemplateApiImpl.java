package com.bjike.goddess.employeecontract.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.employeecontract.bo.CommunicationTemplateBO;
import com.bjike.goddess.employeecontract.dto.CommunicationTemplateDTO;
import com.bjike.goddess.employeecontract.excel.SonPermissionObject;
import com.bjike.goddess.employeecontract.service.CommunicationTemplateSer;
import com.bjike.goddess.employeecontract.to.CommunicationTemplateTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 各类交流沟通模板业务接口实现
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-11 04:28 ]
* @Description:	[ 各类交流沟通模板业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("communicationTemplateApiImpl")
public class CommunicationTemplateApiImpl implements CommunicationTemplateAPI  {
    @Autowired
    private CommunicationTemplateSer communicationTemplateSer;


    @Override
    public void add(CommunicationTemplateTO communicationTemplateTO) throws SerException {
        communicationTemplateSer.add(communicationTemplateTO);
    }

    @Override
    public void modify(CommunicationTemplateTO communicationTemplateTO) throws SerException {
        communicationTemplateSer.modify(communicationTemplateTO);
    }

    @Override
    public List<CommunicationTemplateBO> pageList(CommunicationTemplateDTO communicationTemplateDTO) throws SerException {
        return communicationTemplateSer.pageList(communicationTemplateDTO);
    }

    @Override
    public void delete(String id) throws SerException {
        communicationTemplateSer.delete(id);
    }

    @Override
    public Long count(CommunicationTemplateDTO communicationTemplateDTO) throws SerException {
        return communicationTemplateSer.count(communicationTemplateDTO);
    }

    @Override
    public CommunicationTemplateBO findOne(String id) throws SerException {
        return communicationTemplateSer.findOne(id);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return communicationTemplateSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return communicationTemplateSer.guidePermission(guidePermissionTO);
    }
}