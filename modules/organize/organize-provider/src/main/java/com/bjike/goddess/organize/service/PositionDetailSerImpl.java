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
import com.bjike.goddess.user.api.PositionAPI;
import com.bjike.goddess.user.bo.PositionBO;
import com.bjike.goddess.user.dto.PositionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
    private PositionAPI positionAPI;
    @Autowired
    private DepartmentDetailSer departmentDetailSer;
    @Autowired
    private ArrangementSer arrangementSer;
    @Autowired
    private ModuleTypeSer moduleTypeSer;

    private PositionDetailBO transformationToBO(PositionDetail entity) throws SerException {
        PositionDetailBO bo = BeanTransform.copyProperties(entity, PositionDetailBO.class);
        PositionBO position = positionAPI.findById(entity.getPosition_id());
        DepartmentDetailBO department = departmentDetailSer.findBOById(entity.getDepartment().getId());
        Arrangement arrangement = entity.getArrangement();
        ModuleType moduleType = entity.getModule();
        bo.setArea(department.getArea());
        bo.setDepartment_id(department.getId());
        bo.setDepartmentName(department.getDepartment());
        bo.setArrangementName(arrangement.getArrangement());
        bo.setDepartmentName(department.getDepartment());
        bo.setHierarchyName(department.getHierarchyName());
        bo.setPositionName(position.getName());
        bo.setArrangement_id(arrangement.getId());
        bo.setModule_id(moduleType.getId());
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
    public List<PositionDetailBO> findStatus() throws SerException {
        PositionDTO dto = new PositionDTO();
        dto.getConditions().add(Restrict.eq(STATUS, Status.THAW));
        List<PositionBO> list = positionAPI.findByCis(dto);
        return this.findByPostIds(list.stream().map(PositionBO::getId).collect(Collectors.toList()).toArray(new String[0]));
    }

    @Override
    public List<PositionDetailBO> findByPostIds(String[] ids) throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.in("position_id", ids));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public PositionDetailBO findByPostId(String id) throws SerException {
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq("position_id", id));
        return this.transformationToBO(super.findOne(dto));
    }

    @Override
    public List<PositionDetailBO> findChild(String parentId) throws SerException {
        return null;
    }

    @Override
    public PositionDetailBO findParent(String postId) throws SerException {
        return null;
    }

    @Override
    public List<PositionDetailBO> findChildByArrangement(String postId) throws SerException {
        PositionDetail entity = super.findById(postId);
        List<String> arrangementIds = arrangementSer.findChild(entity.getArrangement().getId())
                .stream().map(ArrangementBO::getId).collect(Collectors.toList());
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.in("arrangement_id", arrangementIds));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public List<PositionDetailBO> findParentByArrangement(String postId) throws SerException {
        PositionDetail entity = super.findById(postId);
        Arrangement arrangement = arrangementSer.findById(entity.getArrangement().getId());
        PositionDetailDTO dto = new PositionDetailDTO();
        dto.getConditions().add(Restrict.eq("arrangement_id", arrangement.getParent().getId()));
        return this.transformationToBOList(super.findByCis(dto));
    }

    @Override
    public PositionDetailBO findBOById(String id) throws SerException {
        return this.transformationToBO(super.findById(id));
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionDetailBO save(PositionDetailTO to) throws SerException {
        PositionDetail positionDetail = BeanTransform.copyProperties(to, PositionDetail.class);
        positionDetail.setDepartment(departmentDetailSer.findById(to.getDepartment_id()));
        positionDetail.setArrangement(arrangementSer.findById(to.getArrangement_id()));
        positionDetail.setModule(moduleTypeSer.findById(to.getModule_id()));
        positionDetail.setCreateTime(LocalDateTime.now());
        super.save(positionDetail);
        return this.transformationToBO(positionDetail);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionDetailBO update(PositionDetailTO to) throws SerException {
        PositionDetail entity = BeanTransform.copyProperties(to, PositionDetail.class, true), positionDetail = super.findById(to.getId());
        entity.setDepartment(departmentDetailSer.findById(to.getDepartment_id()));
        entity.setArrangement(arrangementSer.findById(to.getArrangement_id()));
        positionDetail.setModule(moduleTypeSer.findById(to.getModule_id()));
        entity.setCreateTime(positionDetail.getCreateTime());
        super.update(entity);
        return BeanTransform.copyProperties(entity, PositionBO.class);
    }
}
