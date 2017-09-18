package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
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
    default List<ProjectVO> list(ProjectDTO dto) throws SerException {
        return null;
    }
}
