package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.managepromotion.bo.ExchangeTemplateBO;
import com.bjike.goddess.managepromotion.bo.SkillStandardBO;
import com.bjike.goddess.managepromotion.dto.ExchangeTemplateDTO;
import com.bjike.goddess.managepromotion.dto.SkillStandardDTO;
import com.bjike.goddess.managepromotion.entity.ExchangeTemplate;
import com.bjike.goddess.managepromotion.to.ExchangeTemplateTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillStandardTO;

import java.util.List;

/**
 * 各类交流沟通模板业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 11:49 ]
 * @Description: [ 各类交流沟通模板业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface ExchangeTemplateSer extends Ser<ExchangeTemplate, ExchangeTemplateDTO> {
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
     * 各类交流沟通模板列表总条数
     */
    default Long count(ExchangeTemplateDTO dto) throws SerException {
        return null;
    }

    /**
     * 一个各类交流沟通模板
     *
     * @return class ExchangeTemplateBO
     */
    default ExchangeTemplateBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 各类交流沟通模板
     *
     * @param dto 各类交流沟通模板dto
     * @return class ExchangeTemplateBO
     * @throws SerException
     */
    default List<ExchangeTemplateBO> list(ExchangeTemplateDTO dto) throws SerException {
        return null;
    }

    /**
     * 添加各类交流沟通模板
     *
     * @param to 各类交流沟通模板to
     * @return class ExchangeTemplateBO
     * @throws SerException
     */
    default ExchangeTemplateBO save(ExchangeTemplateTO to) throws SerException {
        return null;
    }

    /**
     * 编辑各类交流沟通模板
     *
     * @param to 各类交流沟通模板数据to
     * @return class ExchangeTemplateBO
     * @throws SerException
     */
    default ExchangeTemplateBO edit(ExchangeTemplateTO to) throws SerException {
        return null;
    }

    /**
     * 删除各类交流沟通模板
     *
     * @param id id
     * @throws SerException
     */
    default void delete(String id) throws SerException {
    }
    /**
     * 根据标题获得发送邮件模板
     *
     * @param title 标题
     * @return class ExchangeTemplateBO
     * @throws SerException
     */
    default ExchangeTemplateBO getContent(String title) throws SerException {
        return null;
    }
    /**
     * 获取所有标题
     *
     * @return class String
     * @throws SerException
     */
    default List<String> getTitle() throws SerException {
        return null;
    }
}