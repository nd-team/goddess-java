package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.DayCountSetBO;
import com.bjike.goddess.attendance.dto.DayCountSetDTO;
import com.bjike.goddess.attendance.service.DayCountSetSer;
import com.bjike.goddess.attendance.to.DayCountSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 日报汇总设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:20 ]
 * @Description: [ 日报汇总设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("dayCountSetApiImpl")
public class DayCountSetApiImpl implements DayCountSetAPI {
    @Autowired
    private DayCountSetSer dayCountSetSer;

    @Override
    public DayCountSetBO save(DayCountSetTO to) throws SerException {
        return dayCountSetSer.save(to);
    }

    @Override
    public void edit(DayCountSetTO to) throws SerException {
        dayCountSetSer.edit(to);
    }

    @Override
    public List<DayCountSetBO> list(DayCountSetDTO dto) throws SerException {
        return dayCountSetSer.list(dto);
    }

    @Override
    public void delete(String id) throws SerException {
        dayCountSetSer.delete(id);
    }

    @Override
    public DayCountSetBO findByID(String id) throws SerException {
        return dayCountSetSer.findByID(id);
    }

    @Override
    public Long count(DayCountSetDTO dto) throws SerException {
        return dayCountSetSer.count(dto);
    }

    @Override
    public void send() throws SerException {
        dayCountSetSer.send();
    }
}