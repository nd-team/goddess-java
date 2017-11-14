package com.bjike.goddess.voucher.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.voucher.bo.*;
import com.bjike.goddess.voucher.dto.SubjectCollectDTO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import com.bjike.goddess.voucher.dto.VoucherGenerateExportDTO;
import com.bjike.goddess.voucher.excel.SonPermissionObject;
import com.bjike.goddess.voucher.service.VoucherGenerateSer;
import com.bjike.goddess.voucher.to.AnalysisTO;
import com.bjike.goddess.voucher.to.GuidePermissionTO;
import com.bjike.goddess.voucher.to.VoucherGenerateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 记账凭证生成业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-17 05:33 ]
 * @Description: [ 记账凭证生成业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("voucherGenerateApiImpl")
public class VoucherGenerateApiImpl implements VoucherGenerateAPI {

    @Autowired
    private VoucherGenerateSer voucherGenerateSer;


    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return voucherGenerateSer.sonPermission();
    }
    @Override
    public List<SonPermissionObject> sonPermissionAccount() throws SerException {
        return voucherGenerateSer.sonPermissionAccount();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return voucherGenerateSer.guidePermission(guidePermissionTO);
    }

    @Override
    public VoucherGenerateBO getById(String id) throws SerException {
        return voucherGenerateSer.getById(id);
    }

    @Override
    public Long countVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countVoucherGenerate(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listVoucherGenerate(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listVoucherGenerate(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listNoPage(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listNoPage(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> addVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.addVoucherGenerate(voucherGenerateTO);
    }

    @Override
    public VoucherGenerateBO editVoucherGenerate(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.editVoucherGenerate(voucherGenerateTO);
    }

    @Override
    public void deleteVoucherGenerate(String id) throws SerException {
        voucherGenerateSer.deleteVoucherGenerate(id);
    }

    @Override
    public Long countAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countAudit(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listAudit(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listAudit(voucherGenerateDTO);
    }

    @Override
    public VoucherGenerateBO split(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.split(voucherGenerateTO);
    }

    @Override
    public VoucherGenerateBO audit(String id) throws SerException {
        return voucherGenerateSer.audit(id);
    }

    @Override
    public Long countAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countAudited(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listAudited(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listAudited(voucherGenerateDTO);
    }

    @Override
    public Long posting(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.posting(voucherGenerateTO);
    }

    @Override
    public VoucherGenerateBO antiAudit(String id) throws SerException {
        return voucherGenerateSer.antiAudit(id);
    }

    @Override
    public List<VoucherGenerateBO> collectSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.collectSub(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> collectArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.collectArea(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> collectGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.collectGroup(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> collectPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.collectPname(voucherGenerateDTO);
    }

    @Override
    public Long countChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countChecked(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listChecked(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listChecked(voucherGenerateDTO);
    }

    @Override
    public VoucherGenerateBO antiPosting(String id) throws SerException {
        return voucherGenerateSer.antiPosting(id);
    }

    @Override
    public VoucherGenerateBO checkAccount(VoucherGenerateTO voucherGenerateTO) throws SerException {
        return voucherGenerateSer.checkAccount(voucherGenerateTO);
    }

    @Override
    public List<VoucherGenerateBO> ctTransSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctTransSub(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctTransArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctTransArea(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctTransGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctTransGroup(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctTransPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctTransPname(voucherGenerateDTO);
    }

    @Override
    public Long countCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countCkRecord(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listCkRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listCkRecord(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctCkSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctCkSub(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctCkArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctCkArea(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctCkGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctCkGroup(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctCkPname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctCkPname(voucherGenerateDTO);
    }

    @Override
    public Long countRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.countRecord(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> listRecord(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.listRecord(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctReSub(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctReSub(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctReArea(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctReArea(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctReGroup(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctReGroup(voucherGenerateDTO);
    }

    @Override
    public List<VoucherGenerateBO> ctRePname(VoucherGenerateDTO voucherGenerateDTO) throws SerException {
        return voucherGenerateSer.ctRePname(voucherGenerateDTO);
    }

    @Override
    public List<String> listFirstSubject() throws SerException {
        return voucherGenerateSer.listFirstSubject();
    }

    @Override
    public List<String> listSubByFirst(String firstSub) throws SerException {
        return voucherGenerateSer.listSubByFirst(firstSub);
    }

    @Override
    public List<String> listTubByFirst(String firstSub, String secondSub) throws SerException {
        return voucherGenerateSer.listTubByFirst(firstSub, secondSub);
    }

    @Override
    public List<String> listArea() throws SerException {
        return voucherGenerateSer.listArea();
    }

    @Override
    public List<String> listProject() throws SerException {
        return voucherGenerateSer.listProject();
    }

    @Override
    public List<String> listGroup() throws SerException {
        return voucherGenerateSer.listGroup();
    }

    @Override
    public List<String> subSubject(String firstSubject) throws SerException {
        return voucherGenerateSer.subSubject(firstSubject);
    }

    @Override
    public List<String> thirdSubject(String firstSubject, String subSubject) throws SerException {
        return voucherGenerateSer.thirdSubject(firstSubject, subSubject);
    }

    @Override
    public List<VoucherGenerateBO> findFundRecord(VoucherGenerateDTO dto) throws SerException {
        return voucherGenerateSer.findFundRecord(dto);
    }

    @Override
    public List<VoucherGenerateBO> areaAnalyze(Integer year, Integer month, String area) throws SerException {
        return voucherGenerateSer.areaAnalyze(year, month, area);
    }

    @Override
    public List<VoucherGenerateBO> groupAnalyze(Integer year, Integer month, String group) throws SerException {
        return voucherGenerateSer.groupAnalyze(year, month, group);
    }

    @Override
    public List<VoucherGenerateBO> projectAnalyze(Integer year, Integer month, String project) throws SerException {
        return voucherGenerateSer.projectAnalyze(year, month, project);
    }

    @Override
    public List<VoucherGenerateBO> listStatistic(VoucherGenerateDTO voucherGenerateDTO, String condition) throws SerException {
        return voucherGenerateSer.listStatistic(voucherGenerateDTO, condition);
    }

    @Override
    public List<VoucherGenerateBO> allSales() throws SerException {
        return voucherGenerateSer.allSales();
    }

    @Override
    public List<PartBO> findByMoney(VoucherGenerateDTO dto) throws SerException {
        return voucherGenerateSer.findByMoney(dto);
    }

    @Override
    public List<AccountInfoBO> accountCollect(VoucherGenerateDTO dto) throws SerException {
        return voucherGenerateSer.accountCollect(dto);
    }

    @Override
    public byte[] exportExcelAccount(VoucherGenerateDTO dto) throws SerException {
        return voucherGenerateSer.exportExcelAccount(dto);
    }

    @Override
    public List<String> accountArea() throws SerException {
        return voucherGenerateSer.accountArea();
    }

    @Override
    public List<String> accountProjectName() throws SerException {
        return voucherGenerateSer.accountProjectName();
    }

    @Override
    public List<String> accountProjectGroup() throws SerException {
        return voucherGenerateSer.accountProjectGroup();
    }

    @Override
    public List<String> accountSubject() throws SerException {
        return voucherGenerateSer.accountSubject();
    }

    @Override
    public PartOptionBO findMoneyByCondition(String first, String second, String third) throws SerException {
        return voucherGenerateSer.findMoneyByCondition(first, second, third);
    }

    @Override
    public byte[] exportExcel(VoucherGenerateExportDTO dto) throws SerException {
        return voucherGenerateSer.exportExcel(dto);
    }

    @Override
    public byte[] templateExport() throws SerException {
        return voucherGenerateSer.templateExport();
    }

    @Override
    public VoucherGenerateBO importExcel(List<VoucherGenerateTO> voucherGenerateTO) throws SerException {
        return voucherGenerateSer.importExcel(voucherGenerateTO);
    }

    @Override
    public VoucherGenerateBO getByIdCJh(String id) throws SerException {
        return voucherGenerateSer.getByIdCJh(id);
    }

    @Override
    public List<VoucherGenerateBO> antiCheckAccount(String[] ids) throws SerException {
        return voucherGenerateSer.antiCheckAccount(ids);
    }

    @Override
    public List<VoucherGenerateBO> findCkRecordByTime(String month, Integer quart, String year) throws SerException {
        return voucherGenerateSer.findCkRecordByTime(month, quart, year);
    }

    @Override
    public List<AnalysisBO> analysis(AnalysisTO to) throws SerException {
        return voucherGenerateSer.analysis(to);
    }

    @Override
    public OptionBO ctReSubHistogram() throws SerException {
        return voucherGenerateSer.ctReSubHistogram();
    }

    @Override
    public SubjectCollectBO getSum(SubjectCollectDTO dto, String time, Boolean tar) throws SerException {
        return voucherGenerateSer.getSum(dto, time, tar);
    }

    @Override
    public Double getCurrent(SubjectCollectDTO subjectCollectDTO, String s, String s1, Boolean tar) throws SerException {
        return voucherGenerateSer.getCurrent(subjectCollectDTO, s, s1, tar);
    }

    @Override
    public List<String> findProjectName() throws SerException {
        return voucherGenerateSer.findProjectName();
    }

    @Override
    public SubjectCollectBO findCurrentAndYear(String firstSubject, String startTime, String endTime) throws SerException {
        return voucherGenerateSer.findCurrentAndYear(firstSubject, startTime, endTime);
    }

    @Override
    public List<VoucherGenerateBO> findByCourseName() throws SerException {
        return voucherGenerateSer.findByCourseName();
    }
}