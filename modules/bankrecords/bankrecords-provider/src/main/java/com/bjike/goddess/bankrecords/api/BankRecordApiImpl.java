package com.bjike.goddess.bankrecords.api;

import com.bjike.goddess.bankrecords.bo.*;
import com.bjike.goddess.bankrecords.dto.BankRecordDTO;
import com.bjike.goddess.bankrecords.service.BankRecordSer;
import com.bjike.goddess.bankrecords.to.BankRecordTO;
import com.bjike.goddess.bankrecords.to.GuidePermissionTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
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

    @Override
    public List<ExcelTitleBO> check(List<InputStream> inputStreams) throws SerException {
        return bankRecordSer.check(inputStreams);
    }

    @Override
    public void upload(BankRecordTO to) throws SerException {
        bankRecordSer.upload(to);
    }

    @Override
    public BankRecordBO find(String id) throws SerException {
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
        return bankRecordSer.collect(year, month, accountName);
    }

    @Override
    public BankRecordAnalyzeBO analyze(Integer year, Integer month, String accountName) throws SerException {
        return bankRecordSer.analyze(year, month, accountName);
    }

    @Override
    public BankRecordCompareBO compare(Integer year, Integer month) throws SerException {
        return bankRecordSer.compare(year, month);
    }

    @Override
    public List<BankRecordBO> findByCondition(Integer year, Integer month, String number) throws SerException {
        return bankRecordSer.findByCondition(year, month, number);
    }

    @Override
    public List<BankRecordCollectBO> collectByCondition(Integer year, Integer month, String number) throws SerException {
        return bankRecordSer.collectByCondition(year, month, number);
    }

    @Override
    public BankRecordPageListBO findById(String id) throws SerException {
        return BeanTransform.copyProperties(bankRecordSer.find(id),BankRecordPageListBO.class);
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO to) throws SerException {
        return bankRecordSer.guidePermission(to);
    }


}