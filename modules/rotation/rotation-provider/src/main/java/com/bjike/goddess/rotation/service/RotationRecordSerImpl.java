package com.bjike.goddess.rotation.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.common.utils.date.DateUtil;
import com.bjike.goddess.rotation.bo.RotationRecordBO;
import com.bjike.goddess.rotation.dto.RotationRecordDTO;
import com.bjike.goddess.rotation.entity.RotationRecord;
import com.bjike.goddess.rotation.enums.AuditType;
import com.bjike.goddess.rotation.to.GuidePermissionTO;
import com.bjike.goddess.rotation.to.RotationConditionTO;
import com.bjike.goddess.rotation.to.RotationRecordTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 岗位轮换记录业务实现
 *
 * @Author: [ caiwenxian ]
 * @Date: [ 2018-01-08 09:29 ]
 * @Description: [ 岗位轮换记录业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "rotationSerCache")
@Service
public class RotationRecordSerImpl extends ServiceImpl<RotationRecord, RotationRecordDTO> implements RotationRecordSer {

    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    @Override
    public List<RotationRecordBO> list(RotationRecordDTO dto) throws SerException {
        List<RotationRecord> entities =  super.findByPage(dto);

        return convertRecord(entities);
    }

    @Override
    public void add(RotationRecordTO to) throws SerException {
        RotationRecord entity = BeanTransform.copyProperties(to, RotationRecord.class);
        super.save(entity);
    }

    @Override
    public void update(RotationRecordTO to) throws SerException {
        RotationRecord old = super.findById(to.getId());
        if (null == old) {
            throw new SerException("更新实体不存在");
        }
        RotationRecord entity = BeanTransform.copyProperties(to, RotationRecord.class);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    @Override
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    List<RotationRecordBO> convertRecord(List<RotationRecord> entities){
        if (entities.size() == 0) {
            return null;
        }
        List<RotationRecordBO> bos = new ArrayList<>();
        for (RotationRecord entity : entities) {
            RotationRecordBO bo = new RotationRecordBO();
            if (null != entity.getCoverRotation()) {
                bo = BeanTransform.copyProperties(entity.getCoverRotation(), RotationRecordBO.class);
                bo.setApplyLevelId(entity.getCoverRotation().getApplyLevel().getId());
                bo.setApplyLevelArrangement(entity.getCoverRotation().getApplyLevel().getArrangement());
                bo.setRotationLevelId(entity.getCoverRotation().getRotationLevel().getId());
                bo.setRotationLevelArrangement(entity.getCoverRotation().getRotationLevel().getArrangement());
                if (AuditType.ALLOWED.equals(bo.getAudit())) {
                    bo.setGetTime(entity.getModifyTime().toString());
                }   
            } else {
                bo = BeanTransform.copyProperties(entity.getRecommendRotation(), RotationRecordBO.class);
                bo.setApplyLevelId(entity.getRecommendRotation().getApplyLevel().getId());
                bo.setApplyLevelArrangement(entity.getRecommendRotation().getApplyLevel().getArrangement());
                bo.setRotationLevelId(entity.getRecommendRotation().getRotationLevel().getId());
                bo.setRotationLevelArrangement(entity.getRecommendRotation().getRotationLevel().getArrangement());

                if (AuditType.ALLOWED.equals(bo.getAudit())) {
                    bo.setGetTime(entity.getModifyTime().toString());
                }
            }
            bo.setRotationType(entity.getRotationType());
            bo.setApplyTime(entity.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm")).toString());
            bos.add(bo);
        }
        return bos;
    }


}