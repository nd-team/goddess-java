package com.bjike.goddess.workjoin.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.workjoin.bo.TaskJoinBO;
import com.bjike.goddess.workjoin.dto.TaskJoinDTO;
import com.bjike.goddess.workjoin.entity.TaskJoin;
import com.bjike.goddess.workjoin.service.TaskJoinSer;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.TaskJoinTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 任务交接业务接口实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:55 ]
 * @Description: [ 任务交接业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("taskJoinApiImpl")
public class TaskJoinApiImpl implements TaskJoinAPI {
    @Autowired
    private TaskJoinSer taskJoinSer;
    @Override
    public Boolean sonPermission() throws SerException {
        return taskJoinSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return taskJoinSer.guidePermission(guidePermissionTO);
    }
    @Override
    public Long countTaskJoin(TaskJoinDTO taskJoinDTO) throws SerException {
        return taskJoinSer.countTaskJoin(taskJoinDTO);
    }

    @Override
    public TaskJoinBO getOne(String id) throws SerException {
        return taskJoinSer.getOne(id);
    }

    @Override
    public List<TaskJoinBO> findListTaskJoin(TaskJoinDTO taskJoinDTO) throws SerException {
        return taskJoinSer.findListTaskJoin(taskJoinDTO);
    }

    @Override
    public TaskJoinBO insertTaskJoin(TaskJoinTO taskJoinTO) throws SerException {
        return taskJoinSer.insertTaskJoin(taskJoinTO);
    }

    @Override
    public TaskJoinBO editTaskJoin(TaskJoinTO taskJoinTO) throws SerException {
        return taskJoinSer.editTaskJoin(taskJoinTO);
    }

    @Override
    public void removeTaskJoin(String id) throws SerException {
        taskJoinSer.removeTaskJoin(id);
    }

}