package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.AccountVoucherBO;
import com.bjike.goddess.lendreimbursement.bo.CollectDataBO;
import com.bjike.goddess.lendreimbursement.bo.ReimburseRecordBO;
import com.bjike.goddess.lendreimbursement.dto.ReimburseRecordDTO;
import com.bjike.goddess.lendreimbursement.excel.SonPermissionObject;
import com.bjike.goddess.lendreimbursement.service.ReimburseRecordSer;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
import com.bjike.goddess.lendreimbursement.to.ReimburseRecordTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 报销记录业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:42 ]
 * @Description: [ 报销记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("reimburseRecordApiImpl")
public class ReimburseRecordApiImpl implements ReimburseRecordAPI {

    @Autowired
    private ReimburseRecordSer reimburseRecordSer;

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return reimburseRecordSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(LendGuidePermissionTO guidePermissionTO) throws SerException {
        return reimburseRecordSer.guidePermission(guidePermissionTO);
    }

    @Override
    public ReimburseRecordBO getOneById(String id) throws SerException {
        return reimburseRecordSer.getOneById(id);
    }

    @Override
    public Long countReimburseRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.countReimburseRecord(reimburseRecordDTO);
    }

    @Override
    public List<ReimburseRecordBO> listReimburseRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.listReimburseRecord(reimburseRecordDTO);
    }

    @Override
    public ReimburseRecordBO addReimburseRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.addReimburseRecord(reimburseRecordTO);
    }

    @Override
    public ReimburseRecordBO editReimburseRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.editReimburseRecord(reimburseRecordTO);
    }

    @Override
    public void deleteReimburseRecord(String id) throws SerException {
        reimburseRecordSer.deleteReimburseRecord(id);
    }

    @Override
    public Long countErrorRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.countErrorRecord(reimburseRecordDTO);
    }

    @Override
    public List<ReimburseRecordBO> listErrorRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.listErrorRecord(reimburseRecordDTO);
    }

    @Override
    public ReimburseRecordBO editErrorRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.editErrorRecord(reimburseRecordTO);
    }

    @Override
    public Long countAuditRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.countAuditRecord(reimburseRecordDTO);
    }

    @Override
    public List<ReimburseRecordBO> listAuditRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.listAuditRecord(reimburseRecordDTO);
    }

    @Override
    public ReimburseRecordBO auditRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.auditRecord(reimburseRecordTO);
    }

    @Override
    public ReimburseRecordBO congelAuditRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.congelAuditRecord(reimburseRecordTO);
    }

    @Override
    public Long countAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.countAnalisysRecord(reimburseRecordDTO);
    }

    @Override
    public List<ReimburseRecordBO> listAnalisysRecord(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.listAnalisysRecord(reimburseRecordDTO);
    }

    @Override
    public ReimburseRecordBO analisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.analisysRecord(reimburseRecordTO);
    }

    @Override
    public ReimburseRecordBO congelAnalisysRecord(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.congelAnalisysRecord(reimburseRecordTO);
    }

    @Override
    public Long countHasAnalisys(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.countHasAnalisys(reimburseRecordDTO);
    }

    @Override
    public List<ReimburseRecordBO> listHasAnalisys(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.listHasAnalisys(reimburseRecordDTO);
    }

    @Override
    public Long countAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.countAccountCheck(reimburseRecordDTO);
    }

    @Override
    public List<ReimburseRecordBO> listAccountCheck(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.listAccountCheck(reimburseRecordDTO);
    }

    @Override
    public ReimburseRecordBO recieveTicketCondition(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.recieveTicketCondition(reimburseRecordTO);
    }

    @Override
    public Long countWaitPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.countWaitPay(reimburseRecordDTO);
    }

    @Override
    public List<ReimburseRecordBO> listWaitPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.listWaitPay(reimburseRecordDTO);
    }

    @Override
    public ReimburseRecordBO prePay(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.prePay(reimburseRecordTO);
    }

    @Override
    public ReimburseRecordBO waitPay(ReimburseRecordTO reimburseRecordTO) throws SerException {
        return reimburseRecordSer.waitPay(reimburseRecordTO);
    }

    @Override
    public Long countHasPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.countWaitPay(reimburseRecordDTO);
    }

    @Override
    public List<ReimburseRecordBO> listHasPay(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.listHasPay(reimburseRecordDTO);
    }

    @Override
    public List<AccountVoucherBO> listAccountVoucherByRecord(String id) throws SerException {
        return reimburseRecordSer.listAccountVoucherByRecord(id);
    }


    @Override
    public List<CollectDataBO> collectLender(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.collectLender(reimburseRecordDTO);
    }

    @Override
    public List<CollectDataBO> collectArea(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.collectArea(reimburseRecordDTO);
    }

    @Override
    public List<CollectDataBO> collectFirstSubject(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.collectFirstSubject(reimburseRecordDTO);
    }

    @Override
    public List<CollectDataBO> collectProjectName(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.collectProjectName(reimburseRecordDTO);
    }

    @Override
    public List<String> listAllUser() throws SerException {
        return reimburseRecordSer.listAllUser();
    }

    @Override
    public List<String> listFirstSubject() throws SerException {
        return reimburseRecordSer.listFirstSubject();
    }

    @Override
    public List<String> listArea() throws SerException {
        return reimburseRecordSer.listArea();
    }

    @Override
    public List<String> listProject() throws SerException {
        return reimburseRecordSer.listProject();
    }

    @Override
    public byte[] exportExcel(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.exportExcel(reimburseRecordDTO);
    }

    @Override
    public byte[] exportAlPayExcel(ReimburseRecordDTO reimburseRecordDTO) throws SerException {
        return reimburseRecordSer.exportExcel(reimburseRecordDTO);
    }
}