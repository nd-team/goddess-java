package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.bo.InnerTemplateBO;
import com.bjike.goddess.task.dto.InnerTemplateDTO;
import com.bjike.goddess.task.entity.InnerTemplate;
import com.bjike.goddess.task.to.InnerTemplateTO;

import java.util.List;

/**
 * 内部协助模板业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:07 ]
 * @Description: [ 内部协助模板业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface InnerTemplateSer extends Ser<InnerTemplate, InnerTemplateDTO> {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<InnerTemplateBO> list(InnerTemplateDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    InnerTemplateBO save(InnerTemplateTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(InnerTemplateTO to) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    InnerTemplateBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(InnerTemplateDTO dto) throws SerException;

    /**
     * 发送邮件
     * @param to
     * @throws SerException
     */
    void send(InnerTemplateTO to) throws SerException;
}