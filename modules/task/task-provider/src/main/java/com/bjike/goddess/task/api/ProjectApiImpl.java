package com.bjike.goddess.task.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.bo.ProjectBO;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.service.ProjectSer;
import com.bjike.goddess.task.to.ProjectTO;
import com.bjike.goddess.task.vo.ProjectVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 项目业务接口
 *
 * @Author: [liguiqin]
 * @Date: [2016-11-23 15:47]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service("projectApiImpl")
public class ProjectApiImpl implements ProjectAPI {
    @Autowired
    private ProjectSer projectSer;

    @Override
    public void add(ProjectTO to) throws SerException {
        projectSer.add(to);
    }

    @Override
    public List<ProjectBO> list(ProjectDTO dto) throws SerException {
        List<Project> projects = projectSer.list(dto);
        return BeanTransform.copyProperties(projects,ProjectBO.class);
    }

    @Override
    public List<ProjectBO> list(String userId, Status status) throws SerException {
        List<Project> projects = projectSer.list(userId,status);
        return BeanTransform.copyProperties(projects,ProjectBO.class);
    }
}
