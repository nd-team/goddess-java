package com.bjike.goddess.projectmarketfee.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.projectmarketfee.bo.CostAnalysisBO;
import com.bjike.goddess.projectmarketfee.bo.GradeBO;
import com.bjike.goddess.projectmarketfee.dto.CostAnalysisDTO;
import com.bjike.goddess.projectmarketfee.dto.GradeDTO;
import com.bjike.goddess.projectmarketfee.dto.WarnDTO;
import com.bjike.goddess.projectmarketfee.entity.Grade;
import com.bjike.goddess.projectmarketfee.to.GradeTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 等级设计业务实现
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-05-09 04:55 ]
 * @Description: [ 等级设计业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "projectmarketfeeSerCache")
@Service
public class GradeSerImpl extends ServiceImpl<Grade, GradeDTO> implements GradeSer {
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public GradeBO save(GradeTO to) throws SerException {
        Grade grade = BeanTransform.copyProperties(to, Grade.class, true);
        super.save(grade);
        return BeanTransform.copyProperties(grade, GradeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void edit(GradeTO to) throws SerException {
        Grade grade = super.findById(to.getId());
        LocalDateTime a = grade.getCreateTime();
        LocalDateTime b = grade.getModifyTime();
        grade = BeanTransform.copyProperties(to, Grade.class, true);
        grade.setCreateTime(a);
        grade.setModifyTime(b);
        super.update(grade);
    }

    @Override
    public List<GradeBO> list(GradeDTO dto) throws SerException {
        List<Grade> list = super.findByCis(dto, true);
        return BeanTransform.copyProperties(list, GradeBO.class);
    }

    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void delete(String id) throws SerException {
        super.remove(id);
    }

    @Override
    public GradeBO findByID(String id) throws SerException {
        Grade grade = super.findById(id);
        return BeanTransform.copyProperties(grade, GradeBO.class);
    }

    @Override
    public GradeBO countNum(GradeDTO dto) throws SerException {
        GradeBO bo = new GradeBO();
        bo.setNum(super.count(dto));
        return bo;
    }
}