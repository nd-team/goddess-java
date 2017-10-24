package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.task.bo.InnerTemplateBO;
import com.bjike.goddess.task.dto.InnerTemplateDTO;
import com.bjike.goddess.task.entity.InnerTemplate;
import com.bjike.goddess.task.entity.Record;
import com.bjike.goddess.task.to.InnerTemplateTO;
import com.bjike.goddess.user.api.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 内部协助模板业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-16 11:07 ]
 * @Description: [ 内部协助模板业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskSerCache")
@Service
public class InnerTemplateSerImpl extends ServiceImpl<InnerTemplate, InnerTemplateDTO> implements InnerTemplateSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private RecordSer recordSer;
    @Autowired
    private InternalContactsAPI internalContactsAPI;

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public InnerTemplateBO save(InnerTemplateTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        InnerTemplate entity = BeanTransform.copyProperties(to, InnerTemplate.class, true);
        entity.setCreater(name);
        super.save(entity);
        return BeanTransform.copyProperties(entity, InnerTemplateBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(InnerTemplateTO to) throws SerException {
        InnerTemplate entity = super.findById(to.getId());
        String creater=entity.getCreater();
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, InnerTemplate.class, true);
        entity.setCreateTime(a);
        entity.setCreater(creater);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<InnerTemplateBO> list(InnerTemplateDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<InnerTemplate> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, InnerTemplateBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        InnerTemplate entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        super.remove(id);
    }

    @Override
    public InnerTemplateBO findByID(String id) throws SerException {
        InnerTemplate entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, InnerTemplateBO.class);
    }

    @Override
    public Long count(InnerTemplateDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void send(InnerTemplateTO to) throws SerException {
        String name = userAPI.currentUser().getUsername();
        InnerTemplate entity = super.findById(to.getId());
        String[] sendObjects = to.getSendObjects();
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        Record record = new Record();
        record.setSender(name);
        record.setSendTime(LocalDateTime.now());
        List<Record> list = recordSer.findAll();
        record.setNum(list.size() + 1);
        record.setSituation(entity.getSituation());
        recordSer.save(record);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sendObjects.length; i++) {
            if (i == sendObjects.length - 1) {
                sb.append(sendObjects[i]);
            } else {
                sb.append(sendObjects[i] + ",");
            }
        }
        entity.setSendObject(sb.toString());
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        List<String> mails = internalContactsAPI.getEmails(sendObjects);
        if (null != mails && !mails.isEmpty()) {
            String[] strings = new String[mails.size()];
            strings = mails.toArray(strings);
            MessageTO messageTO = new MessageTO();
            messageTO.setTitle("内部协助模板");
            messageTO.setContent(html(entity));
            messageTO.setReceivers(strings);
            messageAPI.send(messageTO);
        }
    }

    private String html(InnerTemplate template) throws SerException {
        StringBuilder sb = new StringBuilder();
        sb.append("<h4>内部协助模板:</h4>");
        sb.append("<table border=\"1\" cellpadding=\"10\" cellspacing=\"0\"> ");
        sb.append("<tr>");
        sb.append("<td>项目组</td>");
        sb.append("<td>岗位</td>");
        sb.append("<td>协助说明情况</td>");
        sb.append("<td>模板名称</td>");
        sb.append("<td>模板内容编写</td>");
        sb.append("</tr>");
        sb.append("<tr>");
        sb.append("<td>" + template.getDepart() + "</td>");
        sb.append("<td>" + template.getPosition() + "</td>");
        sb.append("<td>" + template.getSituation() + "</td>");
        sb.append("<td>" + template.getName() + "</td>");
        sb.append("<td>" + template.getContent() + "</td>");
        sb.append("</tr>");
        sb.append("</table>");
        return sb.toString();
    }
}