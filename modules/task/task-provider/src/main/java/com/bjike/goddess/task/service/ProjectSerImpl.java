package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.enums.ExecStatus;
import com.bjike.goddess.task.to.ProjectTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
@Service
public class ProjectSerImpl extends ServiceImpl<Project, ProjectDTO> implements ProjectSer {
    @Autowired
    private UserAPI userAPI;
    @Override
    public void add(ProjectTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        Project project = BeanTransform.copyProperties(to,Project.class);
        project.setUserId(user.getId());
        super.save(project);
    }

    @Override
    public List<Project> list(ProjectDTO dto) throws SerException {
        return super.findByCis(dto);
    }
}
