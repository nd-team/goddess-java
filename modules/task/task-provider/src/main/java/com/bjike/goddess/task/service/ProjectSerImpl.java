package com.bjike.goddess.task.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.task.dto.ProjectDTO;
import com.bjike.goddess.task.entity.Project;
import com.bjike.goddess.task.entity.ProjectRange;
import com.bjike.goddess.task.enums.ExecStatus;
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
    public List<Project> list(ProjectDTO dto, boolean page) throws SerException {
        if (null != dto.getExecStatus()) {
            dto.getConditions().add(Restrict.eq("execStatus", dto.getExecStatus()));
        }
        if (null != dto.getStatus()) {
            dto.getConditions().add(Restrict.eq("status", dto.getStatus()));
        }
        dto.getSorts().add("createTime");
        return super.findByCis(dto, page);
    }

    public List<Project> list(String userId, ProjectDTO dto) throws SerException {
        Status status = dto.getStatus();
        ExecStatus execStatus = dto.getExecStatus();
        UserDetailBO detail = detailAPI.findByUserId(userId);
        if (null == detail) {
            detail = new UserDetailBO();
        }
        StringBuilder sb = new StringBuilder();
        if (null != status) {
            sb.append("SELECT *  FROM (");
        }
        sb.append(" SELECT *  FROM task_project WHERE userId = '" + userId + "' ");
        sb.append(" UNION ");
        sb.append(" SELECT a.*  FROM task_project a,( ");
        sb.append(" SELECT pid  FROM task_project_range WHERE users LIKE '%" + userId + "%' ");
        if (null != detail.getDepartmentId()) {
            sb.append(" UNION ");
            sb.append(" SELECT pid  FROM task_project_range WHERE departments LIKE '%" + detail.getDepartmentId() + "%' ");
        }
        if (null != detail.getGroupId()) {
            sb.append(" UNION ");
            sb.append("   SELECT pid  FROM task_project_range WHERE groups LIKE '%" + detail.getGroupId() + "%'");
        }
        sb.append(")b WHERE a.id=b.pid");

        if (null != status) {
            sb.append(" and status=" + status.getCode());
        }
        if (null != execStatus) {
            sb.append(" and execStatus=" + execStatus.getCode());
        }
        sb.append(" order by createTime desc ");
        String sql = sb.toString();
        String[] fields = new String[]{"id", "createTime", "modifyTime", "area", "description", "execStatus", "name", "status"};
        return super.findBySql(sql, Project.class, fields);
    }

    @Override
    public Project findByTableId(String tableId) throws SerException {
            String sql = "select pid from task_table where id='" + tableId + "'";
            List<Object> objects = super.findBySql(sql);
            if(null!=objects && objects.size()>0){
                String pid =  String.valueOf(objects.get(0));
                return super.findById(pid);
            }
            throw  new SerException("找不到数据行");
        }
}
