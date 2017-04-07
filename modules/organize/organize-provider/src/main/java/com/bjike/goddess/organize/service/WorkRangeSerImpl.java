package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.DepartmentWorkRangeBO;
import com.bjike.goddess.organize.bo.WorkRangeBO;
import com.bjike.goddess.organize.dto.WorkRangeDTO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.entity.WorkRange;
import com.bjike.goddess.organize.to.DepartmentWorkRangeTO;
import com.bjike.goddess.organize.to.WorkRangeTO;
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
    public List<DepartmentWorkRangeBO> findDepartmentWorkRangeView(String department_id, WorkRangeDTO dto) throws SerException {
        String[] fields = {"id", "createTime", "classify", "direction", "node", "project", "status", "workRange"};
        StringBuilder sql = new StringBuilder("SELECT wr.id,wr.createTime,wr.classify,wr.direction,wr.node,wr.project,wr.project,wr.status,wr.workRange FROM ");
        sql.append(" organize_work_range AS wr ");
        sql.append(" LEFT JOIN ").append(" (SELECT range_id FROM organize_work_range_department WHERE department_id = '");
        sql.append(department_id).append("' GROUP BY range_id) AS de ");
        sql.append(" ON wr.id = de.range_id ");
        sql.append("LIMIT ").append(dto.getPage() * dto.getLimit()).append(",").append(dto.getPage());
        List<DepartmentWorkRangeBO> list = super.findBySql(sql.toString(), DepartmentWorkRangeBO.class, fields);
        DepartmentDetailBO department = departmentDetailSer.findBOById(department_id);
        for (DepartmentWorkRangeBO bo : list) {
            bo.setDepartmentId(department_id);
            bo.setDepartmentName(department.getDepartment());
            bo.setHierarchy(department.getHierarchyName());
            bo.setSerialNumber(department.getShowNumber());
            bo.setHierarchyNumber(department.getHierarchyNumber());
        }
        return list;
    }

    @Override
    public List<WorkRangeBO> findByDepartment(String departmentId) throws SerException {
        String[] fields = {"id", "createTime", "classify", "direction", "node", "project", "status", "workRange"};
        StringBuilder sql = new StringBuilder("SELECT wr.id,wr.createTime,wr.classify,wr.direction,wr.node,wr.project,wr.project,wr.status,wr.workRange FROM ");
        sql.append(" organize_work_range AS wr ");
        sql.append(" LEFT JOIN ").append(" (SELECT range_id FROM organize_work_range_department WHERE department_id = '");
        sql.append(departmentId).append("' GROUP BY range_id) AS de ");
        sql.append(" ON wr.id = de.range_id ");
        List<WorkRangeBO> list = super.findBySql(sql.toString(), WorkRangeBO.class, fields);
        return list;
    }

    @Override
    public List<DepartmentDetailBO> findByRange(String rangeId) throws SerException {
        WorkRange range = super.findById(rangeId);
        return departmentDetailSer.findByDepartmentIds(range.getDepartments().stream().map(DepartmentDetail::getId).collect(Collectors.toList()));
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void departmentAddRange(DepartmentWorkRangeTO to) throws SerException {
        DepartmentDetail departmentDetail = departmentDetailSer.findById(to.getDepartment_id());
        List<WorkRange> updateList = new ArrayList<>(0);
        if (null != to.getRange_ids()) ;
        for (String id : to.getRange_ids()) {
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
        return BeanTransform.copyProperties(entity, WorkRangeBO.class, true);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public WorkRangeBO update(WorkRangeTO to) throws SerException {
        WorkRange entity = super.findById(to.getId()), workRange = BeanTransform.copyProperties(to, WorkRange.class);
        workRange.setCreateTime(entity.getCreateTime());
        workRange.setStatus(Status.THAW);
        super.update(workRange);
        return BeanTransform.copyProperties(workRange, WorkRangeBO.class, true);
    }
}
