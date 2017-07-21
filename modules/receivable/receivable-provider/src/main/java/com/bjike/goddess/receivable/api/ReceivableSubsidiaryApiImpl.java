package com.bjike.goddess.receivable.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.receivable.bo.*;
import com.bjike.goddess.receivable.dto.ReceivableSubsidiaryDTO;
import com.bjike.goddess.receivable.entity.Contractor;
import com.bjike.goddess.receivable.entity.ReceivableSubsidiary;
import com.bjike.goddess.receivable.service.ReceivableSubsidiarySer;
import com.bjike.goddess.receivable.to.CollectCompareTO;
import com.bjike.goddess.receivable.to.GuidePermissionTO;
import com.bjike.goddess.receivable.to.ProgressTO;
import com.bjike.goddess.receivable.to.ReceivableSubsidiaryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * 回款明细业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-28 04:09 ]
 * @Description: [ 回款明细业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("receivableSubsidiaryApiImpl")
public class ReceivableSubsidiaryApiImpl implements ReceivableSubsidiaryAPI {
    @Autowired
    private ReceivableSubsidiarySer receivableSubsidiarySer;
    @Override
    public Boolean sonPermission() throws SerException {
        return receivableSubsidiarySer.sonPermission();
    }
    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return receivableSubsidiarySer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countReceivableSubsidiary(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws SerException {
        return receivableSubsidiarySer.countReceivableSubsidiary(receivableSubsidiaryDTO);
    }

    @Override
    public ReceivableSubsidiaryBO getOne(String id) throws SerException {
        return receivableSubsidiarySer.getOne(id);
    }

    @Override
    public List<ReceivableSubsidiaryBO> findListReceivableSubsidiary(ReceivableSubsidiaryDTO receivableSubsidiaryDTO) throws SerException {
        return receivableSubsidiarySer.findListReceivableSubsidiary(receivableSubsidiaryDTO);
    }

    @Override
    public ReceivableSubsidiaryBO insertReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
//        receivableSubsidiaryTO.setFinishTime(DateUtil.dateToString(LocalDate.now()));
//        receivableSubsidiaryTO.setCheckTime(DateUtil.dateToString(LocalDate.now()));
//        receivableSubsidiaryTO.setAuditTime(DateUtil.dateToString(LocalDate.now()));
//        receivableSubsidiaryTO.setCountTime(DateUtil.dateToString(LocalDate.now()));
//        receivableSubsidiaryTO.setBillTime(DateUtil.dateToString(LocalDate.now()));
//        receivableSubsidiaryTO.setPlanTime(DateUtil.dateToString(LocalDate.now()));
//        receivableSubsidiaryTO.setAccountTime(DateUtil.dateToString(LocalDate.now()));
        return receivableSubsidiarySer.insertReceivableSubsidiary(receivableSubsidiaryTO);
    }

    @Override
    public ReceivableSubsidiaryBO editReceivableSubsidiary(ReceivableSubsidiaryTO receivableSubsidiaryTO) throws SerException {
        return receivableSubsidiarySer.editReceivableSubsidiary(receivableSubsidiaryTO);
    }

    @Override
    public void removeReceivableSubsidiary(String id) throws SerException {
        receivableSubsidiarySer.removeReceivableSubsidiary(id);
    }


    @Override
    public Map<String,String> auditTime(String auditTime) throws SerException {
        return receivableSubsidiarySer.auditTime(auditTime);
    }

    @Override
    public Map<String,String> countTime(String countTime) throws SerException {
        return receivableSubsidiarySer.countTime(countTime);
    }

    @Override
    public Map<String,String> billTime(String billTime) throws SerException {
        return receivableSubsidiarySer.billTime(billTime);
    }

    @Override
    public Map<String,String> planTime(String planTime) throws SerException {
        return receivableSubsidiarySer.planTime(planTime);
    }
    @Override
    public ReceivableSubsidiaryBO editTime(ReceivableSubsidiaryTO receivableSubsidiaryTO, String auditStatusStr, String countStatusStr, String billStatusStr, String planStatusStr) throws SerException {
        return receivableSubsidiarySer.editTime(receivableSubsidiaryTO, auditStatusStr, countStatusStr, billStatusStr, planStatusStr);
    }
    @Override
    public ReceivableSubsidiaryBO progress(ProgressTO to) throws SerException {
        return receivableSubsidiarySer.progress(to);
    }
    @Override
    public List<String> getArea() throws SerException {
        return receivableSubsidiarySer.getArea();
    }
    @Override
    public List<String> getInnerName() throws SerException {
        return receivableSubsidiarySer.getInnerName();
    }
    @Override
    public List<String> getContractor() throws SerException {
        return receivableSubsidiarySer.getContractor();
    }

    @Override
    public List<CollectAreaBO> collectArea(String[] areas) throws SerException {
        return receivableSubsidiarySer.collectArea(areas);
    }
    @Override
    public List<CollectProjectNameBO> collectInnerName(String[] innerNames) throws SerException {
        return receivableSubsidiarySer.collectInnerName(innerNames);
    }
    @Override
    public List<CollectContractorBO> collectContractor(String[] contractors) throws SerException {
        return receivableSubsidiarySer.collectContractor(contractors);
    }


    @Override
    public List<CollectAreaDetailBO> collectAreaDetail(String[] areas) throws SerException {
        return receivableSubsidiarySer.collectAreaDetail(areas);
    }

    @Override
    public List<CollectProjectNameDetailBO> collectInnerNameDetail(String[] innerNames) throws SerException {
        return receivableSubsidiarySer.collectInnerNameDetail(innerNames);
    }

    @Override
    public List<CollectContractorDetailBO> collectContractorDetail(String[] contractors) throws SerException {
        return receivableSubsidiarySer.collectContractorDetail(contractors);
    }
    @Override
    public ReceivableSubsidiaryBO collectId(String id) throws SerException {
        return receivableSubsidiarySer.collectId(id);
    }
    @Override
    public List<CollectCompareBO> collectCompare(CollectCompareTO to) throws SerException {
        return receivableSubsidiarySer.collectCompare(to);
    }
    @Override
    public ReceivableSubsidiaryBO importExcel(List<ReceivableSubsidiaryTO> receivableSubsidiaryTOS) throws SerException {
        return receivableSubsidiarySer.importExcel(receivableSubsidiaryTOS);
    }

    @Override
    public byte[] exportExcel(ReceivableSubsidiaryDTO dto) throws SerException{
        return receivableSubsidiarySer.exportExcel(dto);
    }
    @Override
    public byte[] templateExport() throws SerException {
        return receivableSubsidiarySer.templateExport();
    }
    @Override
    public List<ReceivableSubsidiaryBO> receivable(String startTime,String endTime) throws SerException {
        return receivableSubsidiarySer.receivable(startTime,endTime);
    }


}