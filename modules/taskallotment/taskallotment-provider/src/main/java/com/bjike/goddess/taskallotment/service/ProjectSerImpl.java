package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.provider.utils.RpcTransmit;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.dto.TableDTO;
import com.bjike.goddess.taskallotment.dto.TaskNodeDTO;
import com.bjike.goddess.taskallotment.entity.Project;
import com.bjike.goddess.taskallotment.entity.Table;
import com.bjike.goddess.taskallotment.entity.TaskNode;
import com.bjike.goddess.taskallotment.enums.GuideAddrStatus;
import com.bjike.goddess.taskallotment.enums.Status;
import com.bjike.goddess.taskallotment.to.GuidePermissionTO;
import com.bjike.goddess.taskallotment.to.ProjectTO;
import com.bjike.goddess.taskallotment.to.TableTO;
import com.bjike.goddess.taskallotment.vo.SonPermissionObject;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
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
        if (null != tableTOSs) {
            for (TableTO tableTO : tableTOSs) {
                Table table = BeanTransform.copyProperties(tableTO, Table.class, true);
                table.setProject(entity);
                tables.add(table);
            }
        }
        entity.setTables(tables);
        super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(ProjectTO to) throws SerException {
        checkAddIdentity();
        Project entity = super.findById(to.getId());
        String name = entity.getName();
        LocalDateTime a = entity.getCreateTime();
        List<Table> tables = entity.getTables();
        if (null != tables && !tables.isEmpty()) {
            tableSer.remove(tables);
        }
        entity = BeanTransform.copyProperties(to, Project.class, true);
        entity.setCreateTime(a);
        entity.setName(name);
        List<Table> tables1 = new ArrayList<>();
        List<TableTO> tableTOSs = to.getTables();
        if (null != tableTOSs) {
            for (TableTO tableTO : tableTOSs) {
                Table table = BeanTransform.copyProperties(tableTO, Table.class, true);
                table.setProject(entity);
                tableSer.save(table);
                tables1.add(table);
            }
        }
        entity.setTables(tables1);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public List<ProjectBO> list(ProjectDTO dto) throws SerException {
        checkSeeIdentity();
        dto.getSorts().add("createTime=desc");
        List<Project> list = super.findByCis(dto, true);
        List<ProjectBO> bos = new ArrayList<>();
        for (Project p : list) {
            List<TableBO> tableBOS = BeanTransform.copyProperties(p.getTables(), TableBO.class);
            ProjectBO bo = BeanTransform.copyProperties(p, ProjectBO.class, "tables");
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
            List<TableBO> tableBOS = BeanTransform.copyProperties(p.getTables(), TableBO.class);
            ProjectBO bo = BeanTransform.copyProperties(p, ProjectBO.class, "tables");
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
        super.remove(id);
    }

    @Override
    public ProjectBO findByID(String id) throws SerException {
        Project entity = super.findById(id);
        if (entity == null) {
            throw new SerException("该对象不存在");
        }
        return BeanTransform.copyProperties(entity, ProjectBO.class, "tables");
    }

    @Override
    public Long count(ProjectDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public void editTable(TableTO tableTO) throws SerException {
        checkAddIdentity();
        Table entity = tableSer.findById(tableTO.getId());
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
        return BeanTransform.copyProperties(list, ProjectBO.class,"tables");
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
        dto.getConditions().add(Restrict.eq("project.id", projectId));
        dto.getConditions().add(Restrict.eq("status", Status.START));
        List<Table> list = tableSer.findByCis(dto);
        return BeanTransform.copyProperties(list, TableBO.class);
    }

    @Override
    public Set<String> taskNames(String tableId) throws SerException {
        TaskNodeDTO dto = new TaskNodeDTO();
        dto.getConditions().add(Restrict.eq("table.id", tableId));
        return taskNodeSer.findByCis(dto).stream().map(TaskNode::getTaskName).collect(Collectors.toSet());
    }
}