package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.service.PhoneRemindSer;
import com.bjike.goddess.common.api.exception.SerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 手机提醒打卡业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 09:28 ]
 * @Description: [ 手机提醒打卡业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("phoneRemindApiImpl")
public class PhoneRemindApiImpl implements PhoneRemindAPI {
    @Autowired
    private PhoneRemindSer phoneRemindSer;

    @Override
    public void start() throws SerException {
        phoneRemindSer.start();
    }

    @Override
    public void close() throws SerException {
        phoneRemindSer.close();
    }

    @Override
    public Boolean currentStauts() throws SerException {
        return phoneRemindSer.currentStauts();
    }

    @Override
    public void quartz() throws SerException {
        phoneRemindSer.quartz();
    }
}