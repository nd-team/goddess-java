package com.bjike.goddess.bankrecords.api;

import com.bjike.goddess.bankrecords.bo.BankRecordAnalyzeBO;
import com.bjike.goddess.bankrecords.bo.BankRecordBO;
import com.bjike.goddess.bankrecords.bo.BankRecordCollectBO;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.service.BankRecordSer;
import com.bjike.goddess.bankrecords.to.BankRecordTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 银行流水业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-22 05:35 ]
 * @Description: [ 银行流水业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("bankRecordApiImpl")
public class BankRecordApiImpl implements BankRecordAPI {

    @Autowired
    private BankRecordSer bankRecordSer;

    /*@Override
    public void upload(TransactionContext txContext,BankRecordTO to) throws SerException {
        bankRecordSer.upload(txContext,to);
    }*/
    @Override
    public void upload(BankRecordTO to) throws SerException {
        bankRecordSer.upload(to);
    }

    @Override
    public BankRecordBO findById(String id) throws SerException {
        return bankRecordSer.find(id);
    }

    @Override
    public Long count(BankRecordDTO dto) throws SerException {
        return bankRecordSer.count(dto);
    }

    @Override
    public List<BankRecordBO> pageList(BankRecordDTO dto) throws SerException {
        return bankRecordSer.pageList(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        bankRecordSer.delete(id);
    }

    @Override
    public List<BankRecordCollectBO> collect(Integer year, Integer month, String accountName) throws SerException {
        return bankRecordSer.collect(year,month,accountName);
    }

    @Override
    public BankRecordAnalyzeBO analyze(Integer year, Integer month, String accountName) throws SerException {
        return bankRecordSer.analyze(year,month,accountName);
    }

    @Override
    public List<String> check(BankRecordTO to) throws SerException {
        return bankRecordSer.check(to);
    }
}