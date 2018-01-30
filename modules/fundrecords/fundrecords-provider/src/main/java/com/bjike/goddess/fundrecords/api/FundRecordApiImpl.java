package com.bjike.goddess.fundrecords.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.fundrecords.bo.*;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.excel.SonPermissionObject;
import com.bjike.goddess.fundrecords.service.FundRecordSer;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;
import com.bjike.goddess.fundrecords.to.GuidePermissionTO;
import com.bjike.goddess.fundrecords.vo.GroupAnalyzeVO;
import com.bjike.goddess.fundrecords.vo.ProjectAnalyzeVO;
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
    public MonthCollectBO monthSumma(Integer year, Integer month) throws SerException {
        return fundRecordSer.monthSumma(year,month);
    }

    @Override
    public ConditionCollectBO areaSumma(Integer year, Integer month, String area) throws SerException {
        return fundRecordSer.areaSumma(year,month,area);
    }

    @Override
    public ConditionCollectBO projectSumma(Integer year, Integer month, String project) throws SerException {
        return fundRecordSer.projectSumma(year,month,project);
    }

    @Override
    public ConditionCollectBO projectNameSumma(Integer year, Integer month, String projectName) throws SerException {
        return fundRecordSer.projectNameSumma(year,month,projectName);
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
    public AreaAnalyzeBO areaAnalysis(Integer year, Integer month, String area) throws SerException {
        return fundRecordSer.areaAnalysis(year, month, area);
    }

    @Override
    public GroupAnalyzeBO projectAnalysis(Integer year, Integer month, String project) throws SerException {
        return fundRecordSer.projectAnalysis(year, month, project);
    }

    @Override
    public ProjectAnalyzeBO projectNameAnalysis(Integer year, Integer month, String projectName) throws SerException {
        return fundRecordSer.projectNameAnalysis(year,month,projectName);
    }

    @Override
    public List<String> findAllDataSource() throws SerException {
        return fundRecordSer.findAllDataSource();
    }
}