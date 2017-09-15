package com.bjike.goddess.managepromotion.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.managepromotion.bo.EmailBO;
import com.bjike.goddess.managepromotion.dto.EmailDTO;
import com.bjike.goddess.managepromotion.service.EmailSer;
import com.bjike.goddess.managepromotion.to.EmailTO;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发送邮件业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-14 02:16 ]
 * @Description: [ 发送邮件业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("emailApiImpl")
public class EmailApiImpl implements EmailAPI {
    @Autowired
    private EmailSer emailSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return emailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return emailSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long count(EmailDTO dto) throws SerException {
        return emailSer.count(dto);
    }

    @Override
    public EmailBO getOne(String id) throws SerException {
        return emailSer.getOne(id);
    }

    @Override
    public List<EmailBO> list(EmailDTO dto) throws SerException {
        return emailSer.list(dto);
    }

    @Override
    public EmailBO save(EmailTO to) throws SerException {
        return emailSer.save(to);
    }

    @Override
    public EmailBO edit(EmailTO to) throws SerException {
        return emailSer.edit(to);
    }
    @Override
    public void remove(String id) throws SerException {
        emailSer.remove(id);
    }
    @Override
    public void sendEmail() throws SerException {
        emailSer.sendEmail();
    }
}