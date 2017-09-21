package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.TimeSetBO;
import com.bjike.goddess.taskallotment.dto.TimeSetDTO;
import com.bjike.goddess.taskallotment.service.TimeSetSer;
import com.bjike.goddess.taskallotment.to.TimeSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 标准工时设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 11:23 ]
 * @Description: [ 标准工时设置业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("timeSetApiImpl")
public class TimeSetApiImpl implements TimeSetAPI {
    @Autowired
    private TimeSetSer timeSetSer;

    @Override
    public List<TimeSetBO> list(TimeSetDTO dto) throws SerException {
        return timeSetSer.list(dto);
    }

    @Override
    public TimeSetBO save(TimeSetTO to) throws SerException {
        return timeSetSer.save(to);
    }

    @Override
    public void edit(TimeSetTO to) throws SerException {
        timeSetSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        timeSetSer.delete(id);
    }

    @Override
    public TimeSetBO findByID(String id) throws SerException {
        return timeSetSer.findByID(id);
    }

    @Override
    public Long count(TimeSetDTO dto) throws SerException {
        return timeSetSer.count(dto);
    }
}