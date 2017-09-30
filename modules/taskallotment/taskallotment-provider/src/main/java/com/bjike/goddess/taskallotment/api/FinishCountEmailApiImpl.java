package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.FinishCountEmailBO;
import com.bjike.goddess.taskallotment.dto.FinishCountEmailDTO;
import com.bjike.goddess.taskallotment.service.FinishCountEmailSer;
import com.bjike.goddess.taskallotment.to.FinishCountEmailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 完成情况汇总设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:31 ]
 * @Description: [ 完成情况汇总设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("finishCountEmailApiImpl")
public class FinishCountEmailApiImpl implements FinishCountEmailAPI {
    @Autowired
    private FinishCountEmailSer finishCountEmailSer;

    @Override
    public List<FinishCountEmailBO> list(FinishCountEmailDTO dto) throws SerException {
        return finishCountEmailSer.list(dto);
    }

    @Override
    public FinishCountEmailBO save(FinishCountEmailTO to) throws SerException {
        return finishCountEmailSer.save(to);
    }

    @Override
    public void edit(FinishCountEmailTO to) throws SerException {
        finishCountEmailSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        finishCountEmailSer.delete(id);
    }

    @Override
    public FinishCountEmailBO findByID(String id) throws SerException {
        return finishCountEmailSer.findByID(id);
    }

    @Override
    public Long count(FinishCountEmailDTO dto) throws SerException {
        return finishCountEmailSer.count(dto);
    }

    @Override
    public void send() throws SerException {
        finishCountEmailSer.send();
    }
}