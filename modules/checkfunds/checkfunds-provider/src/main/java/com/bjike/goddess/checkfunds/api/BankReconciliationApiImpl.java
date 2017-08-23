package com.bjike.goddess.checkfunds.api;

import com.bjike.goddess.bankrecords.bo.BankRecordPageListBO;
import com.bjike.goddess.checkfunds.bo.*;
import com.bjike.goddess.checkfunds.dto.BankReconciliationDTO;
import com.bjike.goddess.checkfunds.service.BankReconciliationSer;
import com.bjike.goddess.checkfunds.to.BankReconciliationTO;
import com.bjike.goddess.checkfunds.to.GuidePermissionTO;
import com.bjike.goddess.checkfunds.vo.SonPermissionObject;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * 银企对账（核对）业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-06-10 03:53 ]
 * @Description: [ 银企对账（核对）业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bankReconciliationApiImpl")
public class BankReconciliationApiImpl implements BankReconciliationAPI {
    @Autowired
    private BankReconciliationSer bankReconciliationSer;

    @Override
    public BankReconciliationBO save(BankReconciliationTO to) throws SerException {
        return bankReconciliationSer.save(to);
    }

    @Override
    public List<BankReconciliationBO> list(BankReconciliationDTO dto) throws SerException {
        return bankReconciliationSer.list(dto);
    }

    @Override
    public BankReconciliationBO findByID(String id) throws SerException {
        return bankReconciliationSer.findByID(id);
    }

    @Override
    public Long countNum(BankReconciliationDTO dto) throws SerException {
        return bankReconciliationSer.countNum(dto);
    }

    @Override
    public List<RemainAdjustBO> boList(String id) throws SerException {
        return bankReconciliationSer.boList(id);
    }

    @Override
    public void confirmAdjust(String id, Double balance) throws SerException {
        bankReconciliationSer.confirmAdjust(id, balance);
    }

    @Override
    public Set<String> allNames() throws SerException {
        return bankReconciliationSer.allNames();
    }

    @Override
    public void commit(String id) throws SerException {
        bankReconciliationSer.commit(id);
    }

    @Override
    public void aduitPass(String id) throws SerException {
        bankReconciliationSer.aduitPass(id);
    }

    @Override
    public void aduitNotPass(String id) throws SerException {
        bankReconciliationSer.aduitNotPass(id);
    }

    @Override
    public List<RemainAdjustBO> adjust(String id) throws SerException {
        return bankReconciliationSer.adjust(id);
    }

    @Override
    public List<FundDetailBO> fundDetail(String id) throws SerException {
        return bankReconciliationSer.fundDetail(id);
    }

    @Override
    public List<BankRecordPageListBO> bankDetail(String id) throws SerException {
        return bankReconciliationSer.bankDetail(id);
    }

    @Override
    public List<DebtorDifferBO> debtorDiffer(String id) throws SerException {
        return bankReconciliationSer.debtorDiffer(id);
    }

    @Override
    public List<CreditorDifferBO> creditorDiffer(String id) throws SerException {
        return bankReconciliationSer.creditorDiffer(id);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return bankReconciliationSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return bankReconciliationSer.guidePermission(guidePermissionTO);
    }
}
