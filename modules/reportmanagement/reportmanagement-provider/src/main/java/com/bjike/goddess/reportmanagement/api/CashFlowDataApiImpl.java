package com.bjike.goddess.reportmanagement.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.reportmanagement.bo.CashFlowDataBO;
import com.bjike.goddess.reportmanagement.bo.CashFormulaDataBO;
import com.bjike.goddess.reportmanagement.bo.ReturnCashDataBO;
import com.bjike.goddess.reportmanagement.dto.CashFlowDataDTO;
import com.bjike.goddess.reportmanagement.service.CashFlowDataSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* 现金流量资料表业务接口实现
* @Author:			[ zhuangkaiqin ]
* @Date:			[  2017-11-20 03:02 ]
* @Description:	[ 现金流量资料表业务接口实现 ]
* @Version:		[ v1.0.0 ]
* @Copy:   		[ com.bjike ]
*/
@Service("cashFlowDataApiImpl")
public class CashFlowDataApiImpl implements CashFlowDataAPI  {

    @Autowired
    private CashFlowDataSer cashFlowDataSer;

    @Override
    public List<CashFlowDataBO> list(CashFlowDataDTO dto) throws SerException {
        return cashFlowDataSer.list(dto);
    }

    @Override
    public CashFormulaDataBO findFormula(String dataId) throws SerException {
        return cashFlowDataSer.findFormula(dataId);
    }

    @Override
    public Long findTotal(CashFlowDataDTO dto) throws SerException {
        return cashFlowDataSer.findTotal(dto);
    }

    @Override
    public ReturnCashDataBO findMoney(CashFlowDataDTO dto) throws SerException {
        return cashFlowDataSer.findMoney(dto);
    }

    @Override
    public void editMoney(CashFlowDataDTO dto) throws SerException {
        cashFlowDataSer.editMoney(dto);
    }
}