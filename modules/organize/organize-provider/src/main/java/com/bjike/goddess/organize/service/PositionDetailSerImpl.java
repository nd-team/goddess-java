package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.api.type.Status;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.ArrangementBO;
import com.bjike.goddess.organize.bo.DepartmentDetailBO;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.dto.PositionDetailDTO;
import com.bjike.goddess.organize.entity.Arrangement;
import com.bjike.goddess.organize.entity.ModuleType;
import com.bjike.goddess.organize.entity.PositionDetail;
import com.bjike.goddess.organize.to.PositionDetailTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 岗位详细业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午10:08]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class PositionDetailSerImpl extends ServiceImpl<PositionDetail, PositionDetailDTO> implements PositionDetailSer {

    @Autowired
    private DepartmentDetailSer departmentDetailSer;
    @Autowired
    private ArrangementSer arrangementSer;
    @Autowired
    private ModuleTypeSer moduleTypeSer;

    private PositionDetailBO transformationToBO(PositionDetail entity) throws SerException {
        PositionDetailBO bo = BeanTransform.copyProperties(entity, PositionDetailBO.class);
        DepartmentDetailBO department = departmentDetailSer.findBOById(entity.getDepartment().getId());
        Arrangement arrangement = entity.getArrangement();
        ModuleType moduleType = entity.getModule();
        bo.setArea(department.getArea());
        bo.setDepartmentId(department.getId());
        bo.setDepartmentName(department.getDepartment());
        bo.setArrangementName(arrangement.getArrangement());
        bo.setDepartmentName(department.getDepartment());
        bo.setHierarchyName(department.getHierarchyName());
        bo.setArrangementId(arrangement.getId());
        bo.setModuleId(moduleType.getId());
        bo.setModuleName(moduleType.getModule());
        bo.setShowNumber(String.format("%s-%s-%s", department.getShowNumber(), arrangement.getSerialNumber(), entity.getSerialNumber()));
        return bo;
    }

    @Override
    public List<PositionDetailBO> transformationToBOList(Collection<PositionDetail> list) throws SerException {
        List<PositionDetailBO> bos = new ArrayList<>(list.size());
        for (PositionDetail entity : list)
            bos.add(this.transformationToBO(entity));
        return bos;
    }

    @Override
    public PositionDetailBO congeal(String id) throws SerException {
        PositionDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.CONGEAL);
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public PositionDetailBO thaw(String id) throws SerException {
        PositionDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        entity.setStatus(Status.THAW);
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public List<PositionDetailBO> findStatus() throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<PositionDetail> list = super.findByCis(dto);
        return this.transformationToBOList(list);
    }

    @Override
    public List<PositionDetailBO> findByPostIds(String[] ids) throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.in("id", ids));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public List<PositionDetailBO> findChildByArrangement(String postId) throws SerException {
        PositionDetail entity = super.findById(postId);
        List<ArrangementBO> arrangementList = arrangementSer.findChild(entity.getArrangement().getId());
        PositionDetailDTO dto = new PositionDetailDTO();
        try {
            for (ArrangementBO arrangement : arrangementList)
                dto.getConditions().add(Restrict.eq("arrangement.id", arrangement.getId()));
        } catch (Exception e) {
            return new ArrayList<>(0);
        }
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public List<PositionDetailBO> findParentByArrangement(String postId) throws SerException {
        PositionDetail entity = super.findById(postId);
        Arrangement arrangement = arrangementSer.findById(entity.getArrangement().getId());
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq("arrangement.id", arrangement.getParent().getId()));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public PositionDetailBO findBOById(String id) throws SerException {
        return this.transformationToBO(super.findById(id));
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionDetailBO save(PositionDetailTO to) throws SerException {
        PositionDetail entity = BeanTransform.copyProperties(to, PositionDetail.class);
        entity.setDepartment(departmentDetailSer.findById(to.getDepartmentId()));
        if (null == entity.getDepartment())
            throw new SerException("部门不存在");
        entity.setArrangement(arrangementSer.findById(to.getArrangementId()));
        if (null == entity.getDepartment())
            throw new SerException("岗位层级不存在");
        entity.setModule(moduleTypeSer.findById(to.getModuleId()));
        if (null == entity.getDepartment())
            throw new SerException("模块类型不存在");
        entity.setStatus(entity.getDepartment().getStatus());
        super.save(entity);
        return this.transformationToBO(entity);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionDetailBO update(PositionDetailTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        PositionDetail entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setDepartment(departmentDetailSer.findById(to.getDepartmentId()));
        if (null == entity.getDepartment())
            throw new SerException("部门不存在");
        entity.setArrangement(arrangementSer.findById(to.getArrangementId()));
        if (null == entity.getDepartment())
            throw new SerException("岗位层级不存在");
        entity.setModule(moduleTypeSer.findById(to.getModuleId()));
        if (null == entity.getDepartment())
            throw new SerException("模块类型不存在");
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
        return this.transformationToBO(entity);
    }

    @Override
    public PositionDetailBO delete(String id) throws SerException {
        PositionDetail entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        try {
            super.remove(entity);
        } catch (SerException e) {
            throw new SerException("存在依赖关系无法删除");
        }
        return this.transformationToBO(entity);
    }

    @Override
    public List<PositionDetailBO> maps(PositionDetailDTO dto) throws SerException {
        dto.getSorts().add("department_id=asc");
        return this.transformationToBOList(super.findByPage(dto));
    }
}
