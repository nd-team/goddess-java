package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.CashAnalyse1BO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseBO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseCashBO;
import com.bjike.goddess.reportmanagement.bo.CashAnalyseManaBO;
import com.bjike.goddess.reportmanagement.dto.CashAnalyseDTO;
import com.bjike.goddess.reportmanagement.service.CashAnalyseSer;
import com.bjike.goddess.reportmanagement.to.CashAnalyse1TO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 现金流量分析业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-24 10:20 ]
 * @Description: [ 现金流量分析业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("cashAnalyseApiImpl")
public class CashAnalyseApiImpl implements CashAnalyseAPI {
    @Autowired
    private CashAnalyseSer cashAnalyseSer;

    @Override
    public List<CashAnalyseBO> managerAnalyse1(CashAnalyseDTO dto) throws SerException {
        return cashAnalyseSer.managerAnalyse1(dto);
    }

    @Override
    public List<CashAnalyseBO> managerAnalyse2(CashAnalyseDTO dto) throws SerException {
        return cashAnalyseSer.managerAnalyse2(dto);
    }

    @Override
    public List<CashAnalyseBO> investmentAnalyse1(CashAnalyseDTO dto) throws SerException {
        return cashAnalyseSer.investmentAnalyse1(dto);
    }

    @Override
    public List<CashAnalyseBO> financingAnalyse(CashAnalyseDTO dto) throws SerException {
        return cashAnalyseSer.financingAnalyse(dto);
    }

    @Override
    public List<CashAnalyseCashBO> cashAnalyse(CashAnalyseDTO dto) throws SerException {
        return cashAnalyseSer.cashAnalyse(dto);
    }

    @Override
    public List<CashAnalyseManaBO> managerAnalyse3(CashAnalyseDTO dto) throws SerException {
        return cashAnalyseSer.managerAnalyse3(dto);
    }

    @Override
    public CashAnalyse1BO findById(String id) throws SerException {
        return cashAnalyseSer.findByid(id);
    }

    @Override
    public void edit(CashAnalyse1TO to) throws SerException {
        cashAnalyseSer.edit(to);
    }
}