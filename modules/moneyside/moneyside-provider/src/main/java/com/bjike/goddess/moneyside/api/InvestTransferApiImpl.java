package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.InvestTransferBO;
import com.bjike.goddess.moneyside.dto.InvestTransferDTO;
import com.bjike.goddess.moneyside.service.InvestFormSer;
import com.bjike.goddess.moneyside.service.InvestTransferSer;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import com.bjike.goddess.moneyside.to.InvestTransferTO;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 投资转让业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 03:09 ]
 * @Description: [ 投资转让业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("investTransferApiImpl")
public class InvestTransferApiImpl implements InvestTransferAPI {
    @Autowired
    private InvestTransferSer investTransferSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return investTransferSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return investTransferSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        return investTransferSer.countInvestTransfer(investTransferDTO);
    }

    @Override
    public InvestTransferBO getOne(String id) throws SerException {
        return investTransferSer.getOne(id);
    }

    @Override
    public List<InvestTransferBO> findListInvestTransfer(InvestTransferDTO investTransferDTO) throws SerException {
        return investTransferSer.findListInvestTransfer(investTransferDTO);
    }

    @Override
    public InvestTransferBO insertInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        return investTransferSer.insertInvestTransfer(investTransferTO);
    }

    @Override
    public InvestTransferBO editInvestTransfer(InvestTransferTO investTransferTO) throws SerException {
        return investTransferSer.editInvestTransfer(investTransferTO);
    }

    @Override
    public void removeInvestTransfer(String id) throws SerException {
        investTransferSer.removeInvestTransfer(id);
    }

    @Override
    public List<UserBO> findUserListInOrgan() throws SerException {
        return investTransferSer.findUserListInOrgan();
    }
}