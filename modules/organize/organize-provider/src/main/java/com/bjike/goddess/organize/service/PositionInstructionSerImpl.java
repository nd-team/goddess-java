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
import org.apache.commons.lang3.StringUtils;
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
        bo.setPositionName(detailBO.getPosition());
        bo.setPositionNumber(detailBO.getShowNumber());
        bo.setArrangement(detailBO.getArrangementName());
        bo.setHierarchy(detailBO.getHierarchyName());
        bo.setDepartment(detailBO.getDepartmentName());
        bo.setPool(detailBO.getPool());
        bo.setStaff(detailBO.getStaff());
        bo.setParent("");
        for (PositionDetailBO detail : positionDetailSer.findParentByArrangement(detailBO.getId()))
            bo.setParent(bo.getParent() + detail.getPosition() + ",");
        bo.setChildren("");
        for (PositionDetailBO detail : positionDetailSer.findChildByArrangement(detailBO.getId()))
            bo.setChildren(bo.getChildren() + detail.getPosition() + ",");
        bo.setAngleId(entity.getAngle().getId());
        bo.setAngleName(entity.getAngle().getName());
        bo.setDimensionId(entity.getDimension().getId());
        bo.setDimensionName(entity.getDimension().getName());
        bo.setClassifyId(entity.getClassify().getId());
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
        super.save(this.setForeign(instruction, to));
        return this.transformToBO(instruction);
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
        if (null == instruction.getPosition())
            throw new SerException("岗位不能为空");
        instruction.setAngle(angleSer.findById(to.getAngleId()));
        if (null == instruction.getAngle())
            throw new SerException("角度不能为空");
        instruction.setClassify(classifySer.findById(to.getClassifyId()));
        if (null == instruction.getClassify())
            throw new SerException("分类不能为空");
        instruction.setDimension(dimensionSer.findById(to.getDimensionId()));
        if (null == instruction.getDimension())
            throw new SerException("维度不能为空");
        if (null != to.getReflectIds())
            for (String id : to.getReflectIds())
                instruction.getReflects().add(reflectSer.findById(id));
        if (null != to.getOperateIds())
            for (String id : to.getOperateIds())
                instruction.getOperates().add(operateSer.findById(id));
        return instruction;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public PositionInstructionBO update(PositionInstructionTO to) throws SerException {
        if (StringUtils.isBlank(to.getId()))
            throw new SerException("数据ID不能为空");
        PositionInstruction entity = super.findById(to.getId());
        if (entity == null)
            throw new SerException("数据对象不能为空");
        BeanTransform.copyProperties(to, entity, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(this.setForeign(entity, to));
        return this.transformToBO(entity);
    }

    @Override
    public PositionInstructionBO delete(String id) throws SerException {
        PositionInstruction entity = super.findById(id);
        if (entity == null)
            throw new SerException("数据对象不能为空");
        try {
            super.remove(entity);
        } catch (SerException e) {
            throw new SerException("存在依赖关系无法删除");
        }
        return this.transformToBO(entity);
    }

    @Override
    public List<PositionInstructionBO> maps(PositionInstructionDTO dto) throws SerException {
        dto.getSorts().add("positionId=asc");
        return this.transformToBOList(super.findByPage(dto));
    }

    @Override
    public PositionInstructionBO getById(String id) throws SerException {
        return this.transformToBO(super.findById(id));
    }

    @Override
    public List<PositionInstructionBO> findByAngle(String angleId) throws SerException {
        PositionInstructionDTO dto = new PositionInstructionDTO();
        dto.getConditions().add(Restrict.eq("angle.id", angleId));
        List<PositionInstruction> list = super.findByCis(dto);
        return this.transformToBOList(list);
    }

    @Override
    public List<PositionInstructionBO> findByDimension(String dimensionId) throws SerException {
        PositionInstructionDTO dto = new PositionInstructionDTO();
        dto.getConditions().add(Restrict.eq("dimension.id", dimensionId));
        List<PositionInstruction> list = super.findByCis(dto);
        return this.transformToBOList(list);
    }

    @Override
    public List<PositionInstructionBO> findByClassify(String classifyId) throws SerException {
        PositionInstructionDTO dto = new PositionInstructionDTO();
        dto.getConditions().add(Restrict.eq("classify.id", classifyId));
        List<PositionInstruction> list = super.findByCis(dto);
        return this.transformToBOList(list);
    }

    @Override
    public List<PositionInstructionBO> findByOperate(String operateId) throws SerException {
        String[] fields = {"id"};
        String sql = String.format("SELECT  instruction_id  FROM organize_position_instruction_operate  WHERE operate_id = '%s'", operateId);
        List<PositionInstructionBO> bos = super.findBySql(sql, PositionDetailBO.class, fields);
        List<PositionInstruction> list = new ArrayList<>(0);
        for (PositionInstructionBO bo : bos)
            list.add(super.findById(bo.getId()));
        return this.transformToBOList(list);
    }

    @Override
    public List<PositionInstructionBO> findByReflect(String reflectId) throws SerException {
        String[] fields = {"id"};
        String sql = String.format("SELECT  instruction_id  FROM organize_position_instruction_reflect  WHERE reflect_id = '%s'", reflectId);
        List<PositionInstructionBO> bos = super.findBySql(sql, PositionDetailBO.class, fields);
        List<PositionInstruction> list = new ArrayList<>(0);
        for (PositionInstructionBO bo : bos)
            list.add(super.findById(bo.getId()));
        return this.transformToBOList(list);
    }
}
