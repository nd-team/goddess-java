//package com.bjike.goddess.organize.service;
//
//import com.bjike.goddess.common.api.dto.Restrict;
//import com.bjike.goddess.common.api.exception.SerException;
//import com.bjike.goddess.common.jpa.service.ServiceImpl;
//import com.bjike.goddess.common.utils.bean.BeanTransform;
//import com.bjike.goddess.organize.bo.PositionWorkDetailBO;
//import com.bjike.goddess.organize.dto.PositionWorkDetailDTO;
//import com.bjike.goddess.organize.entity.PositionWorkDetail;
//import com.bjike.goddess.organize.to.PositionWorkDetailTO;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 岗位工作详细业务实现
// *
// * @Author: [dengjunren]
// * @Date: [2017-03-08 14:20]
// * @Description: [ ]
// * @Version: [1.0.0]
// * @Copy: [com.bjike]
// */
//@Service
//public class PositionWorkDetailSerImpl extends ServiceImpl<PositionWorkDetail, PositionWorkDetailDTO> implements PositionWorkDetailSer {
//
////    @Autowired
////    private PositionInstructionSer positionInstructionSer;
////
////    /**
////     * 实体数据传输转换
////     *
////     * @param entity 岗位工作详细实体
////     * @return
////     */
////    private PositionWorkDetailBO transformBO(PositionWorkDetail entity) {
////        PositionWorkDetailBO bo = BeanTransform.copyProperties(entity, PositionWorkDetailBO.class);
////        bo.setInstructionId(entity.getInstruction().getId());
////        bo.setSerialNumber(entity.getInstruction().getSerialNumber());
////        bo.setAngle(entity.getInstruction().getAngle().getName());
////        if (null != entity.getInstruction().getReflect().getClassify())
////            bo.setClassify(entity.getInstruction().getReflect().getClassify().getName());
////        bo.setDimension(entity.getInstruction().getDimension().getName());
////        bo.setFunction(entity.getInstruction().getFunction());
////        bo.setFrequency(entity.getInstruction().getFrequency());
////        return bo;
////    }
////
////    /**
////     * 集合数据传输转换
////     *
////     * @param list 岗位工作详细实体集合
////     * @return
////     */
////    private List<PositionWorkDetailBO> transformBOList(List<PositionWorkDetail> list) {
////        List<PositionWorkDetailBO> bos = new ArrayList<>(0);
////        for (PositionWorkDetail entity : list)
////            bos.add(this.transformBO(entity));
////        return bos;
////    }
////
////    @Override
////    public List<PositionWorkDetailBO> findByInstruction(String id) throws SerException {
////        PositionWorkDetailDTO dto = new PositionWorkDetailDTO();
////        dto.getConditions().add(Restrict.eq("instruction.id", id));
////        List<PositionWorkDetail> list = super.findByCis(dto);
////        return transformBOList(list);
////    }
////
////    @Transactional(rollbackFor = SerException.class)
////    @Override
////    public PositionWorkDetailBO save(PositionWorkDetailTO to) throws SerException {
////        PositionWorkDetail entity = BeanTransform.copyProperties(to, PositionWorkDetail.class);
////        entity.setInstruction(positionInstructionSer.findById(to.getInstructionId()));
////        if (entity.getInstruction() == null)
////            throw new SerException("岗位说明书为空,无法保存");
////        super.save(entity);
////        return transformBO(entity);
////    }
////
////    @Transactional(rollbackFor = SerException.class)
////    @Override
////    public PositionWorkDetailBO update(PositionWorkDetailTO to) throws SerException {
////        if (StringUtils.isBlank(to.getId()))
////            throw new SerException("数据ID不能为空");
////        PositionWorkDetail entity = super.findById(to.getId());
////        if (entity == null)
////            throw new SerException("数据对象不能为空");
////        BeanTransform.copyProperties(to, entity, true);
////        entity.setModifyTime(LocalDateTime.now());
////        entity.setInstruction(positionInstructionSer.findById(to.getInstructionId()));
////        if (entity.getInstruction() == null)
////            throw new SerException("岗位说明书为空,无法保存");
////        super.update(entity);
////        return transformBO(entity);
////    }
////
////    @Override
////    public PositionWorkDetailBO delete(String id) throws SerException {
////        PositionWorkDetail entity = super.findById(id);
////        if (entity == null)
////            throw new SerException("数据对象不能为空");
////        try {
////            super.remove(entity);
////        } catch (Exception e) {
////            throw new SerException("此处已被引用,无法删除");
////        }
////        return transformBO(entity);
////    }
////
////    @Override
////    public List<PositionWorkDetailBO> maps(PositionWorkDetailDTO dto) throws SerException {
////        dto.getSorts().add("instruction_id=asc");
////        return this.transformBOList(super.findByPage(dto));
////    }
////
////    @Override
////    public PositionWorkDetailBO getById(String id) throws SerException {
////        return this.transformBO(super.findById(id));
////    }
//}
