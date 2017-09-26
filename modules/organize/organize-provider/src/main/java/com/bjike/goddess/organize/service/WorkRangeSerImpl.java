package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.dto.WorkRangeFlatsDTO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.entity.WorkRange;
import com.bjike.goddess.organize.entity.WorkRangeFlats;
import com.bjike.goddess.organize.to.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * 工作范围信息设置业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:50]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class WorkRangeSerImpl extends ServiceImpl<WorkRange, WorkRangeDTO> implements WorkRangeSer {

    @Autowired
    private DepartmentDetailSer departmentDetailSer;
    @Autowired
    private WorkRangeFlatsSer workRangeFlatsSer;

    @Override
    public List<DepartmentWorkRangeBO> findDepartmentWorkRangeView(String departmentId, WorkRangeDTO dto) throws SerException {
        if (StringUtils.isBlank(departmentId))
            return new ArrayList<>(0);
        String[] fields = {"id", "createTime", "classify", "direction", "node", "project", "workRange"};
        StringBuilder sql = new StringBuilder("SELECT wr.id,wr.createTime,wr.classify,wr.direction,wr.node,wr.project,wr.workRange FROM ");
        sql.append(" organize_work_range AS wr ");
        sql.append(" LEFT JOIN ").append(" (SELECT range_id FROM organize_work_range_department WHERE department_id = '");
        sql.append(departmentId).append("' GROUP BY range_id) AS de ");
        sql.append(" ON wr.id = de.range_id ");
        sql.append(" LIMIT ").append(dto.getPage() * dto.getLimit()).append(",").append(dto.getLimit());
        List<WorkRange> list = super.findBySql(sql.toString(), WorkRange.class, fields);
        List<DepartmentWorkRangeBO> bos = new ArrayList<>(0);
        DepartmentDetailBO department = departmentDetailSer.findBOById(departmentId);
        for (WorkRange entity : list) {
            DepartmentWorkRangeBO bo = BeanTransform.copyProperties(entity, DepartmentWorkRangeBO.class);
            bo.setDepartmentId(departmentId);
            bo.setDepartmentName(department.getDepartment());
            bo.setHierarchy(department.getHierarchyName());
            bo.setSerialNumber(department.getShowNumber());
            bo.setHierarchyNumber(department.getHierarchyNumber());
            bos.add(bo);
        }
        return bos;
    }

    @Override
    public List<WorkRangeBO> findByDepartment(String departmentId) throws SerException {
        if (StringUtils.isBlank(departmentId))
            return new ArrayList<>(0);
        String[] fields = {"id", "createTime", "classify", "direction", "node", "project", "workRange"};
        StringBuilder sql = new StringBuilder("SELECT wr.id,wr.createTime,wr.classify,wr.direction,wr.node,wr.project,wr.workRange FROM ");
        sql.append(" organize_work_range AS wr ");
        sql.append(" WHERE wr.id IN ").append(" (SELECT range_id FROM organize_work_range_department WHERE department_id = '");
        sql.append(departmentId).append("' GROUP BY range_id)");
        List<WorkRange> list = super.findBySql(sql.toString(), WorkRange.class, fields);
        if (list.size() == 0)
            return new ArrayList<>(0);
        return BeanTransform.copyProperties(list, WorkRangeBO.class);
    }

    @Override
    public List<DepartmentDetailBO> findByRange(String rangeId) throws SerException {
        WorkRange range = super.findById(rangeId);
        return departmentDetailSer.findByDepartmentIds(range.getDepartments().stream().map(DepartmentDetail::getId).collect(Collectors.toList()));
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void departmentAddRange(DepartmentWorkRangeTO to) throws SerException {
        DepartmentDetail departmentDetail = departmentDetailSer.findById(to.getDepartmentId());
        List<WorkRange> updateList = new ArrayList<>(0);
        if (null != to.getRangeIds()) ;
        for (String id : to.getRangeIds()) {
            WorkRange entity = super.findById(id);
            entity.getDepartments().add(departmentDetail);
            updateList.add(entity);
        }
        if (updateList.size() > 0)
            super.update(updateList);
    }

    @Override
    public List<WorkRangeBO> findByDirection(String direction) throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq("direction", direction));
        List<WorkRange> list = super.findByCis(dto, false);
        return BeanTransform.copyProperties(list, WorkRangeBO.class);
    }

    @Override
    public List<WorkRangeBO> findByDirectionProject(String direction, String project) throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq("direction", direction));
        dto.getConditions().add(Restrict.eq("project", project));
        List<WorkRange> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, WorkRangeBO.class);
    }

    @Override
    public List<WorkRangeBO> findByProject(String project) throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq("project", project));
        List<WorkRange> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, WorkRangeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WorkRangeBO save(WorkRangeTO to) throws SerException {
        WorkRange entity = BeanTransform.copyProperties(to, WorkRange.class);
        entity.setStatus(Status.THAW);
        entity.setCreateTime(LocalDateTime.now());
        super.save(entity);
        return BeanTransform.copyProperties(entity, WorkRangeBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WorkRangeBO update(WorkRangeTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        WorkRange entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return BeanTransform.copyProperties(entity, WorkRangeBO.class);
    }

    @Override
    public WorkRangeBO delete(String id) throws SerException {
        WorkRange entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (entity.getDepartments().size() != 0)
            throw new SerException("此处已被引用,无法删除");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, WorkRangeBO.class);
    }

    @Override
    public List<WorkRangeBO> maps(WorkRangeDTO dto) throws SerException {
        dto.getSorts().add("modifyTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), WorkRangeBO.class);
    }

    @Override
    public List<DirectionBO> findDirection() throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getSorts().add("direction=asc");
        List<WorkRange> list = super.findByCis(dto);
        List<DirectionBO> bos = new ArrayList<>(0);
        String direction = "";
        if (null != list)
            for (WorkRange entity : list)
                if (!entity.getDirection().equals(direction)) {
                    direction = entity.getDirection();
                    DirectionBO bo = new DirectionBO();
                    bo.setDirection(direction);
                    bos.add(bo);
                }
        return bos;
    }

    @Override
    public List<ProjectBO> findProject() throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getSorts().add("project=asc");
        List<WorkRange> list = super.findByCis(dto);
        List<ProjectBO> bos = new ArrayList<>(0);
        String project = "";
        if (null != list)
            for (WorkRange entity : list)
                if (!entity.getProject().equals(project)) {
                    project = entity.getProject();
                    ProjectBO bo = new ProjectBO();
                    bo.setProject(project);
                    bos.add(bo);
                }
        return bos;
    }

    @Override
    public List<ClassifyBO> findClassify() throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getSorts().add("classify=asc");
        List<WorkRange> list = super.findByCis(dto);
        List<ClassifyBO> bos = new ArrayList<>(0);
        String classify = "";
        if (null != list)
            for (WorkRange entity : list)
                if (!entity.getClassify().equals(classify)) {
                    classify = entity.getClassify();
                    ClassifyBO bo = new ClassifyBO();
                    bo.setClassify(classify);
                    bos.add(bo);
                }
        return bos;
    }

    @Override
    public WorkRangeBO close(String id) throws SerException {
        WorkRange entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        entity.setStatus(Status.CONGEAL);
        super.save(entity);
        return BeanTransform.copyProperties(entity, WorkRangeBO.class);
    }

    @Override
    public WorkRangeBO open(String id) throws SerException {
        WorkRange entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据对象不存在");
        entity.setStatus(Status.THAW);
        super.save(entity);
        return BeanTransform.copyProperties(entity, WorkRangeBO.class);
    }

    @Override
    public List<OpinionBO> findThawOpinion() throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<WorkRange> list = super.findByCis(dto);
        List<OpinionBO> bos = new ArrayList<>(0);
        for (WorkRange entity : list)
            bos.add(new OpinionBO(entity.getId(), String.format("方向:%s 科目:%s 专业分类:%s 工作范围:%s", entity.getDirection(), entity.getProject(), entity.getClassify(), entity.getWorkRange())));
        return bos;
    }

    @Override
    public List<WorkRangeBO> findByStatus(Status status) throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, status));
        List<WorkRange> list = super.findByCis(dto);
        return BeanTransform.copyProperties(list, WorkRangeBO.class);
    }

    @Override
    public List<String> findWorkScope() throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<WorkRange> workRanges = super.findByCis(dto);
        List<String> list = new ArrayList<>(0);
        if (!CollectionUtils.isEmpty(workRanges)) {
            list = workRanges.stream().map(WorkRange::getWorkRange).distinct().collect(Collectors.toList());
        }
        return list;
    }

    @Transactional
    @Override
    public List<WorkRangeFlatBO> getFlatList(WorkRangeDTO dto) throws SerException {
        List<WorkRange> workRanges = super.findAll();
        List<String> directions = new ArrayList<>(0);
        List<String> projects = new ArrayList<>(0);
        List<String> classifys = new ArrayList<>(0);
        List<WorkRangeFlatBO> workRangeFlatBOList = new ArrayList<>(0);
        if (null != workRanges && workRanges.size() > 0) {
            directions = workRanges.stream().map(WorkRange::getDirection).distinct().collect(Collectors.toList());
            for (String str : directions) {
                WorkRangeFlatBO workRangeFlatBO = new WorkRangeFlatBO();
                workRangeFlatBO.setDirection(str);
                workRangeFlatBO.setId(UUID.randomUUID().toString());
                WorkRangeFlats workRangeFlats = new WorkRangeFlats();
                workRangeFlats.setId(workRangeFlatBO.getId());

                projects = workRanges.stream().filter(obj -> obj.getDirection().equals(str)).map(WorkRange::getProject).distinct().collect(Collectors.toList());
                List<ProjectFlatBO> projectFlatBOList = new ArrayList<>(0);
                for (String project : projects) {
                    ProjectFlatBO projectFlatBO = new ProjectFlatBO();
                    projectFlatBO.setProject(project);
                    classifys = workRanges.stream().filter(obj -> obj.getProject().equals(project)).map(WorkRange::getClassify).distinct().collect(Collectors.toList());
                    List<ClassifyFlatBO> classifyFlatBOList = new ArrayList<>(0);
                    for (String classify : classifys) {
                        ClassifyFlatBO classifyFlatBO = new ClassifyFlatBO();
                        classifyFlatBO.setClassify(classify);
                        List<WorkRangeListBO> workRangeListBOList = new ArrayList<>(0);
                        for (WorkRange workRange : workRanges) {
                            if (workRange.getDirection().equals(str) && workRange.getProject().equals(project) && workRange.getClassify().equals(classify)) {
                                WorkRangeListBO workRangeListBO = new WorkRangeListBO();
                                workRangeListBO.setWorkRanges(workRange.getWorkRange().split(","));
                                workRangeListBO.setNode(workRange.getNode().split(","));
                                workRangeListBO.setCreateTime(DateUtil.dateToString(workRange.getCreateTime()));
                                workRangeListBO.setStatus(workRange.getStatus());
                                workRangeListBOList.add(workRangeListBO);
                            }
                        }
                        classifyFlatBO.setWorkRangeListBOs(workRangeListBOList);
                        classifyFlatBOList.add(classifyFlatBO);
                    }
                    projectFlatBO.setClassifyFlatBOs(classifyFlatBOList);
                    projectFlatBOList.add(projectFlatBO);
                }
                workRangeFlatBO.setProjectFlatBOs(projectFlatBOList);
                workRangeFlatBOList.add(workRangeFlatBO);
            }
        }
        if (null != workRangeFlatBOList && workRangeFlatBOList.size() > 0) {
            List<String> ids = new ArrayList<>(0);
            ids = workRangeFlatBOList.stream().map(WorkRangeFlatBO::getId).distinct().collect(Collectors.toList());
            List<WorkRangeFlats> workRangeFlatses = new ArrayList<>(0);
            for (String id : ids) {
                WorkRangeFlats workRangeFlats = new WorkRangeFlats();
                workRangeFlats.setFaltId(id);
                workRangeFlatses.add(workRangeFlats);
            }
            List<WorkRangeFlats> workRangeFlatsList = workRangeFlatsSer.findAll();
            if (null != workRangeFlatsList && workRangeFlatsList.size() > 0) {
                workRangeFlatsSer.remove(workRangeFlatsList);
            }
            workRangeFlatsSer.save(workRangeFlatses);

            WorkRangeFlatsDTO workRangeFlatsDTO = BeanTransform.copyProperties(dto, WorkRangeFlatsDTO.class, "serialVersionUID");
            List<WorkRangeFlats> workRangeFlatsList1 = workRangeFlatsSer.findByPage(workRangeFlatsDTO);
            List<String> flatIds = new ArrayList<>(0);
            flatIds = workRangeFlatsList1.stream().map(WorkRangeFlats::getFaltId).collect(Collectors.toList());
            List<WorkRangeFlatBO> returnBO = new ArrayList<>(0);
            for (String flatID : flatIds) {
                for (WorkRangeFlatBO workRangeFlatBO : workRangeFlatBOList) {
                    if (workRangeFlatBO.getId().equals(flatID)) {
                        returnBO.add(workRangeFlatBO);
                    }
                }
            }
            return returnBO;
        }
        return null;
    }

    @Transactional
    @Override
    public void flatAdd(WorkRangeFlatTO to) throws SerException {
        for (ProjectFlatTO projectFlatTO : to.getProjectFlatTOs()) {
            for (ClassifyFlatTO classifyFlatTO : projectFlatTO.getClassifyFlatTOs()) {
                String wr = "";
                String node = "";
                for (WorkRangeListTO workRangeListTO : classifyFlatTO.getWorkRangeListTOs()) {
                    if (StringUtils.isNotBlank(workRangeListTO.getWorkRange())) {
                        wr = wr + workRangeListTO.getWorkRange() + ",";
                    }
                    if (StringUtils.isNotBlank(workRangeListTO.getNode())) {
                        node = node + workRangeListTO.getNode() + ",";
                    }
                }
                if (StringUtils.isNotBlank(wr)) {
                    wr = wr.substring(0, wr.lastIndexOf(","));
                }
                if (StringUtils.isNotBlank(node)) {
                    node = node.substring(0, node.lastIndexOf(","));
                }
                WorkRange workRange = new WorkRange();
                workRange.setWorkRange(wr);
                workRange.setNode(node);
                workRange.setClassify(classifyFlatTO.getClassify());
                workRange.setProject(projectFlatTO.getProject());
                workRange.setDirection(to.getDirection());
                workRange.setCreateTime(LocalDateTime.now());
                workRange.setStatus(Status.THAW);
                super.save(workRange);
            }
        }
    }

    @Override
    public List<WorkRangeFlatBO> findByFlatDirection(String direction) throws SerException {
        if (StringUtils.isBlank(direction)) {
            throw new SerException("业务方向分类不能为空");
        }
        WorkRangeDTO workRangeDTO = new WorkRangeDTO();
        workRangeDTO.getConditions().add(Restrict.eq("direction", direction));
        List<WorkRange> workRanges = super.findByCis(workRangeDTO);

        List<String> directions = new ArrayList<>(0);
        List<String> projects = new ArrayList<>(0);
        List<String> classifys = new ArrayList<>(0);
        List<WorkRangeFlatBO> workRangeFlatBOList = new ArrayList<>(0);
        if (null != workRanges && workRanges.size() > 0) {
            directions = workRanges.stream().map(WorkRange::getDirection).distinct().collect(Collectors.toList());
            for (String str : directions) {
                WorkRangeFlatBO workRangeFlatBO = new WorkRangeFlatBO();
                workRangeFlatBO.setDirection(str);
                workRangeFlatBO.setId(UUID.randomUUID().toString());
                WorkRangeFlats workRangeFlats = new WorkRangeFlats();
                workRangeFlats.setId(workRangeFlatBO.getId());

                projects = workRanges.stream().filter(obj -> obj.getDirection().equals(str)).map(WorkRange::getProject).distinct().collect(Collectors.toList());
                List<ProjectFlatBO> projectFlatBOList = new ArrayList<>(0);
                for (String project : projects) {
                    ProjectFlatBO projectFlatBO = new ProjectFlatBO();
                    projectFlatBO.setProject(project);
                    classifys = workRanges.stream().filter(obj -> obj.getProject().equals(project)).map(WorkRange::getClassify).distinct().collect(Collectors.toList());
                    List<ClassifyFlatBO> classifyFlatBOList = new ArrayList<>(0);
                    for (String classify : classifys) {
                        ClassifyFlatBO classifyFlatBO = new ClassifyFlatBO();
                        classifyFlatBO.setClassify(classify);
                        List<WorkRangeListBO> workRangeListBOList = new ArrayList<>(0);
                        for (WorkRange workRange : workRanges) {
                            if (workRange.getDirection().equals(str) && workRange.getProject().equals(project) && workRange.getClassify().equals(classify)) {
                                WorkRangeListBO workRangeListBO = new WorkRangeListBO();
                                workRangeListBO.setWorkRanges(workRange.getWorkRange().split(","));
                                workRangeListBO.setNode(workRange.getNode().split(","));
                                workRangeListBO.setCreateTime(DateUtil.dateToString(workRange.getCreateTime()));
                                workRangeListBO.setStatus(workRange.getStatus());
                                workRangeListBOList.add(workRangeListBO);
                            }
                        }
                        classifyFlatBO.setWorkRangeListBOs(workRangeListBOList);
                        classifyFlatBOList.add(classifyFlatBO);
                    }
                    projectFlatBO.setClassifyFlatBOs(classifyFlatBOList);
                    projectFlatBOList.add(projectFlatBO);
                }
                workRangeFlatBO.setProjectFlatBOs(projectFlatBOList);
                workRangeFlatBOList.add(workRangeFlatBO);
            }
        }
        return workRangeFlatBOList;
    }

    @Transactional
    @Override
    public void faltDelete(String direction) throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq("direction", direction));
        super.remove(super.findByCis(dto));
    }

    @Transactional
    @Override
    public void flatUpdate(WorkRangeFlatTO to) throws SerException {
        if (StringUtils.isBlank(to.getDirectionEdit())) {
            throw new SerException("修改前的业务方向分类不能为空");
        }
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq("direction", to.getDirectionEdit()));
        super.remove(super.findByCis(dto));
        flatAdd(to);
    }

    @Transactional
    @Override
    public void flatClose(String direction) throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq("direction", direction));
        List<WorkRange> workRanges = super.findByCis(dto);

        if (null != workRanges && workRanges.size() > 0) {
            for (WorkRange workRange : workRanges) {
                workRange.setStatus(Status.CONGEAL);
                super.save(workRange);
            }
        }
    }

    @Transactional
    @Override
    public void flatOpen(String direction) throws SerException {
        WorkRangeDTO dto = new WorkRangeDTO();
        dto.getConditions().add(Restrict.eq("direction", direction));
        List<WorkRange> workRanges = super.findByCis(dto);
        if (null != workRanges && workRanges.size() > 0) {
            for (WorkRange workRange : workRanges) {
                workRange.setStatus(Status.THAW);
                super.save(workRange);
            }
        }
    }

    @Override
    public Long getFlatTotal() throws SerException {
        WorkRangeFlatsDTO dto = new WorkRangeFlatsDTO();
        return workRangeFlatsSer.count(dto);
    }

}
