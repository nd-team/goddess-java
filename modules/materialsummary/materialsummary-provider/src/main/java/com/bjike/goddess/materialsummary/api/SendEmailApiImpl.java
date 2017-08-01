package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.PersonalBuySummBO;
import com.bjike.goddess.materialsummary.bo.SendEmailBO;
import com.bjike.goddess.materialsummary.bo.TypeBuySummBO;
import com.bjike.goddess.materialsummary.dto.SendEmailDTO;
import com.bjike.goddess.materialsummary.service.SendEmailSer;
import com.bjike.goddess.materialsummary.to.SendEmailTO;
import com.bjike.goddess.materialsummary.type.ModuleType;
import com.bjike.goddess.materialsummary.type.SummaryType;
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
@Service("sendEmailApiImpl")
public class SendEmailApiImpl implements SendEmailAPI {
    @Autowired
    private SendEmailSer SendEmailSer;

    @Override
    public Long counts(SendEmailDTO SendEmailDTO) throws SerException {
        return SendEmailSer.count(SendEmailDTO);
    }

    @Override
    public SendEmailBO getOne(String id) throws SerException {
        return SendEmailSer.getOne(id);
    }

    @Override
    public List<SendEmailBO> listCollectEmail(SendEmailDTO SendEmailDTO) throws SerException {
        return SendEmailSer.listCollectEmail(SendEmailDTO);
    }

    @Override
    public SendEmailBO addCollectEmail(SendEmailTO SendEmailTO) throws SerException {
        return SendEmailSer.addCollectEmail(SendEmailTO);
    }

    @Override
    public SendEmailBO editCollectEmail(SendEmailTO SendEmailTO) throws SerException {
        return SendEmailSer.editCollectEmail(SendEmailTO);
    }

    @Override
    public void deleteCollectEmail(String id) throws SerException {
        SendEmailSer.deleteCollectEmail(id);
    }

    @Override
    public void congealCollectEmail(String id) throws SerException {
        SendEmailSer.congealCollectEmail(id);
    }

    @Override
    public void thawCollectEmail(String id) throws SerException {
        SendEmailSer.thawCollectEmail(id);
    }

    @Override
    public List<SummaryType> summaryType(ModuleType moduleType) throws SerException {
        return SendEmailSer.summaryType(moduleType);
    }

    @Override
    public List<TypeBuySummBO> typeBuySummDay(String summTime) throws SerException {
        return SendEmailSer.typeBuySummDay(summTime);
    }

    @Override
    public List<TypeBuySummBO> typeBuySummWeek(Integer year, Integer month, Integer week) throws SerException {
        return SendEmailSer.typeBuySummWeek(year,month,week);
    }

    @Override
    public List<TypeBuySummBO> typeBuySummMonth(Integer year, Integer month) throws SerException {
        return SendEmailSer.typeBuySummMonth(year,month);
    }

    @Override
    public List<TypeBuySummBO> typeBuySummYear(Integer year) throws SerException {
        return SendEmailSer.typeBuySummYear(year);
    }

    @Override
    public List<TypeBuySummBO> areaBuySummDay(String summTime) throws SerException {
        return SendEmailSer.areaBuySummDay(summTime);
    }

    @Override
    public List<TypeBuySummBO> areaBuySummWeek(Integer year, Integer month, Integer week) throws SerException {
        return SendEmailSer.areaBuySummWeek(year,month,week);
    }

    @Override
    public List<TypeBuySummBO> areaBuySummMonth(Integer year, Integer month) throws SerException {
        return SendEmailSer.areaBuySummMonth(year,month);
    }

    @Override
    public List<TypeBuySummBO> areaBuySummYear(Integer year) throws SerException {
        return SendEmailSer.areaBuySummYear(year);
    }

    @Override
    public List<PersonalBuySummBO> personBuySummDay(String summTime) throws SerException {
        return SendEmailSer.personBuySummDay(summTime);
    }

    @Override
    public List<PersonalBuySummBO> personBuySummWeek(Integer year, Integer month, Integer week) throws SerException {
        return SendEmailSer.personBuySummWeek(year,month,week);
    }

    @Override
    public List<PersonalBuySummBO> personBuySummMonth(Integer year, Integer month) throws SerException {
        return SendEmailSer.personBuySummMonth(year,month);
    }

    @Override
    public List<PersonalBuySummBO> personBuySummYear(Integer year) throws SerException {
        return SendEmailSer.personBuySummYear(year);
    }
}