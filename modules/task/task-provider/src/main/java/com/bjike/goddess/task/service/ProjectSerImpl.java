package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.entity.ProjectRange;
import com.bjike.goddess.task.to.ProjectTO;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.api.UserDetailAPI;
import com.bjike.goddess.user.bo.UserBO;
import com.bjike.goddess.user.bo.UserDetailBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private UserDetailAPI detailAPI;
    @Autowired
    private ProjectRangeSer projectRangeSer;

    @Transactional
    @Override
    public void add(ProjectTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        Project project = BeanTransform.copyProperties(to, Project.class);
        project.setUserId(user.getId());
        super.save(project);
        ProjectRange range = new ProjectRange();
        range.setDepartments(StringUtils.join(to.getDepartments(), ","));
        range.setGroups(StringUtils.join(to.getGroups(), ","));
        range.setUsers(StringUtils.join(to.getUsers(), ","));
        range.setProject(project);
        projectRangeSer.save(range);

    }

    @Override
    public List<Project> list(ProjectDTO dto) throws SerException {
        return super.findByCis(dto);
    }

    @Override
    public List<Project> list(String userId, Status status) throws SerException {
        UserDetailBO detail = detailAPI.findByUserId(userId);
        if(null==detail){
            detail = new UserDetailBO();
        }
        StringBuilder sb = new StringBuilder();
        if (null != status) {
            sb.append("select *  from (");
        }
        sb.append(" select *  from task_project where userId = '" + userId + "' ");
        sb.append(" union ");
        sb.append(" select a.*  from task_project a,( ");
        sb.append(" select pid  from task_project_range where users like '%" + userId + "%' ");
        if(null!=detail.getDepartmentId()){
            sb.append(" union ");
            sb.append(" select pid  from task_project_range where departments like '%" + detail.getDepartmentId() + "%' ");
        }
        if(null!=detail.getGroupId()){
            sb.append(" union ");
            sb.append("   select pid  from task_project_range where groups like '%" + detail.getGroupId() + "%'");
        }
        sb.append( ")b where a.id=b.pid ");

        if (null != status) {
            sb.append(") where status=" + status.getCode());
        }
        String sql = sb.toString();
        String[] fields = new String[]{"id", "createTime", "modifyTime", "area", "description", "execStatus", "name", "status"};
        return super.findBySql(sql, Project.class, fields);
    }
}
