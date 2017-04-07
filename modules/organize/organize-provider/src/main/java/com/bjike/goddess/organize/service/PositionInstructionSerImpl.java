package com.bjike.goddess.organize.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.organize.bo.PositionDetailBO;
import com.bjike.goddess.organize.bo.PositionInstructionBO;
import com.bjike.goddess.organize.dto.PositionInstructionDTO;
import com.bjike.goddess.organize.entity.Operate;
import com.bjike.goddess.organize.entity.PositionInstruction;
import com.bjike.goddess.organize.entity.Reflect;
import com.bjike.goddess.organize.to.PositionInstructionTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 岗位说明书业务实现
 *
 * @Author: [dengjunren]
 * @Date: [17-3-8 上午11:38]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike.goddess.organize.entity]
 */
@Service
public class PositionInstructionSerImpl extends ServiceImpl<PositionInstruction, PositionInstructionDTO> implements PositionInstructionSer {

    @Autowired
    private PositionDetailSer positionDetailSer;
    @Autowired
    private AngleSer angleSer;
    @Autowired
    private DimensionSer dimensionSer;
    @Autowired
    private InstructionClassifySer classifySer;
    @Autowired
    private OperateSer operateSer;
    @Autowired
    private ReflectSer reflectSer;

    /**
     * 岗位说明书传输类转换
     *
     * @param entity 岗位说明书实体
     * @return
     * @throws SerException
     */
    private PositionInstructionBO transformToBO(PositionInstruction entity) throws SerException {
        PositionInstructionBO bo = BeanTransform.copyProperties(entity, PositionInstructionBO.class);
        PositionDetailBO detailBO = positionDetailSer.findBOById(entity.getPosition().getId());
        bo.setPositionId(detailBO.getId());
        bo.setPositionName(detailBO.getPositionName());
        bo.setPositionNumber(detailBO.getShowNumber());
        bo.setArrangement(detailBO.getArrangementName());
        bo.setHierarchy(detailBO.getHierarchyName());
        bo.setDepartment(detailBO.getDepartmentName());
        bo.setPool(detailBO.getPool());
        bo.setStaff(detailBO.getStaff());
        bo.setParent(positionDetailSer.findParent(detailBO.getPosition_id()).getPositionName());
        bo.setChildren("");
        for (PositionDetailBO detail : positionDetailSer.findChild(detailBO.getPosition_id()))
            bo.setChildren(bo.getChildren() + detail.getPositionName() + ",");
        bo.setAngle_id(entity.getAngle().getId());
        bo.setAngleName(entity.getAngle().getName());
        bo.setDimension_id(entity.getDimension().getId());
        bo.setDimensionName(entity.getDimension().getName());
        bo.setClassify_id(entity.getClassify().getId());
        bo.setClassifyName(entity.getClassify().getName());
        bo.setOperateIds(entity.getOperates().stream().map(Operate::getId).collect(Collectors.toList()).toArray(new String[0]));
        bo.setOperateNames("");
        for (Operate operate : entity.getOperates())
            bo.setOperateNames(bo.getOperateNames() + operate.getName() + ",");
        bo.setReflectIds(entity.getReflects().stream().map(Reflect::getId).collect(Collectors.toList()).toArray(new String[0]));
        bo.setReflectNames("");
        for (Reflect reflect : entity.getReflects())
            bo.setReflectNames(bo.getReflectNames() + reflect.getName() + ",");
        return bo;
    }

    /**
     * 岗位说明书传输类转换
     *
     * @param list 岗位说明书实体集合
     * @return
     * @throws SerException
     */
    private List<PositionInstructionBO> transformToBOList(List<PositionInstruction> list) throws SerException {
        List<PositionInstructionBO> bos = new ArrayList<>(0);
        for (PositionInstruction entity : list)
            bos.add(this.transformToBO(entity));
        return bos;
    }

    @Override
    public List<PositionInstructionBO> findByPosition(String id) throws SerException {
        PositionInstructionDTO dto = new PositionInstructionDTO();
        dto.getConditions().add(Restrict.eq("position.id", id));
        return this.transformToBOList(super.findByCis(dto));
    }

    @Override
    public List<PositionInstructionBO> findPage(PositionInstructionDTO dto) throws SerException {
        return this.transformToBOList(super.findByPage(dto));
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionInstructionBO save(PositionInstructionTO to) throws SerException {
        PositionInstruction instruction = BeanTransform.copyProperties(to, PositionInstruction.class);
        instruction.setCreateTime(LocalDateTime.now());
        super.save(this.setForeign(instruction, to));
        return BeanTransform.copyProperties(instruction, PositionInstructionBO.class);
    }

    /**
     * 设置外键关系
     *
     * @param instruction 存储实体
     * @param to          临时实体
     * @return
     * @throws SerException
     */
    private PositionInstruction setForeign(PositionInstruction instruction, PositionInstructionTO to) throws SerException {
        instruction.setPosition(positionDetailSer.findById(to.getPositionId()));
        instruction.setAngle(angleSer.findById(to.getAngle_id()));
        instruction.setClassify(classifySer.findById(to.getClassify_id()));
        instruction.setDimension(dimensionSer.findById(to.getDimension_id()));
        for (String id : to.getReflectIds())
            instruction.getReflects().add(reflectSer.findById(id));
        for (String id : to.getOperateIds())
            instruction.getOperates().add(operateSer.findById(id));
        return instruction;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionInstructionBO update(PositionInstructionTO to) throws SerException {
        PositionInstruction instruction = BeanTransform.copyProperties(to, PositionInstruction.class), entity = super.findById(to.getId());
        instruction.setCreateTime(entity.getCreateTime());
        super.update(this.setForeign(instruction, to));
        return BeanTransform.copyProperties(instruction, PositionInstructionBO.class);
    }
}
