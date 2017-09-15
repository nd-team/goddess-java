package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contacts.api.InternalContactsAPI;
import com.bjike.goddess.managepromotion.bo.EmailBO;
import com.bjike.goddess.managepromotion.dto.EmailDTO;
import com.bjike.goddess.managepromotion.entity.Email;
import com.bjike.goddess.managepromotion.to.EmailTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.message.api.MessageAPI;
import com.bjike.goddess.message.enums.MsgType;
import com.bjike.goddess.message.enums.RangeType;
import com.bjike.goddess.message.enums.SendType;
import com.bjike.goddess.message.to.MessageTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 发送邮件业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 02:16 ]
 * @Description: [ 发送邮件业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class EmailSerImpl extends ServiceImpl<Email, EmailDTO> implements EmailSer {
    @Autowired
    private MessageAPI messageAPI;
    @Autowired
    private InternalContactsAPI internalContactsAPI;
    @Autowired
    private UserAPI userAPI;

    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    @Override
    public Long count(EmailDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public EmailBO getOne(String id) throws SerException {
        Email email = super.findById(id);
        return BeanTransform.copyProperties(email, EmailBO.class);
    }

    @Override
    public List<EmailBO> list(EmailDTO dto) throws SerException {
        List<Email> emails = super.findByCis(dto);
        List<EmailBO> emailBOS = BeanTransform.copyProperties(emails, EmailBO.class);
        return emailBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EmailBO save(EmailTO to) throws SerException {
        UserBO userBO = userAPI.currentUser();
        Email email = BeanTransform.copyProperties(to, Email.class, true,"sendObject");
        email.setSendObject(StringUtils.join(to.getSendObject(),","));
        email.setCreateTime(LocalDateTime.now());
        email.setFounder(userBO.getUsername());
        MessageTO messageTO = new MessageTO();
        messageTO.setTitle(to.getSendType());
        messageTO.setContent(to.getSendContent());
        messageTO.setMsgType(MsgType.SYS);
        messageTO.setSendType(SendType.EMAIL);
        messageTO.setRangeType(RangeType.SPECIFIED);
        if(to.getPublicEmail()!=null){
            messageTO.setReceivers(to.getPublicEmail());
            messageAPI.send(messageTO);
        }
        if(to.getSendObject()!=null){
            List<String> sendObject = internalContactsAPI.getEmails(to.getSendObject());
            String[] strings=new String[sendObject.size()];
            strings=sendObject.toArray(strings);
            messageTO.setReceivers(strings);
            messageAPI.send(messageTO);
        }

        super.save(email);
        return BeanTransform.copyProperties(email, EmailBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public EmailBO edit(EmailTO to) throws SerException {
        Email email = super.findById(to.getId());
        LocalDateTime createTime = email.getCreateTime();
        email = BeanTransform.copyProperties(to, Email.class, true);
        email.setCreateTime(createTime);
        email.setModifyTime(LocalDateTime.now());
        EmailBO bo = BeanTransform.copyProperties(email, EmailBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public void sendEmail() throws SerException {

    }


}