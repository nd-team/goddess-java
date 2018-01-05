package com.bjike.goddess.event.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.event.bo.TimeSetBO;
import com.bjike.goddess.event.dto.TimeSetDTO;
import com.bjike.goddess.event.to.TimeSetTO;

import java.util.List;

/**
 * 提醒间隔时间设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-08-09 04:11 ]
 * @Description: [ 提醒间隔时间设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TimeSetAPI {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<TimeSetBO> list(TimeSetDTO dto) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(TimeSetTO to) throws SerException;

    /**
     * 获取一个提醒间隔时间设置
     *
     * @param dto
     * @return
     * @throws SerException
     */
    TimeSetBO timeSet(TimeSetDTO dto) throws SerException;

    /**
     * 冻结
     *
     * @param dto
     * @throws SerException
     */
    void freeze(TimeSetDTO dto) throws SerException;

    /**
     * 解冻
     *
     * @param dto
     * @throws SerException
     */
    void thaw(TimeSetDTO dto) throws SerException;
}