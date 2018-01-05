package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.InvestFormBO;
import com.bjike.goddess.moneyside.dto.InvestFormDTO;
import com.bjike.goddess.moneyside.service.InvestFormSer;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.InvestFormTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 投资形式业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 02:28 ]
 * @Description: [ 投资形式业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("investFormApiImpl")
public class InvestFormApiImpl implements InvestFormAPI {

    @Autowired
    private InvestFormSer investFormSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return investFormSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return investFormSer.guidePermission(guidePermissionTO);
    }

    @Override
    public Long countInvestForm(InvestFormDTO investFormDTO) throws SerException {
        return investFormSer.countInvestForm(investFormDTO);
    }

    @Override
    public InvestFormBO getOne(String id) throws SerException {
        return investFormSer.getOne(id);
    }

    @Override
    public List<InvestFormBO> findListInvestForm(InvestFormDTO investFormDTO) throws SerException {
        return investFormSer.findListInvestForm(investFormDTO);
    }

    @Override
    public InvestFormBO insertInvestForm(InvestFormTO investFormTO) throws SerException {
        return investFormSer.insertInvestForm(investFormTO);
    }

    @Override
    public InvestFormBO editInvestForm(InvestFormTO investFormTO) throws SerException {
        return investFormSer.editInvestForm(investFormTO);
    }


    @Override
    public void removeInvestForm(String id) throws SerException {
        investFormSer.removeInvestForm(id);
    }
}