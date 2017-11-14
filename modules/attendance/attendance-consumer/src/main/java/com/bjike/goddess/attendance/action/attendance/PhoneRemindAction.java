package com.bjike.goddess.attendance.action.attendance;

import com.bjike.goddess.attendance.api.PhoneRemindAPI;
import com.bjike.goddess.common.api.exception.ActException;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.restful.Result;
import com.bjike.goddess.common.consumer.restful.ActResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 手机提醒打卡
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 09:28 ]
 * @Description: [ 手机提醒打卡 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@RestController
@RequestMapping("phoneremind")
public class PhoneRemindAction {
    @Autowired
    private PhoneRemindAPI phoneRemindAPI;

    /**
     * 启用打卡提醒
     *
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/start")
    public Result start() throws ActException {
        try {
            phoneRemindAPI.start();
            return new ActResult("启用成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 关闭打卡提醒
     *
     * @throws ActException
     * @version v1
     */
    @PutMapping("v1/close")
    public Result close() throws ActException {
        try {
            phoneRemindAPI.close();
            return new ActResult("关闭成功");
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }

    /**
     * 查看当前用户的提醒状态
     *
     * @throws ActException
     * @version v1
     */
    @GetMapping("v1/current/stauts")
    public Result currentStauts() throws ActException {
        try {
            return ActResult.initialize(phoneRemindAPI.currentStauts());
        } catch (SerException e) {
            throw new ActException(e.getMessage());
        }
    }
}