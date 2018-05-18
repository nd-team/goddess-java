package com.bjike.goddess.fundrecords.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundrecords.bo.*;
import com.bjike.goddess.fundrecords.dto.CapitalFlowRecordDTO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.excel.SonPermissionObject;
import com.bjike.goddess.fundrecords.service.FundRecordSer;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;
import com.bjike.goddess.fundrecords.to.GuidePermissionTO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金流水业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fundRecordApiImpl")
public class FundRecordApiImpl implements FundRecordAPI {

    @Autowired
    private FundRecordSer fundRecordSer;

    @Override
    public FundRecordBO add(FundRecordTO to) throws SerException {
        return fundRecordSer.insertModel(to);
    }

    @Override
    public FundRecordBO edit(FundRecordTO to) throws SerException {
        return fundRecordSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        fundRecordSer.delete(id);
    }

    @Override
    public List<FundRecordBO> pageList(FundRecordDTO dto) throws SerException {
        return fundRecordSer.pageList(dto);
    }

    @Override
    public FundRecordBO balanceCall(String years, String month) throws SerException {
        return fundRecordSer.balanceCall(years,month);
    }

    @Override
    public Long count(FundRecordDTO dto) throws SerException {
        return (long)fundRecordSer.findAllBO(dto,new VoucherGenerateDTO()).size();
    }


    @Override
    public List<FundRecordBO> findList(FundRecordDTO dto) throws SerException {
        return fundRecordSer.findList(dto);
    }

    @Override
    public Long findCount(FundRecordDTO dto) throws SerException {
        return fundRecordSer.findCount(dto);
    }

    @Override
    public MonthCollectBO month(Integer year ,Integer month) throws SerException {
        return fundRecordSer.month(year ,month);
    }

    @Override
    public List<ConditionCollectBO> condition(CollectTO to) throws SerException {
        return fundRecordSer.condition(to);
    }

    @Override
    public FundRecordBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(fundRecordSer.findById(id),FundRecordBO.class);
    }

    @Override
    public List<AreaAnalyzeBO> areaAnalyze(Integer year, Integer month, String area) throws SerException {
        return fundRecordSer.areaAnalyze(year,month,area);
    }

    @Override
    public List<GroupAnalyzeBO> groupAnalyze(Integer year, Integer month, String group) throws SerException {
        return fundRecordSer.groupAnalyze(year,month,group);
    }

    @Override
    public List<ProjectAnalyzeBO> projectAnalyze(Integer year, Integer month, String project) throws SerException {
        return fundRecordSer.projectAnalyze(year,month,project);
    }

    @Override
    public void leadExcel(List<FundRecordTO> toList) throws SerException {
        fundRecordSer.leadExcel(toList);
    }

    @Override
    public byte[] exportExcelLJT(String dataSource) throws SerException {
        return fundRecordSer.exportExcelLJT(dataSource);
    }

    @Override
    public byte[] exportExcel(String startDate, String endDate) throws SerException {
        return fundRecordSer.exportExcel(startDate,endDate);
    }

    @Override
    public byte[] exportExcelModule() throws SerException {
        return fundRecordSer.exportExcelModule();
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return fundRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        return fundRecordSer.guidePermission(to);
    }

    @Override
    public List<String> sourceAccountValue() throws SerException {
        return fundRecordSer.sourceAccountValue();
    }

    @Override
    public void exportFund() throws SerException {
        fundRecordSer.exportFund();
    }

    @Override
    public List<FundRecordCollectBO> monthSumma(String startTime, String endTime) throws SerException {
        return fundRecordSer.monthSumma(startTime,endTime);
    }


    @Override
    public MonthCollectBO dateCollect(CapitalFlowRecordDTO dto) throws SerException {
        return fundRecordSer.dateCollect(dto);
    }


    @Override
    public List<ConditionCollectBO> areaSumma(String startTime, String endTime, String area) throws SerException {
        return fundRecordSer.areaSumma(startTime,endTime,area);
    }

    @Override
    public List<ConditionCollectBO> projectSumma(String startTime, String endTime, String project) throws SerException {
        return fundRecordSer.projectSumma(startTime,endTime,project);
    }

    @Override
    public List<ConditionCollectBO> projectNameSumma(String startTime, String endTime, String projectName) throws SerException {
        return fundRecordSer.projectNameSumma(startTime,endTime,projectName);
    }

    @Override
    public List<String> findAllArea() throws SerException {
        return fundRecordSer.findAllArea();
    }

    @Override
    public List<String> findAllProjectGroup() throws SerException {
        return fundRecordSer.findAllProjectGroup();
    }

    @Override
    public List<String> findAllProjectName() throws SerException {
        return fundRecordSer.findAllProjectName();
    }

    @Override
    public List<AreaAnalyzeBO> areaAnalysis(String startTime, String endTime, String area) throws SerException {
        return fundRecordSer.areaAnalysis(startTime, endTime, area);
    }

    @Override
    public List<GroupAnalyzeBO> projectAnalysis(String startTime, String endTime, String project) throws SerException {
        return fundRecordSer.projectAnalysis(startTime, endTime, project);
    }

    @Override
    public List<ProjectAnalyzeBO> projectNameAnalysis(String startTime, String endTime, String projectName) throws SerException {
        return fundRecordSer.projectNameAnalysis(startTime,endTime,projectName);
    }

    @Override
    public List<FundRecordBO> allFundRecord(FundRecordDTO dto) throws SerException {
        return fundRecordSer.allFundRecord(dto);
    }

    @Override
    public List<String> findAllDataSource() throws SerException {
        return fundRecordSer.findAllDataSource();
    }

    @Override
    public byte[] monthSummaExportE1xcel(String startTime, String endTime) throws SerException {
        return fundRecordSer.monthSummaExportE1xcel(startTime,endTime);
    }

    @Override
    public byte[] areaSummaExportExcel(String startTime, String endTime, String area) throws SerException {
        return fundRecordSer.areaSummaExportExcel(startTime,endTime,area);
    }

    @Override
    public byte[] projectSummaExportExcel(String startTime, String endTime, String project) throws SerException {
        return fundRecordSer.projectSummaExportExcel(startTime,endTime,project);
    }

    @Override
    public byte[] projectNameSummaExportExcel(String startTime, String endTime, String projectName) throws SerException {
        return fundRecordSer.projectSummaExportExcel(startTime,endTime,projectName);
    }
}