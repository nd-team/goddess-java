package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.task.bo.InnerTemplateBO;
import com.bjike.goddess.task.dto.InnerTemplateDTO;
import com.bjike.goddess.task.service.InnerTemplateSer;
import com.bjike.goddess.task.to.InnerTemplateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内部协助模板业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:07 ]
 * @Description: [ 内部协助模板业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("innerTemplateApiImpl")
public class InnerTemplateApiImpl implements InnerTemplateAPI {
    @Autowired
    private InnerTemplateSer innerTemplateSer;

    @Override
    public List<InnerTemplateBO> list(InnerTemplateDTO dto) throws SerException {
        return innerTemplateSer.list(dto);
    }

    @Override
    public InnerTemplateBO save(InnerTemplateTO to) throws SerException {
        return innerTemplateSer.save(to);
    }

    @Override
    public void edit(InnerTemplateTO to) throws SerException {
        innerTemplateSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        innerTemplateSer.delete(id);
    }

    @Override
    public InnerTemplateBO findByID(String id) throws SerException {
        return innerTemplateSer.findByID(id);
    }

    @Override
    public Long count(InnerTemplateDTO dto) throws SerException {
        return innerTemplateSer.count(dto);
    }

    @Override
    public void send(InnerTemplateTO to) throws SerException {
        innerTemplateSer.send(to);
    }
}