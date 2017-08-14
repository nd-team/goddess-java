package com.bjike.goddess.event.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.event.bo.TimeSetBO;
import com.bjike.goddess.event.dto.TimeSetDTO;
import com.bjike.goddess.event.service.TimeSetSer;
import com.bjike.goddess.event.to.TimeSetTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 提醒间隔时间设置业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 04:11 ]
 * @Description: [ 提醒间隔时间设置业务接口实现 ]
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
    public void edit(TimeSetTO to) throws SerException {
        timeSetSer.edit(to);
    }

    @Override
    public TimeSetBO timeSet(TimeSetDTO dto) throws SerException {
        return timeSetSer.timeSet(dto);
    }

    @Override
    public void freeze(TimeSetDTO dto) throws SerException {
        timeSetSer.freeze(dto);
    }

    @Override
    public void thaw(TimeSetDTO dto) throws SerException {
        timeSetSer.thaw(dto);
    }
}