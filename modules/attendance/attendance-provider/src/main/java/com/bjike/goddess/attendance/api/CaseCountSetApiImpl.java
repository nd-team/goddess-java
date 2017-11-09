package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.CaseCountSetBO;
import com.bjike.goddess.attendance.dto.CaseCountSetDTO;
import com.bjike.goddess.attendance.service.CaseCountSetSer;
import com.bjike.goddess.attendance.to.CaseCountSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考勤情况汇总设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-19 06:18 ]
 * @Description: [ 考勤情况汇总设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("caseCountSetApiImpl")
public class CaseCountSetApiImpl implements CaseCountSetAPI {
    @Autowired
    private CaseCountSetSer caseCountSetSer;

    @Override
    public CaseCountSetBO save(CaseCountSetTO to) throws SerException {
        return caseCountSetSer.save(to);
    }

    @Override
    public void edit(CaseCountSetTO to) throws SerException {
        caseCountSetSer.edit(to);
    }

    @Override
    public List<CaseCountSetBO> list(CaseCountSetDTO dto) throws SerException {
        return caseCountSetSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        caseCountSetSer.delete(id);
    }

    @Override
    public CaseCountSetBO findByID(String id) throws SerException {
        return caseCountSetSer.findByID(id);
    }

    @Override
    public Long count(CaseCountSetDTO dto) throws SerException {
        return caseCountSetSer.count(dto);
    }

    @Override
    public void send() throws SerException {
        caseCountSetSer.send();
    }
}