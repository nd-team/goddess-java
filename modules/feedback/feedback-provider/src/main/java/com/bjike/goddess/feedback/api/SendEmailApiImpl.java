package com.bjike.goddess.feedback.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.feedback.bo.SendEmailBO;
import com.bjike.goddess.feedback.dto.SendEmailDTO;
import com.bjike.goddess.feedback.entity.SendEmail;
import com.bjike.goddess.feedback.service.SendEmailSer;
import com.bjike.goddess.feedback.to.SendEmailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 发送邮件业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-08-05 10:27 ]
 * @Description: [ 发送邮件业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("sendEmailApiImpl")
public class SendEmailApiImpl implements SendEmailAPI {
    @Autowired
    private SendEmailSer sendEmailSer;
    @Override
    public Long counts(SendEmailDTO sendEmailDTO) throws SerException {
        return sendEmailSer.counts(sendEmailDTO);
    }


    @Override
    public SendEmailBO getOne(String id) throws SerException {
        return sendEmailSer.getOne(id);
    }

    @Override
    public List<SendEmailBO> list(SendEmailDTO sendEmailDTO) throws SerException {
        return sendEmailSer.list(sendEmailDTO);
    }

    @Override
    public SendEmailBO add(SendEmailTO sendEmailTO) throws SerException {
        return sendEmailSer.add(sendEmailTO);
    }

    @Override
    public SendEmailBO edit(SendEmailTO sendEmailTO) throws SerException {
        return sendEmailSer.edit(sendEmailTO);
    }

    @Override
    public void delete(String id) throws SerException {
         sendEmailSer.delete(id);
    }

    @Override
    public void congeal(String id) throws SerException {
        sendEmailSer.congeal(id);
    }
    public void thaw(String id) throws SerException {
        sendEmailSer.thaw(id);
    }
    @Override
    public void checkSendEmail() throws SerException {
        sendEmailSer.checkSendEmail();
    }
}