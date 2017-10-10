package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.PunchBO;
import com.bjike.goddess.attendance.bo.PunchSonBO;
import com.bjike.goddess.attendance.dto.PunchDTO;
import com.bjike.goddess.attendance.dto.PunchSonDTO;
import com.bjike.goddess.attendance.entity.PunchSon;
import com.bjike.goddess.attendance.to.PunchSonTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 打卡子表业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-22 03:26 ]
 * @Description: [ 打卡子表业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface PunchSonSer extends Ser<PunchSon, PunchSonDTO> {

    /**
     * 打卡
     *
     * @param to
     * @return
     * @throws SerException
     */
    PunchSonBO save(PunchSonTO to) throws SerException;

    /**
     * 查看打卡范围
     *
     * @param longitude 当前经度
     * @param latitude  当前纬度
     * @param area      当前位置
     * @return
     * @throws SerException
     */
    String string(Double longitude, Double latitude, String area) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<PunchBO> list(PunchDTO dto) throws SerException;

    /**
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(PunchDTO dto) throws SerException;
}