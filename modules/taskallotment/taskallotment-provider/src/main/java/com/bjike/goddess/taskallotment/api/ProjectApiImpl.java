package com.bjike.goddess.taskallotment.api;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.*;
import com.bjike.goddess.taskallotment.entity.CustomTitle;
import com.bjike.goddess.taskallotment.entity.Project;
import com.bjike.goddess.taskallotment.entity.TaskNode;
import com.bjike.goddess.taskallotment.enums.Status;
import com.bjike.goddess.taskallotment.excel.ProjectExcel;
import com.bjike.goddess.taskallotment.excel.TableExcel;
import com.bjike.goddess.taskallotment.service.CustomTitleSer;
import com.bjike.goddess.taskallotment.service.ProjectSer;
import com.bjike.goddess.taskallotment.service.TableSer;
import com.bjike.goddess.taskallotment.service.TaskNodeSer;
import com.bjike.goddess.taskallotment.to.GuidePermissionTO;
import com.bjike.goddess.taskallotment.to.ProjectTO;
import com.bjike.goddess.taskallotment.to.TableTO;
import com.bjike.goddess.taskallotment.vo.SonPermissionObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 项目列表业务接口实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:55 ]
 * @Description: [ 项目列表业务接口实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("projectApiImpl")
public class ProjectApiImpl implements ProjectAPI {
    @Autowired
    private ProjectSer projectSer;
    @Autowired
    private TableSer tableSer;
    @Autowired
    private CustomTitleSer customTitleSer;
    @Autowired
    private TaskNodeSer taskNodeSer;

    @Override
    public List<ProjectBO> list(ProjectDTO dto) throws SerException {
        return projectSer.list(dto);
    }

    @Override
    public void save(ProjectTO to) throws SerException {
        projectSer.save(to);
    }

    @Override
    public void edit(ProjectTO to) throws SerException {
        projectSer.edit(to);
    }

    @Override
    public void delete(String id) throws SerException {
        projectSer.delete(id);
    }

    @Override
    public ProjectBO findByID(String id) throws SerException {
        return projectSer.findByID(id);
    }

    @Override
    public Long count(ProjectDTO dto) throws SerException {
        return projectSer.count(dto);
    }

    @Override
    public void editTable(TableTO tableTO) throws SerException {
        projectSer.editTable(tableTO);
    }

    @Override
    public Set<String> areas() throws SerException {
        return projectSer.areas();
    }

    @Override
    public Set<String> departs(ProjectDTO dto) throws SerException {
        return projectSer.departs(dto);
    }

    @Override
    public List<ProjectBO> projects(ProjectDTO dto) throws SerException {
        return projectSer.projects(dto);
    }

    @Override
    public TableBO table(String id) throws SerException {
        return projectSer.table(id);
    }

    @Override
    public List<ProjectBO> list1(ProjectDTO dto) throws SerException {
        return projectSer.list1(dto);
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        return projectSer.sonPermission();
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return projectSer.guidePermission(guidePermissionTO);
    }

    @Override
    public List<ProjectBO> projects() throws SerException {
        return projectSer.projects();
    }

    @Override
    public List<TableBO> tables(String projectId) throws SerException {
        return projectSer.tables(projectId);
    }

    @Override
    public Set<String> taskNames(String tableId) throws SerException {
        return projectSer.taskNames(tableId);
    }

    @Override
    public void addTable(TableTO to) throws SerException {
        projectSer.addTable(to);
    }

    @Override
    public byte[] exportProjectExcel(ProjectDTO dto) throws SerException {
        return projectSer.exportProjectExcel(dto);
    }

    @Override
    public void leadProjectExcel(List<ProjectExcel> toList) throws SerException {
        projectSer.leadProjectExcel(toList);
    }

    @Override
    public byte[] exportTableExcel(TableDTO dto) throws SerException {
        return projectSer.exportTableExcel(dto);
    }

    @Override
    public void leadTableExcel(List<TableExcel> toList, String projectId) throws SerException {
        projectSer.leadTableExcel(toList, projectId);
    }

    @Override
    public List<String> areass() throws SerException {
        ProjectDTO dto = new ProjectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        return projectSer.findByCis(dto).stream().map(Project::getArea).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> departs(String area) throws SerException {
        ProjectDTO dto = new ProjectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        dto.getConditions().add(Restrict.eq("area", area));
        return projectSer.findByCis(dto).stream().map(Project::getDepart).distinct().collect(Collectors.toList());
    }

    @Override
    public List<ProjectBO> projectByAreaAndGroup(ProjectNameDTO projectNameDTO) throws SerException {
        return projectSer.projectByAreaAndGroup(projectNameDTO);
    }

    @Override
    public List<String> tableNamesBypname(ProjectNameDTO projectNameDTO) throws SerException {
        return projectSer.tableNamesBypname(projectNameDTO);
    }

    @Override
    public List<String> makeProjects(String area, String depart) throws SerException {
        ProjectDTO dto = new ProjectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("depart", depart));
        return projectSer.findByCis(dto).stream().map(Project::getMakeProject).distinct().collect(Collectors.toList());
    }

    @Override
    public List<ProjectBO> projects(String area, String depart, String makeProject) throws SerException {
        ProjectDTO dto = new ProjectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        dto.getConditions().add(Restrict.eq("area", area));
        dto.getConditions().add(Restrict.eq("depart", depart));
        dto.getConditions().add(Restrict.eq("makeProject", makeProject));
        return BeanTransform.copyProperties(projectSer.findByCis(dto), ProjectBO.class);
    }

    @Override
    public List<ProjectBO> starts() throws SerException {
        ProjectDTO dto = new ProjectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        return BeanTransform.copyProperties(projectSer.findByCis(dto), ProjectBO.class);
    }

    @Override
    public List<TableBO> tableByProjectId(String projectId) throws SerException {
        TableDTO tableDTO=new TableDTO();
        tableDTO.getConditions().add(Restrict.eq("projectId",projectId));
        tableDTO.getConditions().add(Restrict.eq("status", Status.START));
        return BeanTransform.copyProperties(tableSer.findByCis(tableDTO), TableBO.class);
    }

    @Override
    public List<String> fileds(String[] tablesId) throws SerException {
        Set<String> set=new HashSet<>();
        TaskNodeDTO taskNodeDTO=new TaskNodeDTO();
        taskNodeDTO.getConditions().add(Restrict.in("tableId",tablesId));
        List<TaskNode> taskNodes=taskNodeSer.findByCis(taskNodeDTO);
        Set<String> nodeIds=taskNodes.stream().map(TaskNode::getId).collect(Collectors.toSet());
        if (!nodeIds.isEmpty()){
            CustomTitleDTO customTitleDTO=new CustomTitleDTO();
            customTitleDTO.getConditions().add(Restrict.in("taskNodeId",nodeIds));
            List<CustomTitle> customTitles=customTitleSer.findByCis(customTitleDTO);
            Set<String> titles=customTitles.stream().map(CustomTitle::getTitle).collect(Collectors.toSet());
            set.addAll(titles);
        }
        return new ArrayList<>(set);
    }
}