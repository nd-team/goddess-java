package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.EquityInvestBO;
import com.bjike.goddess.moneyside.dto.EquityInvestDTO;
import com.bjike.goddess.moneyside.service.EquityInvestSer;
import com.bjike.goddess.moneyside.to.EquityInvestTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 投资条件-股权投资业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:38 ]
 * @Description: [ 投资条件-股权投资业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("equityInvestApiImpl")
public class EquityInvestApiImpl implements EquityInvestAPI {
    @Autowired
    private EquityInvestSer equityInvestSer;

    @Override
    public Long countEquityInvest(EquityInvestDTO equityInvestDTO) throws SerException {
        return equityInvestSer.countEquityInvest(equityInvestDTO);
    }

    @Override
    public EquityInvestBO getOne(String id) throws SerException {
        return equityInvestSer.getOne(id);
    }

    @Override
    public List<EquityInvestBO> findListEquityInvest(EquityInvestDTO equityInvestDTO) throws SerException {
        return equityInvestSer.findListEquityInvest(equityInvestDTO);
    }

    @Override
    public EquityInvestBO insertEquityInvest(EquityInvestTO equityInvestTO) throws SerException {
        return equityInvestSer.insertEquityInvest(equityInvestTO);
    }

    @Override
    public EquityInvestBO editEquityInvest(EquityInvestTO equityInvestTO) throws SerException {
        return equityInvestSer.editEquityInvest(equityInvestTO);
    }

    @Override
    public void removeEquityInvest(String id) throws SerException {
        equityInvestSer.removeEquityInvest(id);
    }
}