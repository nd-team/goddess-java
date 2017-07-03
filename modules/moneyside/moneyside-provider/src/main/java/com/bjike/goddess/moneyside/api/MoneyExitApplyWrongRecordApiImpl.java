package com.bjike.goddess.moneyside.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.moneyside.bo.MoneyExitApplyWrongRecordBO;
import com.bjike.goddess.moneyside.dto.MoneyExitApplyWrongRecordDTO;
import com.bjike.goddess.moneyside.service.MoneyExitApplyWrongRecordSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金退出申请有误记录业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-06-09 05:58 ]
 * @Description: [ 资金退出申请有误记录业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moneyExitApplyWrongRecordApiImpl")
public class MoneyExitApplyWrongRecordApiImpl implements MoneyExitApplyWrongRecordAPI {
    @Autowired
    private MoneyExitApplyWrongRecordSer moneyExitApplyWrongRecordSer;
    @Override
    public Long countMoneyExitApplyWrongRecord(MoneyExitApplyWrongRecordDTO moneyExitApplyWrongRecordDTO) throws SerException {
        return moneyExitApplyWrongRecordSer.countMoneyExitApplyWrongRecord(moneyExitApplyWrongRecordDTO);
    }

    @Override
    public MoneyExitApplyWrongRecordBO getOne(String id) throws SerException {
        return moneyExitApplyWrongRecordSer.getOne(id);
    }

    @Override
    public List<MoneyExitApplyWrongRecordBO> findListMoneyExitApplyWrongRecord(MoneyExitApplyWrongRecordDTO moneyExitApplyWrongRecordDTO) throws SerException {
        return moneyExitApplyWrongRecordSer.findListMoneyExitApplyWrongRecord(moneyExitApplyWrongRecordDTO);
    }
    @Override
    public void removeMoneyExitApplyWrongRecord(String id) throws SerException {
        moneyExitApplyWrongRecordSer.removeMoneyExitApplyWrongRecord(id);

    }
}