package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.AutoPunchBO;
import com.bjike.goddess.attendance.service.AutoPunchSer;
import com.bjike.goddess.attendance.to.AutoPunchTO;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 自动打卡业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-06 09:22 ]
 * @Description: [ 自动打卡业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("autoPunchApiImpl")
public class AutoPunchApiImpl implements AutoPunchAPI {
    @Autowired
    private AutoPunchSer autoPunchSer;

    @Override
    public void start(AutoPunchTO to) throws SerException {
        autoPunchSer.start(to);
    }

    @Override
    public void close() throws SerException {
        autoPunchSer.close();
    }

    @Override
    public Boolean currentStauts() throws SerException {
        return autoPunchSer.currentStauts();
    }

    @Override
    public AutoPunchBO get() throws SerException {
        return autoPunchSer.get();
    }
}