package com.bjike.goddess.equipmentprepared.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.equipmentprepared.bo.MoneyReadyBO;
import com.bjike.goddess.equipmentprepared.bo.MoneyReadyCountBO;
import com.bjike.goddess.equipmentprepared.dto.MoneyReadyDTO;
import com.bjike.goddess.equipmentprepared.service.MoneyReadySer;
import com.bjike.goddess.equipmentprepared.to.MoneyReadyTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资金准备审核业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-16 11:15 ]
 * @Description: [ 资金准备审核业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("moneyReadyApiImpl")
public class MoneyReadyApiImpl implements MoneyReadyAPI {
    @Autowired
    private MoneyReadySer moneyReadySer;

    @Override
    public MoneyReadyBO save(MoneyReadyTO to) throws SerException {
        return moneyReadySer.save(to);
    }

    @Override
    public void edit(MoneyReadyTO to) throws SerException {
        moneyReadySer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        moneyReadySer.delete(id);
    }

    @Override
    public List<MoneyReadyBO> list(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.list(dto);
    }

    @Override
    public MoneyReadyBO findByID(String id) throws SerException {
        return moneyReadySer.findByID(id);
    }

    @Override
    public List<MoneyReadyCountBO> count(Integer month) throws SerException {
        return moneyReadySer.count(month);
    }

    @Override
    public Long countSum(MoneyReadyDTO dto) throws SerException {
        return moneyReadySer.countSum(dto);
    }
}