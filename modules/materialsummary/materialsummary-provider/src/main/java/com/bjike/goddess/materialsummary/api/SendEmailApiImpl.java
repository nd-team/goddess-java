package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.SendEmailBO;
import com.bjike.goddess.materialsummary.dto.SendEmailDTO;
import com.bjike.goddess.materialsummary.service.SendEmailSer;
import com.bjike.goddess.materialsummary.to.SendEmailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 物质购买发送邮件业务接口实现
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-07-28 08:17 ]
 * @Description: [ 物质购买发送邮件业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("buySendEmailApiImpl")
public class SendEmailApiImpl implements SendEmailAPI {
    @Autowired
    private SendEmailSer buySendEmailSer;

    @Override
    public Long counts(SendEmailDTO buySendEmailDTO) throws SerException {
        return buySendEmailSer.count(buySendEmailDTO);
    }

    @Override
    public SendEmailBO getOne(String id) throws SerException {
        return buySendEmailSer.getOne(id);
    }

    @Override
    public List<SendEmailBO> listCollectEmail(SendEmailDTO buySendEmailDTO) throws SerException {
        return buySendEmailSer.listCollectEmail(buySendEmailDTO);
    }

    @Override
    public SendEmailBO addCollectEmail(SendEmailTO buySendEmailTO) throws SerException {
        return buySendEmailSer.addCollectEmail(buySendEmailTO);
    }

    @Override
    public SendEmailBO editCollectEmail(SendEmailTO buySendEmailTO) throws SerException {
        return buySendEmailSer.editCollectEmail(buySendEmailTO);
    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        buySendEmailSer.deleteCollectEmail(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        buySendEmailSer.congealCollectEmail(id);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        buySendEmailSer.thawCollectEmail(id);
    }
}