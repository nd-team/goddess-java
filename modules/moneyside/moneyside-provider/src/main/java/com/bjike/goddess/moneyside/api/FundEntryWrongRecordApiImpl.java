package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.FundEntryWrongRecordBO;
import com.bjike.goddess.moneyside.dto.FundEntryWrongRecordDTO;
import com.bjike.goddess.moneyside.service.FundEntryWrongRecordSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金进入申请有误记录业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-05 11:07 ]
 * @Description: [ 资金进入申请有误记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fundEntryWrongRecordApiImpl")
public class FundEntryWrongRecordApiImpl implements FundEntryWrongRecordAPI {
    @Autowired
    private FundEntryWrongRecordSer fundEntryWrongRecordSer;
    @Override
    public Long countFundEntryWrongRecord(FundEntryWrongRecordDTO fundEntryWrongRecordDTO) throws SerException {
        return fundEntryWrongRecordSer.countFundEntryWrongRecord(fundEntryWrongRecordDTO);
    }

    @Override
    public FundEntryWrongRecordBO getOne(String id) throws SerException {
        return fundEntryWrongRecordSer.getOne(id);
    }

    @Override
    public List<FundEntryWrongRecordBO> findListFundEntryWrongRecord(FundEntryWrongRecordDTO fundEntryWrongRecordDTO) throws SerException {
        return fundEntryWrongRecordSer.findListFundEntryWrongRecord(fundEntryWrongRecordDTO);
    }

    @Override
    public void removeFundEntryWrongRecord(String id) throws SerException {
        fundEntryWrongRecordSer.removeFundEntryWrongRecord(id);
    }
}