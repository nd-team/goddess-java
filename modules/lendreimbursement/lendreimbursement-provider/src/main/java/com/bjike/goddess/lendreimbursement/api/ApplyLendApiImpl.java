package com.bjike.goddess.lendreimbursement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.lendreimbursement.bo.AccountVoucherBO;
import com.bjike.goddess.lendreimbursement.bo.ApplyLendBO;
import com.bjike.goddess.lendreimbursement.bo.CollectDataBO;
import com.bjike.goddess.lendreimbursement.bo.LendAuditDetailBO;
import com.bjike.goddess.lendreimbursement.dto.ApplyLendDTO;
import com.bjike.goddess.lendreimbursement.service.ApplyLendSer;
import com.bjike.goddess.lendreimbursement.to.ApplyLendTO;
import com.bjike.goddess.lendreimbursement.to.LendGuidePermissionTO;
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
        return applyLendSer.getLendAuditDetailByApplyLendId( applyLendId );
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
        return applyLendSer.countBorrowRecord( applyLendDTO);
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
    public List<AccountVoucherBO> listAccountVoucherByRecord(String id) throws SerException {
        return applyLendSer.listAccountVoucherByRecord(id);
    }

    @Override
    public Long countReturn(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.countReturn( applyLendDTO);
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
        return applyLendSer.countRecTicket( applyLendDTO);
    }

    @Override
    public List<ApplyLendBO> listRecieveTicketRecord(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.listRecieveTicketRecord( applyLendDTO);
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
    public List<String> listAccountCom() throws SerException {
        return applyLendSer.listAccountCom();
    }

    @Override
    public byte[] exportExcel(ApplyLendDTO applyLendDTO) throws SerException {
        return applyLendSer.exportExcel(applyLendDTO);
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

}