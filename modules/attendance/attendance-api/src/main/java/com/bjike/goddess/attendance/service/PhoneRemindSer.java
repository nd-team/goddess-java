package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.dto.PhoneRemindDTO;
import com.bjike.goddess.attendance.entity.PhoneRemind;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

/**
 * 手机提醒打卡业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 09:28 ]
 * @Description: [ 手机提醒打卡业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PhoneRemindSer extends Ser<PhoneRemind, PhoneRemindDTO> {
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
     * @throws SerException
     */
    void quartz() throws SerException;
}