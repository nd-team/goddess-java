package com.bjike.goddess.task.dao;

import com.bjike.goddess.common.jpa.dao.JpaRep;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.dto.ProjectRangeDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.entity.ProjectRange;

/**
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: [项目可见范围持久化接口]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public interface ProjectRangeRep extends JpaRep<ProjectRange, ProjectRangeDTO> {

}
