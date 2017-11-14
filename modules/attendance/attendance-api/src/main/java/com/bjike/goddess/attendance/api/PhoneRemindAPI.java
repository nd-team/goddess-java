package com.bjike.goddess.attendance.api;

import com.bjike.goddess.common.api.exception.SerException;

/**
 * 手机提醒打卡业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 09:28 ]
 * @Description: [ 手机提醒打卡业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PhoneRemindAPI {
    /**
     * 启用打卡提醒
     *
     * @throws SerException
     */
    void start() throws SerException;

    /**
     * 关闭打卡提醒
     *
     * @throws SerException
     */
    void close() throws SerException;

    /**
     * 查看当前用户的提醒状态
     *
     * @return
     * @throws SerException
     */
    Boolean currentStauts() throws SerException;

    /**
     * 定时消息推送
     *
     * @throws SerException
     */
    void quartz() throws SerException;
}