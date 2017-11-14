package com.bjike.goddess.employeecontract.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.employeecontract.bo.CommunicationTemplateBO;
import com.bjike.goddess.employeecontract.entity.CommunicationTemplate;
import com.bjike.goddess.employeecontract.dto.CommunicationTemplateDTO;
import com.bjike.goddess.employeecontract.excel.SonPermissionObject;
import com.bjike.goddess.employeecontract.to.CommunicationTemplateTO;
import com.bjike.goddess.employeecontract.to.GuidePermissionTO;

import java.util.List;

/**
* 各类交流沟通模板业务接口
* @Author:			[ jiangzaixuan ]
* @Date:			[  2017-11-11 04:28 ]
* @Description:	[ 各类交流沟通模板业务接口 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
public interface CommunicationTemplateSer extends Ser<CommunicationTemplate, CommunicationTemplateDTO> {
    /**
     * 下拉导航权限
     */
    default List<SonPermissionObject> sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }
    /**
     * 添加
     */
    void add(CommunicationTemplateTO communicationTemplateTO) throws SerException;

    /**
     * 修改
     */
    void modify(CommunicationTemplateTO communicationTemplateTO) throws SerException;

    /**
     * 列表
     */
    List<CommunicationTemplateBO> pageList(CommunicationTemplateDTO communicationTemplateDTO) throws SerException;

    /**
     * 删除
     */
    void delete(String id) throws SerException;

    /**
     * 列表总条数
     */
    Long count(CommunicationTemplateDTO communicationTemplateDTO) throws SerException;


    /**
     * 根据id查询单条数据
     */
    CommunicationTemplateBO findOne(String id) throws SerException;
 }