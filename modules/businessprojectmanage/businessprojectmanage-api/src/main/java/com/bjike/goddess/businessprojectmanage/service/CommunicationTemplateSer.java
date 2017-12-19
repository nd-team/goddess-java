package com.bjike.goddess.businessprojectmanage.service;

import com.bjike.goddess.businessprojectmanage.bo.CommunicationTemplateBO;
import com.bjike.goddess.businessprojectmanage.dto.CommunicationTemplateDTO;
import com.bjike.goddess.businessprojectmanage.entity.CommunicationTemplate;
import com.bjike.goddess.businessprojectmanage.to.CommunicationTemplateTO;
import com.bjike.goddess.businessprojectmanage.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 各类沟通交流模板业务接口
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2017-12-09 02:31 ]
 * @Description: [ 各类沟通交流模板业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface CommunicationTemplateSer extends Ser<CommunicationTemplate, CommunicationTemplateDTO> {

    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    /**
     * 各类沟通交流模板总条数
     */
    default Long countCommuni(CommunicationTemplateDTO CommunicationTemplateDTO) throws SerException {
        return null;
    }

    /**
     * 根据id获取各类沟通交流模板
     *
     * @return class CommunicationTemplateBO
     */
    default CommunicationTemplateBO getOneById(String id) throws SerException {
        return null;
    }

    /**
     * 各类沟通交流模板列表
     *
     * @return class CommunicationTemplateBO
     */
    default List<CommunicationTemplateBO> listCommuni(CommunicationTemplateDTO CommunicationTemplateDTO) throws SerException {
        return null;
    }

    /**
     * 添加
     *
     * @param CommunicationTemplateTO 各类沟通交流模板
     * @return class NodeHeadersCustomBO
     */
    default CommunicationTemplateBO addCommuni(CommunicationTemplateTO CommunicationTemplateTO) throws SerException {
        return null;
    }

    /**
     * 编辑
     *
     * @param CommunicationTemplateTO 各类沟通交流模板
     * @return class NodeHeadersCustomBO
     */
    default CommunicationTemplateBO editCommuni(CommunicationTemplateTO CommunicationTemplateTO) throws SerException {
        return null;
    }

    /**
     * 删除
     *
     * @param id id
     */
    default void deleteNode(String id) throws SerException {
        return;
    }
}