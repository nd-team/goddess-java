package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.service.Ser;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.dto.ProjectRangeDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.entity.ProjectRange;
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
public interface ProjectRangeSer extends Ser<ProjectRange, ProjectRangeDTO> {

}
