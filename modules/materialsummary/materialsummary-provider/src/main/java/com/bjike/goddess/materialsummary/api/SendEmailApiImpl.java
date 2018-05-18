package com.bjike.goddess.materialsummary.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.materialsummary.bo.*;
import com.bjike.goddess.materialsummary.dto.SendEmailDTO;
import com.bjike.goddess.materialsummary.excel.SonPermissionObject;
import com.bjike.goddess.materialsummary.service.SendEmailSer;
import com.bjike.goddess.materialsummary.to.GuidePermissionTO;
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
    public List<SonPermissionObject> sonPermission() throws SerException {
        return SendEmailSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return SendEmailSer.guidePermission(guidePermissionTO);
    }

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

    @Override
    public List<ResouceStockSummBO> sourStockSummDay(String summTime) throws SerException {
        return SendEmailSer.sourStockSummDay(summTime);
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummWeek(Integer year, Integer month, Integer week) throws SerException {
        return SendEmailSer.sourStockSummWeek(year,month,week);
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummMonth(Integer year, Integer month) throws SerException {
        return SendEmailSer.sourStockSummMonth(year,month);
    }

    @Override
    public List<ResouceStockSummBO> sourStockSummYear(Integer year) throws SerException {
        return SendEmailSer.sourStockSummYear(year);
    }

    @Override
    public List<AreaStockSummBO> areaStockSummDay(String summTime) throws SerException {
        return SendEmailSer.areaStockSummDay(summTime);
    }

    @Override
    public List<AreaStockSummBO> areaStockSummWeek(Integer year, Integer month, Integer week) throws SerException {
        return SendEmailSer.areaStockSummWeek(year,month,week);
    }

    @Override
    public List<AreaStockSummBO> areaStockSummMonth(Integer year, Integer month) throws SerException {
        return SendEmailSer.areaStockSummMonth(year,month);
    }

    @Override
    public List<AreaStockSummBO> areaStockSummYear(Integer year) throws SerException {
        return SendEmailSer.areaStockSummYear(year);
    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummDay(String summTime) throws SerException {
        return SendEmailSer.statusDeviceSummDay(summTime);
    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummWeek(Integer year, Integer month, Integer week) throws SerException {
        return SendEmailSer.statusDeviceSummWeek(year,month,week);
    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummMonth(Integer year, Integer month) throws SerException {
        return SendEmailSer.statusDeviceSummMonth(year,month);
    }

    @Override
    public List<StatusDeviceSummBO> statusDeviceSummYear(Integer year) throws SerException {
        return SendEmailSer.statusDeviceSummYear(year);
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummDay(String summTime) throws SerException {
        return SendEmailSer.warranDeviceSummDay(summTime);
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummWeek(Integer year, Integer month, Integer week) throws SerException {
        return SendEmailSer.warranDeviceSummWeek(year,month,week);
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummMonth(Integer year, Integer month) throws SerException {
        return SendEmailSer.warranDeviceSummMonth(year,month);
    }

    @Override
    public List<WarrantyDeviceSummBO> warranDeviceSummYear(Integer year) throws SerException {
        return SendEmailSer.warranDeviceSummYear(year);
    }

    @Override
    public void checkSendEmail() throws SerException {
        SendEmailSer.checkSendEmail();
    }
}