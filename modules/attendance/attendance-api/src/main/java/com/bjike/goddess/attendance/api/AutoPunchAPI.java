package com.bjike.goddess.attendance.api;

import com.bjike.goddess.attendance.bo.AutoPunchBO;
import com.bjike.goddess.attendance.to.AutoPunchTO;
import com.bjike.goddess.common.api.exception.SerException;

/**
 * 自动打卡业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-06 09:22 ]
 * @Description: [ 自动打卡业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface AutoPunchAPI {
    /**
     * 启用自动打卡
     *
     * @param to
     * @throws SerException
     */
    void start(AutoPunchTO to) throws SerException;

    /**
     * 关闭自动打卡
     *
     * @throws SerException
     */
    void close() throws SerException;

    /**
     * 查看当前用户的自动打卡状态
     * @return
     * @throws SerException
     */
    Boolean currentStauts() throws SerException;

    /**
     * 获取当前用户设置的上下班时间
     *
     * @return
     * @throws SerException
     */
    AutoPunchBO get() throws SerException;
}