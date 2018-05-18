package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.TaskRemindBO;
import com.bjike.goddess.taskallotment.dto.TaskRemindDTO;
import com.bjike.goddess.taskallotment.to.TaskRemindTO;

import java.util.List;

/**
 * 任务提醒业务接口
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 10:14 ]
 * @Description: [ 任务提醒业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TaskRemindAPI {
    /**
     * 列表
     *
     * @param dto
     * @return
     * @throws SerException
     */
    List<TaskRemindBO> list(TaskRemindDTO dto) throws SerException;

    /**
     * 添加
     *
     * @param to
     * @return
     * @throws SerException
     */
    TaskRemindBO save(TaskRemindTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to
     * @throws SerException
     */
    void edit(TaskRemindTO to) throws SerException;

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
    TaskRemindBO findByID(String id) throws SerException;

    /**
     * 查找总记录数
     *
     * @param dto
     * @return
     * @throws SerException
     */
    Long count(TaskRemindDTO dto) throws SerException;

    /**
     * 邮件定时发送
     *
     * @throws SerException
     */
    void mail() throws SerException;
}