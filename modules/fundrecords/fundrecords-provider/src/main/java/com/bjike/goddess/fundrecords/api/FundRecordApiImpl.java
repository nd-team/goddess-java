package com.bjike.goddess.fundrecords.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.fundrecords.bo.AnalyzeBO;
import com.bjike.goddess.fundrecords.bo.ConditionCollectBO;
import com.bjike.goddess.fundrecords.bo.FundRecordBO;
import com.bjike.goddess.fundrecords.bo.MonthCollectBO;
import com.bjike.goddess.fundrecords.dto.FundRecordDTO;
import com.bjike.goddess.fundrecords.service.FundRecordSer;
import com.bjike.goddess.fundrecords.to.CollectTO;
import com.bjike.goddess.fundrecords.to.FundRecordTO;
import com.bjike.goddess.voucher.dto.VoucherGenerateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金流水业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-20 09:33 ]
 * @Description: [ 资金流水业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("fundRecordApiImpl")
public class FundRecordApiImpl implements FundRecordAPI {

    @Autowired
    private FundRecordSer fundRecordSer;

    @Override
    public FundRecordBO add(FundRecordTO to) throws SerException {
        return fundRecordSer.insertModel(to);
    }

    @Override
    public FundRecordBO edit(FundRecordTO to) throws SerException {
        return fundRecordSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        fundRecordSer.delete(id);
    }

    @Override
    public List<FundRecordBO> pageList(FundRecordDTO dto) throws SerException {
        return fundRecordSer.pageList(dto);
    }

    @Override
    public Long count(FundRecordDTO dto) throws SerException {
        return (long)fundRecordSer.findAllBO(dto,new VoucherGenerateDTO()).size();
    }

    @Override
    public MonthCollectBO month(Integer year ,Integer month) throws SerException {
        return fundRecordSer.month(year ,month);
    }

    @Override
    public List<ConditionCollectBO> condition(CollectTO to) throws SerException {
        return fundRecordSer.condition(to);
    }

    @Override
    public AnalyzeBO analyze(CollectTO to) throws SerException {
        return fundRecordSer.analyze(to);
    }

}