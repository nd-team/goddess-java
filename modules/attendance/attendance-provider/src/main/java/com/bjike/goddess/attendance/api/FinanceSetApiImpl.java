package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.FinanceSetBO;
import com.bjike.goddess.attendance.dto.FinanceSetDTO;
import com.bjike.goddess.attendance.service.FinanceSetSer;
import com.bjike.goddess.attendance.to.FinanceSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 财务出勤设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:15 ]
 * @Description: [ 财务出勤设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("financeSetApiImpl")
public class FinanceSetApiImpl implements FinanceSetAPI {
    @Autowired
    private FinanceSetSer financeSetSer;

    @Override
    public FinanceSetBO save(FinanceSetTO to) throws SerException {
        return financeSetSer.save(to);
    }

    @Override
    public void edit(FinanceSetTO to) throws SerException {
        financeSetSer.edit(to);
    }

    @Override
    public List<FinanceSetBO> list(FinanceSetDTO dto) throws SerException {
        return financeSetSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        financeSetSer.delete(id);
    }

    @Override
    public FinanceSetBO findByID(String id) throws SerException {
        return financeSetSer.findByID(id);
    }

    @Override
    public Long count(FinanceSetDTO dto) throws SerException {
        return financeSetSer.count(dto);
    }

    @Override
    public void send() throws SerException {
        financeSetSer.send();
    }
}