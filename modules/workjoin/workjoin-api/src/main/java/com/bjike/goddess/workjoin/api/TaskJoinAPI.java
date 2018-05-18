package com.bjike.goddess.workjoin.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.organize.bo.PositionInstructionBO;
import com.bjike.goddess.workjoin.bo.TaskJoinBO;
import com.bjike.goddess.workjoin.dto.TaskJoinDTO;
import com.bjike.goddess.workjoin.to.GuidePermissionTO;
import com.bjike.goddess.workjoin.to.TaskJoinTO;

import java.util.List;

/**
 * 任务交接业务接口
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-29 04:55 ]
 * @Description: [ 任务交接业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TaskJoinAPI {
    /**
     * 下拉导航权限
     */
    default Boolean sonPermission() throws SerException {
        return null;
    }

    /**
     * 导航权限
     */
    default Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }


    /**
     * 任务交接列表总条数
     */
    default Long countTaskJoin(TaskJoinDTO taskJoinDTO) throws SerException {
        return null;
    }

    /**
     * 一个任务交接
     *
     * @return class JoinInfoBO
     */
    default TaskJoinBO getOne(String id) throws SerException {
        return null;
    }

    /**
     * 任务交接
     *
     * @param taskJoinDTO 任务交接dto
     * @return class TaskJoinBO
     * @throws SerException
     */
    default List<TaskJoinBO> findListTaskJoin(TaskJoinDTO taskJoinDTO) throws SerException {
        return null;
    }

    /**
     * 添加任务交接
     *
     * @param taskJoinTO 任务交接数据to
     * @return class TaskJoinBO
     * @throws SerException
     */
    default TaskJoinBO insertTaskJoin(TaskJoinTO taskJoinTO) throws SerException {
        return null;
    }

    /**
     * 编辑任务交接
     *
     * @param taskJoinTO 任务交接数据to
     * @return class TaskJoinBO
     * @throws SerException
     */
    default TaskJoinBO editTaskJoin(TaskJoinTO taskJoinTO) throws SerException {
        return null;
    }

    /**
     * 根据id删除任务交接
     *
     * @throws SerException
     */
    default void removeTaskJoin(String id) throws SerException {

    }

    /**
     * 查询汇报对象
     */
    List<PositionInstructionBO> findPosition() throws SerException;


}