package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.VacateCountSetBO;
import com.bjike.goddess.attendance.dto.VacateCountSetDTO;
import com.bjike.goddess.attendance.service.VacateCountSetSer;
import com.bjike.goddess.attendance.to.VacateCountSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 请假汇总通报设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:19 ]
 * @Description: [ 请假汇总通报设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("vacateCountSetApiImpl")
public class VacateCountSetApiImpl implements VacateCountSetAPI {
    @Autowired
    private VacateCountSetSer vacateCountSetSer;

    @Override
    public VacateCountSetBO save(VacateCountSetTO to) throws SerException {
        return vacateCountSetSer.save(to);
    }

    @Override
    public void edit(VacateCountSetTO to) throws SerException {
        vacateCountSetSer.edit(to);
    }

    @Override
    public List<VacateCountSetBO> list(VacateCountSetDTO dto) throws SerException {
        return vacateCountSetSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        vacateCountSetSer.delete(id);
    }

    @Override
    public VacateCountSetBO findByID(String id) throws SerException {
        return vacateCountSetSer.findByID(id);
    }

    @Override
    public Long count(VacateCountSetDTO dto) throws SerException {
        return vacateCountSetSer.count(dto);
    }

    @Override
    public void send() throws SerException {
        vacateCountSetSer.send();
    }
}