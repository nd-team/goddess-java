package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.excel.Excel;
import com.bjike.goddess.common.utils.excel.ExcelUtil;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.*;
import com.bjike.goddess.taskallotment.entity.*;
import com.bjike.goddess.taskallotment.enums.GuideAddrStatus;
import com.bjike.goddess.taskallotment.enums.Status;
import com.bjike.goddess.taskallotment.excel.ProjectExcel;
import com.bjike.goddess.taskallotment.excel.TableExcel;
import com.bjike.goddess.taskallotment.to.GuidePermissionTO;
import com.bjike.goddess.taskallotment.to.ProjectTO;
import com.bjike.goddess.taskallotment.to.TableTO;
import com.bjike.goddess.taskallotment.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 项目列表业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-09-14 11:55 ]
 * @Description: [ 项目列表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "taskallotmentSerCache")
@Service
public class ProjectSerImpl extends ServiceImpl<Project, ProjectDTO> implements ProjectSer {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private TableSer tableSer;
    @Autowired
    private CusPermissionSer cusPermissionSer;
    @Autowired
    private TaskNodeSer taskNodeSer;
    @Autowired
    private QuestionSer questionSer;
    @Autowired
    private CustomTitleSer customTitleSer;

    /**
     * 核对查看权限（部门级别）
     */
    private void checkSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以查看");
            }
        }
        RpcTransmit.transmitUserToken(userToken);

    }

    /**
     * 核对添加修改删除审核权限（岗位级别）
     */
    private void checkAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
            if (!flag) {
                throw new SerException("您不是相应部门的人员，不可以操作");
            }
        }
        RpcTransmit.transmitUserToken(userToken);
    }

    /**
     * 导航栏核对查看权限（部门级别）
     */
    private Boolean guideSeeIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.getCusPermission("1");
        } else {
            flag = true;
        }
        return flag;
    }

    /**
     * 导航栏核对添加修改删除审核权限（岗位级别）
     */
    private Boolean guideAddIdentity() throws SerException {
        Boolean flag = false;
        String userToken = RpcTransmit.getUserToken();
        UserBO userBO = userAPI.currentUser();
        RpcTransmit.transmitUserToken(userToken);
        String userName = userBO.getUsername();
        if (!"admin".equals(userName.toLowerCase())) {
            flag = cusPermissionSer.busCusPermission("2");
        } else {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<SonPermissionObject> sonPermission() throws SerException {
        List<SonPermissionObject> list = new ArrayList<>();
        String userToken = RpcTransmit.getUserToken();
        Boolean flagSeeSign = guideSeeIdentity();
        RpcTransmit.transmitUserToken(userToken);
        Boolean flagAddSign = guideAddIdentity();

        SonPermissionObject obj = new SonPermissionObject();

        obj = new SonPermissionObject();
        obj.setName("project");
        obj.setDescribesion("项目列表");
        if (flagSeeSign || flagAddSign) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        RpcTransmit.transmitUserToken(userToken);
        Boolean flagSeeDis = taskNodeSer.sonPermission();
        RpcTransmit.transmitUserToken(userToken);
        obj = new SonPermissionObject();
        obj.setName("tasknode");
        obj.setDescribesion("我执行的任务");
        if (flagSeeDis) {
            obj.setFlag(true);
        } else {
            obj.setFlag(false);
        }
        list.add(obj);

        return list;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        String userToken = RpcTransmit.getUserToken();
        GuideAddrStatus guideAddrStatus = guidePermissionTO.getGuideAddrStatus();
        Boolean flag = true;
        switch (guideAddrStatus) {
            case LIST:
                flag = guideSeeIdentity();
                break;
            case ADD:
                flag = guideAddIdentity();
                break;
            case EDIT:
                flag = guideAddIdentity();
                break;
            case DELETE:
                flag = guideAddIdentity();
                break;
            case START:
                flag = guideAddIdentity();
                break;
            case END:
                flag = guideAddIdentity();
                break;
            default:
                flag = true;
                break;
        }
        return flag;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void save(ProjectTO to) throws SerException {
        String token = RpcTransmit.getUserToken();
        checkAddIdentity();
        RpcTransmit.transmitUserToken(token);
        String name = userAPI.currentUser().getUsername();
        Project entity = BeanTransform.copyProperties(to, Project.class, true);
        entity.setName(name);
        List<Table> tables = new ArrayList<>();
        List<TableTO> tableTOSs = to.getTables();
        super.save(entity);
        if (null != tableTOSs) {
            for (TableTO tableTO : tableTOSs) {
                Table table = BeanTransform.copyProperties(tableTO, Table.class, true);
                table.setProjectId(entity.getId());
                table.setCreater(name);
                tables.add(table);
            }
            tableSer.save(tables);
        }
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProjectTO to) throws SerException {
        checkAddIdentity();
        Project entity = super.findById(to.getId());
        String name = entity.getName();
        LocalDateTime a = entity.getCreateTime();
        entity = BeanTransform.copyProperties(to, Project.class, true);
        entity.setCreateTime(a);
        entity.setName(name);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
//        TableDTO tableDTO = new TableDTO();
//        tableDTO.getConditions().add(Restrict.eq("projectId", entity.getId()));
//        List<Table> tableList = tableSer.findByCis(tableDTO);
//        if (!tableList.isEmpty()) {
//            tableSer.remove(tableList);
//        }
//        List<TableTO> tableTOS = to.getTables();
//        List<Table> tables = BeanTransform.copyProperties(tableTOS, Table.class, true);
//        for (Table table:tables){
//            table.setProjectId(entity.getId());
//            tableSer.save(table);
//        }
    }

    @Override
    public List<ProjectBO> list(ProjectDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<Project> list = super.findByCis(dto, true);
        List<ProjectBO> bos = new ArrayList<>();
        for (Project p : list) {
            TableDTO tableDTO = new TableDTO();
            tableDTO.getConditions().add(Restrict.eq("projectId", p.getId()));
            List<Table> tableList = tableSer.findByCis(tableDTO);
            List<TableBO> tableBOS = BeanTransform.copyProperties(tableList, TableBO.class);
            ProjectBO bo = BeanTransform.copyProperties(p, ProjectBO.class);
            bo.setTables(tableBOS);
            bos.add(bo);
        }
        return bos;
    }

    @Override
    public List<ProjectBO> list1(ProjectDTO dto) throws SerException {
        dto.getSorts().add("createTime=desc");
        List<Project> list = super.findByCis(dto, true);
        List<ProjectBO> bos = new ArrayList<>();
        for (Project p : list) {
            TableDTO tableDTO = new TableDTO();
            tableDTO.getConditions().add(Restrict.eq("projectId", p.getId()));
            List<Table> tableList = tableSer.findByCis(tableDTO);
            List<TableBO> tableBOS = BeanTransform.copyProperties(tableList, TableBO.class);
            ProjectBO bo = BeanTransform.copyProperties(p, ProjectBO.class);
            bo.setTables(tableBOS);
            bos.add(bo);
        }
        return bos;
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        checkAddIdentity();
        Project entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        TableDTO tableDTO = new TableDTO();
        tableDTO.getConditions().add(Restrict.eq("projectId", entity.getId()));
        List<Table> tableList = tableSer.findByCis(tableDTO);
        for (Table table : tableList) {
            TaskNodeDTO taskNodeDTO = new TaskNodeDTO();
            taskNodeDTO.getConditions().add(Restrict.eq("tableId", table.getId()));
            List<TaskNode> taskNodes = taskNodeSer.findByCis(taskNodeDTO);
            for (TaskNode taskNode : taskNodes) {
                QuestionDTO questionDTO = new QuestionDTO();
                questionDTO.getConditions().add(Restrict.eq("taskNodeId", taskNode.getId()));
                List<Question> questions = questionSer.findByCis(questionDTO);
                questionSer.remove(questions);
                CustomTitleDTO customTitleDTO = new CustomTitleDTO();
                customTitleDTO.getConditions().add(Restrict.eq("taskNodeId", taskNode.getId()));
                List<CustomTitle> customTitles = customTitleSer.findByCis(customTitleDTO);
                customTitleSer.remove(customTitles);
            }
            taskNodeSer.remove(taskNodes);
        }
        tableSer.remove(tableList);
        super.remove(id);
    }

    @Override
    public ProjectBO findByID(String id) throws SerException {
        Project entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        TableDTO tableDTO = new TableDTO();
        tableDTO.getConditions().add(Restrict.eq("projectId", entity.getId()));
        List<Table> tableList = tableSer.findByCis(tableDTO);
        List<TableBO> tableBOS = BeanTransform.copyProperties(tableList, TableBO.class);
        ProjectBO bo = BeanTransform.copyProperties(entity, ProjectBO.class);
        bo.setTables(tableBOS);
        return bo;
    }

    @Override
    public Long count(ProjectDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public void editTable(TableTO tableTO) throws SerException {
        checkAddIdentity();
        Table entity = tableSer.findById(tableTO.getId());
        if (null == entity) {
            throw new SerException("该对象不存在");
        }
        entity.setName(tableTO.getName());
        entity.setStatus(tableTO.getStatus());
        entity.setModifyTime(LocalDateTime.now());
        tableSer.update(entity);
    }

    @Override
    public Set<String> areas() throws SerException {
        ProjectDTO dto = new ProjectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        List<Project> list = super.findByCis(dto);
        return list.stream().map(project -> project.getArea()).collect(Collectors.toSet());
    }

    @Override
    public Set<String> departs(ProjectDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("status", Status.START));
        dto.getConditions().add(Restrict.in("area", dto.getAreas()));
        List<Project> list = super.findByCis(dto);
        return list.stream().map(project -> project.getDepart()).collect(Collectors.toSet());
    }

    @Override
    public List<ProjectBO> projects(ProjectDTO dto) throws SerException {
        dto.getConditions().add(Restrict.eq("status", Status.START));
        dto.getConditions().add(Restrict.in("depart", dto.getDeparts()));
        List<Project> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ProjectBO.class, "tables");
    }

    @Override
    public TableBO table(String id) throws SerException {
        Table table = tableSer.findById(id);
        if (null == table) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(table, TableBO.class);
    }

    @Override
    public List<ProjectBO> projects() throws SerException {
        ProjectDTO dto = new ProjectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        List<Project> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, ProjectBO.class, "tables");
    }

    @Override
    public List<TableBO> tables(String projectId) throws SerException {
        TableDTO dto = new TableDTO();
        dto.getConditions().add(Restrict.eq("projectId", projectId));
        dto.getConditions().add(Restrict.eq("status", Status.START));
        List<Table> list = tableSer.findByCis(dto);
        return BeanTransform.copyProperties(list, TableBO.class);
    }

    @Override
    public List<String> projectByAreaAndGroup(ProjectNameDTO projectNameDTO) throws SerException {
        ProjectDTO dto = new ProjectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
//        dto.getConditions().add(Restrict.in("area", projectNameDTO.getAreas()));
        dto.getConditions().add(Restrict.in("depart", projectNameDTO.getDeparts()));
        List<Project> list = super.findByCis(dto);
        List<String> projectNames = new ArrayList<>();
        projectNames.addAll(list.stream().map(Project::getProject).collect(Collectors.toList()));
        return projectNames;
    }

    @Override
    public List<String> tableNamesBypname(ProjectNameDTO projectNameDTO) throws SerException {
        ProjectDTO dto = new ProjectDTO();
        dto.getConditions().add(Restrict.eq("status", Status.START));
        dto.getConditions().add(Restrict.in("project", projectNameDTO.getProjects()));
        List<Project> list = super.findByCis(dto);
        List<String> projectIds = new ArrayList<>();
        projectIds.addAll( list.stream().map(Project::getId).collect(Collectors.toList()) );

        TableDTO tdto = new TableDTO();
        tdto.getConditions().add(Restrict.in("projectId", projectIds));
        tdto.getConditions().add(Restrict.eq("status", Status.START));
        List<Table> tableList = tableSer.findByCis(tdto);
        List<String> tableNames = new ArrayList<>();
        tableNames = tableList.stream().map(Table::getName).distinct().collect(Collectors.toList());
        return tableNames;
    }

    @Override
    public Set<String> taskNames(String tableId) throws SerException {
        TaskNodeDTO dto = new TaskNodeDTO();
        dto.getConditions().add(Restrict.eq("tableId", tableId));
        return taskNodeSer.findByCis(dto).stream().map(TaskNode::getTaskName).collect(Collectors.toSet());
    }

    @Override
    public void addTable(TableTO to) throws SerException {
        Project project = super.findById(to.getProjectId());
        if (null == project) {
            throw new SerException("该对象不存在");
        }
        Table table = new Table();
        table.setProjectId(project.getId());
        table.setName(to.getName());
        table.setCreater(userAPI.currentUser().getUsername());
        table.setStatus(to.getStatus());
        tableSer.save(table);
    }

    @Override
    public byte[] exportProjectExcel(ProjectDTO dto) throws SerException {
        dto.getConditions().add(Restrict.in("project",dto.getProjects()));
        List<Project> list=super.findByCis(dto);
        List<ProjectExcel> toList=BeanTransform.copyProperties(list,ProjectExcel.class);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void leadProjectExcel(List<ProjectExcel> toList) throws SerException {
        List<Project> list=BeanTransform.copyProperties(toList,Project.class,true);
        super.save(list);
    }

    @Override
    public byte[] exportTableExcel(TableDTO dto) throws SerException {
        dto.getConditions().add(Restrict.in("name",dto.getTables()));
        List<Table> list=tableSer.findByCis(dto);
        List<TableExcel> toList=BeanTransform.copyProperties(list,TableExcel.class);
        Excel excel = new Excel(0, 2);
        byte[] bytes = ExcelUtil.clazzToExcel(toList, excel);
        return bytes;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void leadTableExcel(List<TableExcel> toList,String projectId) throws SerException {
        List<Table> list=BeanTransform.copyProperties(toList,Table.class,true);
        for (Table table:list){
            table.setProjectId(projectId);
        }
        tableSer.save(list);
    }


}