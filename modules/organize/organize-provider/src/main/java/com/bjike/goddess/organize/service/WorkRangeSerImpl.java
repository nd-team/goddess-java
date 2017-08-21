package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.*;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.entity.WorkRange;
import com.bjike.goddess.organize.to.DepartmentWorkRangeTO;
import com.bjike.goddess.organize.to.WorkRangeTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
}
