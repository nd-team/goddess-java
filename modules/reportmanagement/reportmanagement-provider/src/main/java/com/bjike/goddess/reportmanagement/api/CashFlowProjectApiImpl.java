package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.CashFlowProjectBO;
import com.bjike.goddess.reportmanagement.bo.CashFormulaBO;
import com.bjike.goddess.reportmanagement.bo.CashRateBO;
import com.bjike.goddess.reportmanagement.bo.ReturnCashBO;
import com.bjike.goddess.reportmanagement.dto.CashFlowProjectDTO;
import com.bjike.goddess.reportmanagement.service.CashFlowProjectSer;
import com.bjike.goddess.reportmanagement.to.CashRateTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 现金流量项目表业务接口实现
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-11-20 03:06 ]
 * @Description: [ 现金流量项目表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("cashFlowProjectApiImpl")
public class CashFlowProjectApiImpl implements CashFlowProjectAPI {

    @Autowired
    private CashFlowProjectSer cashFlowProjectSer;

    @Override
    public List<CashFlowProjectBO> list(CashFlowProjectDTO dto) throws SerException {
        return cashFlowProjectSer.list(dto);
    }

    @Override
    public CashFormulaBO findFormula(String id) throws SerException {
        return cashFlowProjectSer.findFormula(id);
    }

    @Override
    public Long findTotal(CashFlowProjectDTO dto) throws SerException {
        return cashFlowProjectSer.findTotal(dto);
    }

    @Override
    public ReturnCashBO findMoney(CashFlowProjectDTO dto) throws SerException {
        return cashFlowProjectSer.findMoney(dto);
    }

    @Override
    public void editMoney(CashFlowProjectDTO dto) throws SerException {
        cashFlowProjectSer.editMoney(dto);
    }

    @Override
    public List<CashRateBO> findRate(String id) throws SerException {
        return cashFlowProjectSer.findRate(id);
    }

    @Override
    public void editRate(CashRateTO to) throws SerException {
        cashFlowProjectSer.editRate(to);
    }
}