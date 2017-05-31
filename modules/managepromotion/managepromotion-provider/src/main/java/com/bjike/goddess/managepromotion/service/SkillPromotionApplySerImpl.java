package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.SkillPromotionApplyBO;
import com.bjike.goddess.managepromotion.dto.SkillPromotionApplyDTO;
import com.bjike.goddess.managepromotion.entity.SkillPromotionApply;
import com.bjike.goddess.managepromotion.to.SkillPromotionApplyTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 技能晋升申请业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 09:03 ]
 * @Description: [ 技能晋升申请业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class SkillPromotionApplySerImpl extends ServiceImpl<SkillPromotionApply, SkillPromotionApplyDTO> implements SkillPromotionApplySer {

    @Override
    public Long countSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        Long count = super.count(skillPromotionApplyDTO);
        return count;
    }

    @Override
    public SkillPromotionApplyBO getOne(String id) throws SerException {
        SkillPromotionApply skillPromotionApply = super.findById(id);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public List<SkillPromotionApplyBO> findListSkillPromotionApply(SkillPromotionApplyDTO skillPromotionApplyDTO) throws SerException {
        List<SkillPromotionApply> skillPromotionApplies = super.findByPage(skillPromotionApplyDTO);
        List<SkillPromotionApplyBO> skillPromotionApplyBOS = BeanTransform.copyProperties(skillPromotionApplies, SkillPromotionApplyBO.class);
        return skillPromotionApplyBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SkillPromotionApplyBO insertSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        SkillPromotionApply skillPromotionApply = BeanTransform.copyProperties(skillPromotionApplyTO, SkillPromotionApply.class, true);
        skillPromotionApply.setCreateTime(LocalDateTime.now());
        super.save(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SkillPromotionApplyBO editSkillPromotionApply(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        BeanTransform.copyProperties(skillPromotionApplyTO, skillPromotionApply, true);
        skillPromotionApply.setModifyTime(LocalDateTime.now());
        super.update(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO headAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        skillPromotionApply.setHeadOpinion(skillPromotionApplyTO.getHeadOpinion());
        skillPromotionApply.setPhase(1);
        super.update(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO budgetAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        skillPromotionApply.setBudgetOpinion(skillPromotionApplyTO.getBudgetOpinion());
        skillPromotionApply.setPhase(1);
        super.update(skillPromotionApply);
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO projectManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        boolean b1 = skillPromotionApply.getHeadOpinion() == null;
        boolean b2 = skillPromotionApply.getBudgetOpinion() == null;
        if (b1 && b2) {
            throw new SerException("负责人或预算模块还未审核");
        } else {
            skillPromotionApply.setProjectManagerOpinion(skillPromotionApplyTO.getProjectManagerOpinion());
            skillPromotionApply.setPhase(2);
            super.update(skillPromotionApply);
        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO planAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        boolean b1 = skillPromotionApply.getProjectManagerOpinion() == null;
        if (b1) {
            throw new SerException("项目经理还未审核");
        } else {
            skillPromotionApply.setPlanOpinion(skillPromotionApplyTO.getPlanOpinion());
            skillPromotionApply.setPhase(3);
            super.update(skillPromotionApply);
        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }

    @Override
    public SkillPromotionApplyBO generalManagerAudit(SkillPromotionApplyTO skillPromotionApplyTO) throws SerException {
        SkillPromotionApply skillPromotionApply = super.findById(skillPromotionApplyTO.getId());
        boolean b1 = skillPromotionApply.getPlanOpinion() == null;
        if (b1) {
            throw new SerException("规划模块还未审核");
        } else {
            skillPromotionApply.setManagerOpinion(skillPromotionApplyTO.getManagerOpinion());
            skillPromotionApply.setPhase(4);
            skillPromotionApply.setAuditStatus(skillPromotionApplyTO.getAuditStatus());
            super.update(skillPromotionApply);
        }
        return BeanTransform.copyProperties(skillPromotionApply, SkillPromotionApplyBO.class);
    }
}