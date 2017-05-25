package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.AreaBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.OpinionBO;
import com.bjike.goddess.organize.dto.DepartmentDetailDTO;
import com.bjike.goddess.organize.entity.DepartmentDetail;
import com.bjike.goddess.organize.to.DepartmentDetailTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
    private WorkRangeSer workRangeSer;

    @Autowired
    private HierarchySer hierarchySer;

    private DepartmentDetailBO transformationToBO(DepartmentDetail entity) throws SerException {
        DepartmentDetailBO bo = BeanTransform.copyProperties(entity, DepartmentDetailBO.class);
        bo.setHierarchyId(entity.getHierarchy().getId());
        bo.setHierarchyName(entity.getHierarchy().getHierarchy());
        bo.setHierarchyNumber(entity.getHierarchy().getSerialNumber());
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
    public List<DepartmentDetailBO> findByHierarchy(String hierarchyId) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.eq("hierarchy.id", hierarchyId));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public List<DepartmentDetailBO> findStatus() throws SerException {
        DepartmentDetailDTO departmentDTO = new DepartmentDetailDTO();
        departmentDTO.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<DepartmentDetail> list = super.findByCis(departmentDTO);
        return this.transformationToBOList(list);
    }

    @Override
    public List<DepartmentDetailBO> findByDepartmentIds(List<String> ids) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.in("id", ids));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public DepartmentDetailBO findBOById(String id) throws SerException {
        return this.transformationToBO(super.findById(id));
    }

    /**
     * 检测部门或部门编号是否重复
     *
     * @param to
     * @throws SerException
     */
    private void checkUnique(DepartmentDetailTO to) throws SerException {
        String[] fields = {"id", "department"};
        StringBuilder sql = new StringBuilder(" SELECT ");
        sql.append(" id,department ").append(" FROM organize_department_detail ");
        sql.append(" WHERE serialNumber='").append(to.getSerialNumber()).append("' OR department='").append(to.getDepartment()).append("'");
        List<DepartmentDetailBO> list = super.findBySql(sql.toString(), DepartmentDetailBO.class, fields);
        if (list.size() > 0)
            throw new SerException("部门或编号已存在,无法保存");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DepartmentDetailBO save(DepartmentDetailTO to) throws SerException {
        this.checkUnique(to);
        DepartmentDetail department = BeanTransform.copyProperties(to, DepartmentDetail.class, true);
        department.setHierarchy(hierarchySer.findById(to.getHierarchyId()));
        if (department.getHierarchy() == null)
            throw new SerException("体系不能为空");
        department.setCreateTime(LocalDateTime.now());
        department.setStatus(Status.THAW);
        super.save(department);
        return this.transformationToBO(department);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public DepartmentDetailBO update(DepartmentDetailTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        DepartmentDetail entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (!entity.getSerialNumber().equals(to.getSerialNumber()) || !entity.getDepartment().equals(to.getDepartment()))
            this.checkUnique(to);
        BeanTransform.copyProperties(to, entity, true);
        entity.setHierarchy(hierarchySer.findById(to.getHierarchyId()));
        if (entity.getHierarchy() == null)
            throw new SerException("体系不能为空");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public DepartmentDetailBO congeal(String id) throws SerException {
        DepartmentDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public DepartmentDetailBO thaw(String id) throws SerException {
        DepartmentDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public DepartmentDetailBO delete(String id) throws SerException {
        DepartmentDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        if (workRangeSer.findByDepartment(id).size() > 0)
            throw new SerException("存在依赖关系无法删除");
        super.remove(entity);
        return null;
    }

    @Override
    public DepartmentDetailBO getById(String id) throws SerException {
        return this.transformationToBO(super.findById(id));
    }

    @Override
    public List<AreaBO> findArea() throws SerException {
        List<DepartmentDetail> list = super.findAll().stream()
                .sorted(Comparator.comparing(DepartmentDetail::getArea))
                .collect(Collectors.toList());
        List<AreaBO> bos = new ArrayList<>(0);
        String area = "";
        for (DepartmentDetail entity : list)
            if (!entity.getArea().equals(area)) {
                area = entity.getArea();
                bos.add(new AreaBO(area));
            }
        return bos;
    }

    @Override
    public List<DepartmentDetailBO> findByArea(String area) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.eq("area", area));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public List<OpinionBO> findByIds(String... ids) throws SerException {
        DepartmentDetailDTO dto = new DepartmentDetailDTO();
        dto.getConditions().add(Restrict.in(ID, ids));
        List<DepartmentDetail> list = super.findByCis(dto);
        List<OpinionBO> bos = new ArrayList<>(0);
        if (null != list)
            for (DepartmentDetail entity : list)
                bos.add(new OpinionBO(entity.getId(), entity.getDepartment()));
        return bos;
    }
}
