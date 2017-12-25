package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CreditorsInvestBO;
import com.bjike.goddess.moneyside.dto.CreditorsInvestDTO;
import com.bjike.goddess.moneyside.service.CreditorsInvestSer;
import com.bjike.goddess.moneyside.to.CreditorsInvestTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 投资条件-债权投资业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:33 ]
 * @Description: [ 投资条件-债权投资业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("creditorsInvestApiImpl")
public class CreditorsInvestApiImpl implements CreditorsInvestAPI {

    @Autowired
    private CreditorsInvestSer creditorsInvestSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return creditorsInvestSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return creditorsInvestSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countCreditorsInvest(CreditorsInvestDTO creditorsInvestDTO) throws SerException {
        return creditorsInvestSer.countCreditorsInvest(creditorsInvestDTO);
    }

    @Override
    public CreditorsInvestBO getOne(String id) throws SerException {
        return creditorsInvestSer.getOne(id);
    }

    @Override
    public List<CreditorsInvestBO> findListCreditorsInvest(CreditorsInvestDTO creditorsInvestDTO) throws SerException {
        return creditorsInvestSer.findListCreditorsInvest(creditorsInvestDTO);
    }

    @Override
    public CreditorsInvestBO insertCreditorsInvest(CreditorsInvestTO creditorsInvestTO) throws SerException {
        return creditorsInvestSer.insertCreditorsInvest(creditorsInvestTO);
    }

    @Override
    public CreditorsInvestBO editCreditorsInvest(CreditorsInvestTO creditorsInvestTO) throws SerException {
        return creditorsInvestSer.editCreditorsInvest(creditorsInvestTO);
    }

    @Override
    public void removeCreditorsInvest(String id) throws SerException {
        creditorsInvestSer.removeCreditorsInvest(id);
    }
}