package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.api.HierarchyAPI;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.to.DepartmentDetailTO;
import com.bjike.goddess.user.api.DepartmentAPI;
import com.bjike.goddess.user.dto.DepartmentDTO;
import com.bjike.goddess.user.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门详细业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午9:54]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class DepartmentDetailSerImpl extends ServiceImpl<DepartmentDetail, DepartmentDetailDTO> implements DepartmentDetailSer {

    @Autowired
    private HierarchyAPI hierarchyAPI;

    @Autowired
    private HierarchySer hierarchySer;

    @Autowired
    private DepartmentAPI departmentAPI;

    private DepartmentDetailBO transformationToBO(DepartmentDetail entity) throws SerException {
        DepartmentDetailBO bo = BeanTransform.copyProperties(entity, DepartmentDetailBO.class, true);
        bo.setDepartment_id(entity.getDepartment_id());
        bo.setHierarchy_id(entity.getHierarchy().getId());
//        DepartmentBO department = departmentAPI.findById(bo.getDepartment_id());
        bo.setHierarchyName(entity.getHierarchy().getHierarchy());
        bo.setHierarchyNumber(entity.getHierarchy().getSerialNumber());
//        bo.setDepartment(department.getName());
        bo.setShowNumber(String.format("%s-%s", entity.getHierarchy().getSerialNumber(), entity.getSerialNumber()));
        return bo;
    }

    private List<DepartmentDetailBO> transformationToBOList(List<DepartmentDetail> list) throws SerException {
        List<DepartmentDetailBO> bos = new ArrayList<>(list.size());
        for (DepartmentDetail detail : list)
            bos.add(this.transformationToBO(detail));
        return bos;
    }

    @Override
    public List<DepartmentDetailBO> view(DepartmentDetailDTO dto) throws SerException {
        return this.transformationToBOList(super.findByPage(dto));
    }


    @Override
    public List<DepartmentDetailBO> findByHierarchy(String hierarchy_id) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.eq("hierarchy.id", hierarchy_id));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public List<DepartmentDetailBO> findStatus() throws SerException {
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<Department> list = new ArrayList<>(0);//@TODO 等待部门接口修改
        if (list.size() > 0)
            return this.findByDepartmentIds(list.stream().map(Department::getId).collect(Collectors.toList()));
        else
            return null;
    }

    @Override
    public List<DepartmentDetailBO> findByDepartmentIds(List<String> ids) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.in("department_id", ids));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public DepartmentDetailBO findByDepartment(String id) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.eq("department_id", id));
        return this.transformationToBO(super.findOne(dto));
    }

    @Override
    public DepartmentDetailBO findBOById(String id) throws SerException {
        return this.transformationToBO(super.findById(id));
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DepartmentDetailBO save(DepartmentDetailTO to) throws SerException {
        DepartmentDetail department = BeanTransform.copyProperties(to, DepartmentDetail.class, true);
        department.setHierarchy(hierarchySer.findById(to.getHierarchy_id()));
        department.setCreateTime(LocalDateTime.now());
        super.save(department);
        return this.transformationToBO(department);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DepartmentDetailBO update(DepartmentDetailTO to) throws SerException {
        DepartmentDetail entity = BeanTransform.copyProperties(to, DepartmentDetail.class, true);
        entity.setHierarchy(hierarchySer.findById(to.getHierarchy_id()));
        super.update(entity);
        return this.transformationToBO(entity);
    }
}
