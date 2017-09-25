package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.PunchBO;
import com.bjike.goddess.attendance.bo.PunchSonBO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.service.PunchSonSer;
import com.bjike.goddess.attendance.to.PunchSonTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 打卡子表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:26 ]
 * @Description: [ 打卡子表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("punchSonApiImpl")
public class PunchSonApiImpl implements PunchSonAPI {
    @Autowired
    private PunchSonSer punchSonSer;

    @Override
    public PunchSonBO save(PunchSonTO to) throws SerException {
        return punchSonSer.save(to);
    }

    @Override
    public String string(Double longitude, Double latitude, String area) throws SerException {
        return punchSonSer.string(longitude, latitude, area);
    }

    @Override
    public List<PunchBO> list(PunchDTO dto) throws SerException {
        return punchSonSer.list(dto);
    }

    @Override
    public Long count(PunchDTO dto) throws SerException {
        return punchSonSer.count(dto);
    }
}