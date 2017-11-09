package com.bjike.goddess.attendance.service;

import com.bjike.goddess.attendance.bo.DayCountSetBO;
import com.bjike.goddess.attendance.dto.DayCountSetDTO;
import com.bjike.goddess.attendance.entity.DayCountSet;
import com.bjike.goddess.attendance.to.DayCountSetTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;

import java.util.List;

/**
 * 日报汇总设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-11-03 04:20 ]
 * @Description: [ 日报汇总设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface DayCountSetSer extends Ser<DayCountSet, DayCountSetDTO> {
    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    DayCountSetBO save(DayCountSetTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(DayCountSetTO to) throws SerException;

    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<DayCountSetBO> list(DayCountSetDTO dto) throws SerException;

    /**
     * 删除
     *
     * @param id
     * @throws SerException
     */
    void delete(String id) throws SerException;

    /**
     * 通过id查找
     *
     * @param id
     * @return
     * @throws SerException
     */
    DayCountSetBO findByID(String id) throws SerException;

    /**
     * 总条数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(DayCountSetDTO dto) throws SerException;

    /**
     * 定时检测发送
     * @throws SerException
     */
    void send() throws SerException;
}