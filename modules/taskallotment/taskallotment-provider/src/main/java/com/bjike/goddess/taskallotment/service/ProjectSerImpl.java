package com.bjike.goddess.taskallotment.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.taskallotment.bo.ProjectBO;
import com.bjike.goddess.taskallotment.bo.TableBO;
import com.bjike.goddess.taskallotment.dto.ProjectDTO;
import com.bjike.goddess.taskallotment.entity.Project;
import com.bjike.goddess.taskallotment.entity.Table;
import com.bjike.goddess.taskallotment.enums.Status;
import com.bjike.goddess.taskallotment.to.ProjectTO;
import com.bjike.goddess.taskallotment.to.TableTO;
import com.bjike.goddess.user.api.UserAPI;
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

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void save(ProjectTO to) throws SerException {
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
        return BeanTransform.copyProperties(entity, ProjectBO.class);
    }

    @Override
    public Long count(ProjectDTO dto) throws SerException {
        return super.count(dto);
    }

    @Override
    public void editTable(TableTO tableTO) throws SerException {
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
        return BeanTransform.copyProperties(list,ProjectBO.class);
    }
}