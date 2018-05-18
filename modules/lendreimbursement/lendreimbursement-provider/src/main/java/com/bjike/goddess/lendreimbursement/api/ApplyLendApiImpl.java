package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.financeinit.bo.SecondSubjectDataBO;
import com.bjike.goddess.financeinit.bo.SubjectDataBO;
import com.bjike.goddess.financeinit.bo.SubjectDatasBO;
import com.bjike.goddess.lendreimbursement.bo.*;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendDTO;
import com.bjike.goddess.lendreimbursement.dto.PhoneApplyLendDTO;
import com.bjike.goddess.lendreimbursement.dto.PhoneApplyLendSelectDTO;
import com.bjike.goddess.lendreimbursement.dto.reimshape.*;
import com.bjike.goddess.lendreimbursement.enums.LendPhoneShowStatus;
import com.bjike.goddess.lendreimbursement.service.ApplyLendSer;
import com.bjike.goddess.lendreimbursement.to.*;
import com.bjike.goddess.lendreimbursement.vo.lendreimshape.*;
import com.bjike.goddess.reimbursementprepare.excel.ExportExcelTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 申请借款业务接口实现
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-06 10:01 ]
 * @Description: [ 申请借款业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("applyLendApiImpl")
public class ApplyLendApiImpl implements ApplyLendAPI {

    @Autowired
    private ApplyLendSer applyLendSer;

    @Override
    public Boolean sonPermission() throws SerException {
        return applyLendSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(LendGuidePermissionTO guidePermissionTO) throws SerException {
        return applyLendSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countApplyLend(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countApplyLend(applyLendDTO);
    }

    @Override
    public ApplyLendBO getOneById(String id) throws SerException {
        return applyLendSer.getOneById(id);
    }

    @Override
    public List<ApplyLendBO> listApplyLend(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listApplyLend(applyLendDTO);
    }

    @Override
    public ApplyLendBO addApplyLend(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.addApplyLend(applyLendTO);
    }

    @Override
    public ApplyLendBO editApplyLend(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editApplyLend(applyLendTO);
    }

    @Override
    public void deleteApplyLend(String id) throws SerException {
        applyLendSer.deleteApplyLend(id);
    }

    @Override
    public ApplyLendBO getApplyLendDetail(String id) throws SerException {
        return applyLendSer.getApplyLendDetail(id);
    }

    @Override
    public List<LendAuditDetailBO> getLendAuditDetailByApplyLendId(String applyLendId) throws SerException {
        return applyLendSer.getLendAuditDetailByApplyLendId(applyLendId);
    }

    @Override
    public Long countWaitAudit(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countWaitAudit(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listWaitAudit(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listWaitAudit(applyLendDTO);
    }

    @Override
    public ApplyLendBO editWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editWaitAudit(applyLendTO);
    }

    @Override
    public ApplyLendBO editChargerWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editChargerWaitAudit(applyLendTO);
    }

    @Override
    public ApplyLendBO editManageWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editManageWaitAudit(applyLendTO);
    }

    @Override
    public ApplyLendBO editOperateWaitAudit(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editOperateWaitAudit(applyLendTO);
    }

    @Override
    public ApplyLendBO editOperateCongel(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editOperateCongel(applyLendTO);
    }

    @Override
    public ApplyLendBO editChargeSureCongel(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editChargeSureCongel(applyLendTO);
    }

    @Override
    public ApplyLendBO editChargeConcelCongel(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editChargeConcelCongel(applyLendTO);
    }

    @Override
    public Long countApplyError(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countApplyError(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listApplyError(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listApplyError(applyLendDTO);
    }

    @Override
    public ApplyLendBO editApplyError(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editApplyError(applyLendTO);
    }

    @Override
    public void deleteApplyError(String id) throws SerException {
        applyLendSer.deleteApplyError(id);
    }

    @Override
    public ApplyLendBO getApplyApplyError(String id) throws SerException {
        return applyLendSer.getApplyApplyError(id);
    }

    @Override
    public ApplyLendBO getApplyApplyErrorCopy(String id) throws SerException {
        return applyLendSer.getApplyApplyErrorCopy(id);
    }

    @Override
    public Long countHasAudit(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countHasAudit(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listHasAudit(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listHasAudit(applyLendDTO);
    }

    @Override
    public Long countWaitPay(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countWaitPay(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listWaitPay(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listWaitPay(applyLendDTO);
    }

    @Override
    public ApplyLendBO editPayMoney(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editPayMoney(applyLendTO);
    }

    @Override
    public Long countSureRecieve(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countSureRecieve(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listSureRecieveMoney(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listSureRecieveMoney(applyLendDTO);
    }

    @Override
    public ApplyLendBO editSureRecieveMoney(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editSureRecieveMoney(applyLendTO);
    }

    @Override
    public Long countBorrowRecord(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countBorrowRecord(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listBorrowRecord(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listBorrowRecord(applyLendDTO);
    }

    @Override
    public ApplyLendBO editReturnBorrowRecord(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editReturnBorrowRecord(applyLendTO);
    }

    @Override
    public ApplyLendBO editBorrowRecordSend(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editBorrowRecordSend(applyLendTO);
    }

    @Override
    public ApplyLendBO editPhoneReturn(PhoneLendReturnSendTO phoneLendReturnSendTO) throws SerException {
        return applyLendSer.editPhoneReturn(phoneLendReturnSendTO);
    }

    @Override
    public ApplyLendBO editPhoneSend(PhoneLendSendTO phoneLendSendTO) throws SerException {
        return applyLendSer.editPhoneSend(phoneLendSendTO);
    }

    @Override
    public List<AccountVoucherBO> listAccountVoucherByRecord(String id) throws SerException {
        return applyLendSer.listAccountVoucherByRecord(id);
    }

    @Override
    public Long countReturn(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countReturn(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listReturnMoneyRecord(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listReturnMoneyRecord(applyLendDTO);
    }

    @Override
    public List<AccountVoucherBO> listAccountVoucherByReturnMoney(String id) throws SerException {
        return applyLendSer.listAccountVoucherByReturnMoney(id);
    }

    @Override
    public ApplyLendBO checkReturnMoney(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.checkReturnMoney(applyLendTO);
    }

    @Override
    public ApplyLendBO phoneCheckReturn(PhoneLendReturnCheckTO phoneLendReturnCheckTO) throws SerException {
        return applyLendSer.phoneCheckReturn(phoneLendReturnCheckTO);
    }

    @Override
    public ApplyLendBO editErrorReturn(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editErrorReturn(applyLendTO);
    }

    @Override
    public Long countBusCheck(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countBusCheck(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listBusinessCheck(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listBusinessCheck(applyLendDTO);
    }

    @Override
    public ApplyLendBO checkTicket(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.checkTicket(applyLendTO);
    }

    @Override
    public Long countRecTicket(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countRecTicket(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listRecieveTicketRecord(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listRecieveTicketRecord(applyLendDTO);
    }

    @Override
    public List<CollectDataBO> collectLender(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.collectLender(applyLendDTO);
    }

    @Override
    public List<CollectDataBO> collectArea(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.collectArea(applyLendDTO);
    }

    @Override
    public List<CollectDataBO> collectProjectGroup(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.collectProjectGroup(applyLendDTO);
    }

    @Override
    public List<CollectDataBO> collectProjectName(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.collectProjectName(applyLendDTO);
    }

    @Override
    public List<String> getAllUser() throws SerException {
        return applyLendSer.getAllUser();
    }

    @Override
    public List<String> listLender() throws SerException {
        return applyLendSer.listLender();
    }

    @Override
    public List<String> listArea() throws SerException {
        return applyLendSer.listArea();
    }


    @Override
    public List<String> listProjectGroup() throws SerException {
        return applyLendSer.listProjectGroup();
    }

    @Override
    public List<String> listProjectName() throws SerException {
        return applyLendSer.listProjectName();
    }

    @Override
    public List<String> listPhoneArea() throws SerException {
        return applyLendSer.listPhoneArea();
    }

    @Override
    public List<String> listPhoneProjectGroup(PhoneApplyLendSelectDTO phoneApplyLendSelectDTO) throws SerException {
        return applyLendSer.listPhoneProjectGroup(phoneApplyLendSelectDTO);
    }

    @Override
    public List<String> listPhoneProjectName(PhoneApplyLendSelectDTO phoneApplyLendSelectDTO) throws SerException {
        return applyLendSer.listPhoneProjectName(phoneApplyLendSelectDTO);
    }

    @Override
    public byte[] exportExcel(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.exportExcel(applyLendDTO);
    }


    @Override
    public List<String> listAccountCom() throws SerException {
        return applyLendSer.listAccountCom();
    }


    @Override
    public byte[] waitingPayExcel(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.waitingPayExcel(applyLendDTO);
    }

    @Override
    public byte[] borrowExcel(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.borrowExcel(applyLendDTO);
    }

    @Override
    public byte[] returnExcel(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.returnExcel(applyLendDTO);
    }

    @Override
    public byte[] receiveExcel(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.receiveExcel(applyLendDTO);
    }

    @Override
    public List<ExportExcelTO> waitPayExport(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.waitPayExport(applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listWaitPayCJH(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listWaitPayCJH(applyLendDTO);
    }

    @Override
    public ApplyLendBO editPayMoneyCJH(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.editPayMoneyCJH(applyLendTO);
    }

    @Override
    public Long countWaitPayCJH(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countWaitPayCJH(applyLendDTO);
    }

    @Override
    public Boolean phoneShowRight(LendPhoneShowStatus lendPhoneShowStatus, String lendId) throws SerException {
        return applyLendSer.phoneShowRight(lendPhoneShowStatus, lendId);
    }

    @Override
    public List<ApplyLendBO> listAll(PhoneApplyLendDTO dto) throws SerException {
        return applyLendSer.listAll(dto);
    }


    @Override
    public ReimShapeAllVO collectSelfShape(ReimburseShapeDTO reimburseShapeDTO) throws SerException {
        return applyLendSer.collectSelfShape(reimburseShapeDTO);
    }

    @Override
    public ReimShapeMixVO collectSelfTrend(ReimburseTrendShapeDTO reimburseTrendShapeDTO) throws SerException {
        return applyLendSer.collectSelfTrend(reimburseTrendShapeDTO);
    }

    @Override
    public ReimCompanyMixShapeVO collectGroupBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        return applyLendSer.collectGroupBar(reimCompanyShapeDTO);
    }

    @Override
    public ReimCompanyMixShapeVO collectProjectBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        return applyLendSer.collectProjectBar(reimCompanyShapeDTO);
    }

    @Override
    public ReimCompanyMixShapeVO collectAreaBar(ReimCompanyShapeDTO reimCompanyShapeDTO) throws SerException {
        return applyLendSer.collectAreaBar(reimCompanyShapeDTO);
    }

    @Override
    public ReimShapeVO collectAreaDetailBar(LendShapeDetailDTO reimburseShapeDetailDTO) throws SerException {
        return applyLendSer.collectAreaDetailBar(reimburseShapeDetailDTO);
    }

    @Override
    public LendMixReimShapeVO collectMixMonAndWeek(LendMixReimSelfShapeDTO lendMixReimShapeDTO) throws SerException {
        return applyLendSer.collectMixMonAndWeek(lendMixReimShapeDTO);
    }

    @Override
    public LendMixReimShapeVO collectMixCompany(LendMixCompanyShapeDTO lendMixCompanyShapeDTO) throws SerException {
        return applyLendSer.collectMixCompany(lendMixCompanyShapeDTO);
    }

    @Override
    public ReimShapeVO collectDetailMixCompany(LendMixCompanyShapeDTO lendMixCompanyShapeDTO) throws SerException {
        return applyLendSer.collectDetailMixCompany(lendMixCompanyShapeDTO);
    }

    @Override
    public SubjectDataBO findSubjects(String name) throws SerException {
        return applyLendSer.findSubjects(name);
    }

    @Override
    public SubjectDatasBO findSubjects1(String name) throws SerException {
        return applyLendSer.findSubjects1(name);
    }

    @Override
    public List<SecondSubjectDataBO> findSecondSubject(String firstSubjectCode) throws SerException {
        return applyLendSer.findSecondSubject(firstSubjectCode);
    }

    @Override
    public OptionBO analysisDiagram() throws SerException {
        return applyLendSer.analysisDiagram();
    }

    @Override
    public ApplyLendBO analyse(ApplyLendTO applyLendTO) throws SerException {
        return applyLendSer.analyse(applyLendTO);
    }

    @Override
    public byte[] businessCheckOut(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.businessCheckOut(applyLendDTO);
    }

    @Override
    public List<String> findAllName() throws SerException {
        return applyLendSer.findAllName();
    }

    @Override
    public List<String> findAllArea() throws SerException {
        return applyLendSer.findAllArea();
    }

    @Override
    public List<String> findDepartment() throws SerException {
        return applyLendSer.findDepartment();
    }

    @Override
    public List<String> findProject() throws SerException {
        return applyLendSer.findProject();
    }

    @Override
    public void sendEmail() throws SerException {
        applyLendSer.sendEmailTiming();
    }

    @Override
    public void departmentEmail() throws SerException {
        applyLendSer.departmentEmail();
    }

    @Override
    public void finanEmail() throws SerException {
        applyLendSer.finanEmail();
    }

}