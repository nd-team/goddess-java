package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.taskallotment.bo.TaskRemindBO;
import com.bjike.goddess.taskallotment.dto.TaskRemindDTO;
import com.bjike.goddess.taskallotment.service.TaskRemindSer;
import com.bjike.goddess.taskallotment.to.TaskRemindTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务提醒业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-15 10:14 ]
 * @Description: [ 任务提醒业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("taskRemindApiImpl")
public class TaskRemindApiImpl implements TaskRemindAPI {
    @Autowired
    private TaskRemindSer taskRemindSer;

    @Override
    public List<TaskRemindBO> list(TaskRemindDTO dto) throws SerException {
        return taskRemindSer.list(dto);
    }

    @Override
    public TaskRemindBO save(TaskRemindTO to) throws SerException {
        return taskRemindSer.save(to);
    }

    @Override
    public void edit(TaskRemindTO to) throws SerException {
        taskRemindSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        taskRemindSer.delete(id);
    }

    @Override
    public TaskRemindBO findByID(String id) throws SerException {
        return taskRemindSer.findByID(id);
    }

    @Override
    public Long count(TaskRemindDTO dto) throws SerException {
        return taskRemindSer.count(dto);
    }
}