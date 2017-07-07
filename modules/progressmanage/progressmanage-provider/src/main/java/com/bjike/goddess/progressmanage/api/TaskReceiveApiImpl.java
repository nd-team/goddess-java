package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.TaskReceiveBO;
import com.bjike.goddess.progressmanage.dto.ProjectInfoDTO;
import com.bjike.goddess.progressmanage.dto.TaskReceiveDTO;
import com.bjike.goddess.progressmanage.service.TaskReceiveSer;
import com.bjike.goddess.progressmanage.to.TaskReceiveTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务接收业务接口实现
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-07-03 02:33 ]
 * @Description: [ 任务接收业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("taskReceiveApiImpl")
public class TaskReceiveApiImpl implements TaskReceiveAPI {
    @Autowired
    private TaskReceiveSer taskReceiveSer;

    @Override
    public TaskReceiveBO add(TaskReceiveTO to) throws SerException {
        return taskReceiveSer.insertModel(to);
    }

    @Override
    public TaskReceiveBO edit(TaskReceiveTO to) throws SerException {
        return taskReceiveSer.updateModel(to);
    }

    @Override
    public void delete(String id) throws SerException {
        taskReceiveSer.delete(id);
    }

    @Override
    public List<TaskReceiveBO> pageList(TaskReceiveDTO dto) throws SerException {
        return taskReceiveSer.pageList(dto);
    }
}