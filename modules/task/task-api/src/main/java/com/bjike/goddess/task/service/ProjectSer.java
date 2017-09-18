package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.enums.ExecStatus;
import com.bjike.goddess.task.to.ProjectTO;

import java.util.List;

/**
 * 项目业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2017-09-15 16:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ProjectSer extends Ser<Project, ProjectDTO> {
    default void add(ProjectTO to) throws SerException {

    }

    /**
     * 项目列表
     *
     * @param dto
     * @throws SerException
     */
    default List<Project> list(ProjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 用户项目列表
     * status 为空查询全部
     *
     * @throws SerException
     */
    default List<Project> list(String userId, Status status) throws SerException {
        return null;
    }
}
