package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.ConfirmCountEmailBO;
import com.bjike.goddess.taskallotment.dto.ConfirmCountEmailDTO;
import com.bjike.goddess.taskallotment.service.ConfirmCountEmailSer;
import com.bjike.goddess.taskallotment.to.ConfirmCountEmailTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 分配及确认汇总设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-28 09:44 ]
 * @Description: [ 分配及确认汇总设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("confirmCountEmailApiImpl")
public class ConfirmCountEmailApiImpl implements ConfirmCountEmailAPI {
    @Autowired
    private ConfirmCountEmailSer confirmCountEmailSer;

    @Override
    public List<ConfirmCountEmailBO> list(ConfirmCountEmailDTO dto) throws SerException {
        return confirmCountEmailSer.list(dto);
    }

    @Override
    public ConfirmCountEmailBO save(ConfirmCountEmailTO to) throws SerException {
        return confirmCountEmailSer.save(to);
    }

    @Override
    public void edit(ConfirmCountEmailTO to) throws SerException {
        confirmCountEmailSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        confirmCountEmailSer.delete(id);
    }

    @Override
    public ConfirmCountEmailBO findByID(String id) throws SerException {
        return confirmCountEmailSer.findByID(id);
    }

    @Override
    public Long count(ConfirmCountEmailDTO dto) throws SerException {
        return confirmCountEmailSer.count(dto);
    }

    @Override
    public void send() throws SerException {
        confirmCountEmailSer.send();
    }
}