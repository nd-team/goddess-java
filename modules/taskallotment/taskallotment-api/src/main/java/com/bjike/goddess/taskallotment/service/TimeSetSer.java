package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.taskallotment.bo.TimeSetBO;
import com.bjike.goddess.taskallotment.dto.TimeSetDTO;
import com.bjike.goddess.taskallotment.entity.TimeSet;
import com.bjike.goddess.taskallotment.to.TimeSetTO;

import java.util.List;

/**
 * 标准工时设置业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 11:23 ]
 * @Description: [ 标准工时设置业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TimeSetSer extends Ser<TimeSet, TimeSetDTO> {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<TimeSetBO> list(TimeSetDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    TimeSetBO save(TimeSetTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(TimeSetTO to) throws SerException;

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
    TimeSetBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(TimeSetDTO dto) throws SerException;

    /**
     * 定时邮件发送
     *
     * @throws SerException
     */
    void send() throws SerException;
}