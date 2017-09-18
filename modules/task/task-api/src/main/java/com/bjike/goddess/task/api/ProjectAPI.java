package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.task.bo.ProjectBO;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.to.ProjectTO;
import com.bjike.goddess.task.vo.ProjectVO;

import java.util.List;

/**
 * @Author: [liguiqin]
 * @Date: [2017-09-15 16:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ProjectAPI {

    /**
     * 项目添加
     *
     * @param to
     * @throws SerException
     */
    default void add(ProjectTO to) throws SerException {

    }

    /**
     * 项目列表
     *
     * @param dto
     * @throws SerException
     */
    default List<ProjectBO> list(ProjectDTO dto) throws SerException {
        return null;
    }

    /**
     * 用户项目列表
     * status 为空查询全部
     *
     * @throws SerException
     */
    default List<ProjectBO> list(String userId, Status status) throws SerException {
        return null;
    }
}
