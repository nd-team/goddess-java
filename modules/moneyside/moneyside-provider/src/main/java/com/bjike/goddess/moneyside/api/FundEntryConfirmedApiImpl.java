package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.FundEntryConfirmedBO;
import com.bjike.goddess.moneyside.dto.FundEntryConfirmedDTO;
import com.bjike.goddess.moneyside.service.FundEntryConfirmedSer;
import com.bjike.goddess.moneyside.to.GuidePermissionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金进入申请已确认业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:08 ]
 * @Description: [ 资金进入申请已确认业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fundEntryConfirmedApiImpl")
public class FundEntryConfirmedApiImpl implements FundEntryConfirmedAPI {
    @Autowired
    private FundEntryConfirmedSer fundEntryConfirmedSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return fundEntryConfirmedSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return fundEntryConfirmedSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countFundEntryConfirmed(FundEntryConfirmedDTO fundEntryConfirmedDTO) throws SerException {
        return fundEntryConfirmedSer.countFundEntryConfirmed(fundEntryConfirmedDTO);
    }

    @Override
    public FundEntryConfirmedBO getOne(String id) throws SerException {
        return fundEntryConfirmedSer.getOne(id);
    }

    @Override
    public List<FundEntryConfirmedBO> findListFundEntryConfirmed(FundEntryConfirmedDTO fundEntryConfirmedDTO) throws SerException {
        return fundEntryConfirmedSer.findListFundEntryConfirmed(fundEntryConfirmedDTO);
    }

    @Override
    public void removeFundEntryConfirmed(String id) throws SerException {
        fundEntryConfirmedSer.removeFundEntryConfirmed(id);
    }
}