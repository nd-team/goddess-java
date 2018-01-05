package com.bjike.goddess.progressmanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.progressmanage.bo.TaskReceiveBO;
import com.bjike.goddess.progressmanage.dto.ProjectInfoDTO;
import com.bjike.goddess.progressmanage.dto.TaskReceiveDTO;
import com.bjike.goddess.progressmanage.to.TaskReceiveTO;

import java.util.List;

/**
 * 任务接收业务接口
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-07-03 02:33 ]
 * @Description: [ 任务接收业务接口 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public interface TaskReceiveAPI {

    /**
     * 新增
     *
     * @param to 任务接收
     * @return 任务接收
     */
    TaskReceiveBO add(TaskReceiveTO to) throws SerException;

    /**
     * 编辑
     *
     * @param to 任务接收
     * @return 任务接收
     */
    TaskReceiveBO edit(TaskReceiveTO to) throws SerException;

    /**
     * 删除
     *
     * @param id id
     */
    void delete(String id) throws SerException;

    /**
     * 列表
     *
     * @param dto 分页条件
     * @return 任务接收结果集
     */
    List<TaskReceiveBO> pageList(TaskReceiveDTO dto) throws SerException;
}