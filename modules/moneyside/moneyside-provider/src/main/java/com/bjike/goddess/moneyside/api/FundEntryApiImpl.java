package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.CollectBO;
import com.bjike.goddess.moneyside.bo.FundEntryBO;
import com.bjike.goddess.moneyside.dto.FundEntryDTO;
import com.bjike.goddess.moneyside.excel.SonPermissionObject;
import com.bjike.goddess.moneyside.service.FundEntrySer;
import com.bjike.goddess.moneyside.to.CollectTO;
import com.bjike.goddess.moneyside.to.FundEntryTO;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金进入申请业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:05 ]
 * @Description: [ 资金进入申请业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fundEntryApiImpl")
public class FundEntryApiImpl implements FundEntryAPI {

    @Autowired
    private FundEntrySer fundEntrySer;
    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return fundEntrySer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return fundEntrySer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countFundEntry(FundEntryDTO fundEntryDTO) throws SerException {
        return fundEntrySer.countFundEntry(fundEntryDTO);
    }

    @Override
    public FundEntryBO getOne(String id) throws SerException {
        return fundEntrySer.getOne(id);
    }

    @Override
    public List<FundEntryBO> findListFundEntry(FundEntryDTO fundEntryDTO) throws SerException {
        return fundEntrySer.findListFundEntry(fundEntryDTO);
    }

    @Override
    public FundEntryBO insertFundEntry(FundEntryTO fundEntryTO) throws SerException {
        return fundEntrySer.insertFundEntry(fundEntryTO);
    }

    @Override
    public FundEntryBO editFundEntry(FundEntryTO fundEntryTO) throws SerException {
        return fundEntrySer.editFundEntry(fundEntryTO);
    }

    @Override
    public void removeFundEntry(String id) throws SerException {
        fundEntrySer.removeFundEntry(id);
    }
    @Override
    public List<String> getInvestor() throws SerException {
        return fundEntrySer.getInvestor();
    }
    @Override
    public FundEntryBO audit (FundEntryTO fundEntryTO) throws SerException {
        return fundEntrySer.audit(fundEntryTO);
    }
    @Override
    public List<CollectBO> collect(CollectTO to) throws SerException {
        return fundEntrySer.collect(to);
    }
}