package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.SkillGradingBO;
import com.bjike.goddess.managepromotion.dto.SkillGradingDTO;
import com.bjike.goddess.managepromotion.entity.SkillGrading;
import com.bjike.goddess.managepromotion.to.SkillGradingTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 技能定级业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class SkillGradingSerImpl extends ServiceImpl<SkillGrading, SkillGradingDTO> implements SkillGradingSer {

    @Override
    public Long countSkillGrading(SkillGradingDTO skillGradingDTO) throws SerException {
        Long count = super.count(skillGradingDTO);
        return count;
    }

    @Override
    public SkillGradingBO getOne(String id) throws SerException {
        SkillGrading skillGrading = super.findById(id);
        return BeanTransform.copyProperties(skillGrading,SkillGradingBO.class);
    }

    @Override
    public List<SkillGradingBO> findListSkillGrading(SkillGradingDTO skillGradingDTO) throws SerException {
        skillGradingDTO.getSorts().add("createTime=desc");
        List<SkillGrading> skillGradings = super.findByCis(skillGradingDTO);
        List<SkillGradingBO> skillGradingBOS = BeanTransform.copyProperties(skillGradings,SkillGradingBO.class);
        return skillGradingBOS;
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SkillGradingBO insertSkillGrading(SkillGradingTO skillGradingTO) throws SerException {
        SkillGrading skillGrading = BeanTransform.copyProperties(skillGradingTO,SkillGrading.class,true);
        //职衔补助额度
        Integer quotaJobTitle = skillGrading.getTechnicalRank()*skillGrading.getConvertLine();
        skillGrading.setQuotaJobTitle(quotaJobTitle);
        //补助额度合计
        Integer totalAllowance = skillGrading.getSubsidiesAmount()+quotaJobTitle;
        skillGrading.setTotalAllowance(totalAllowance);
        //每次晋升增长幅度
        SkillGrading skill = findBySql(skillGrading.getSystem(), skillGrading.getIndustry(), skillGrading.getSubject(), skillGrading.getTechnicalRank());
        if (skill != null) {
            skillGrading.setGrowth(skillGrading.getTotalAllowance() - skill.getTotalAllowance());
        } else {
            skillGrading.setGrowth(skillGrading.getTotalAllowance());
        }
        skillGrading.setCreateTime(LocalDateTime.now());
        super.save(skillGrading);
        return BeanTransform.copyProperties(skillGrading,SkillGradingBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public SkillGradingBO editSkillGrading(SkillGradingTO skillGradingTO) throws SerException {
        SkillGrading skillGrading = super.findById(skillGradingTO.getId());
        BeanTransform.copyProperties(skillGradingTO,skillGrading,true);
        //职衔补助额度
        Integer quotaJobTitle = skillGrading.getTechnicalRank()*skillGrading.getConvertLine();
        skillGrading.setQuotaJobTitle(quotaJobTitle);
        //补助额度合计
        Integer totalAllowance = skillGrading.getSubsidiesAmount()+quotaJobTitle;
        skillGrading.setTotalAllowance(totalAllowance);
        //每次晋升增长幅度
        SkillGrading skill = findBySql(skillGrading.getSystem(), skillGrading.getIndustry(), skillGrading.getSubject(), skillGrading.getTechnicalRank());
        if (skill != null) {
            skillGrading.setGrowth(skillGrading.getTotalAllowance() - skill.getTotalAllowance());
        } else {
            skillGrading.setGrowth(skillGrading.getTotalAllowance());
        }
        skillGrading.setModifyTime(LocalDateTime.now());
        super.update(skillGrading);
        return BeanTransform.copyProperties(skillGrading,SkillGradingBO.class);
    }

    @Override
    @Transactional(rollbackFor = SerException.class)
    public void removeSkillGrading(String id) throws SerException {
        super.remove(id);
    }
    private SkillGrading findBySql(String system, String industry, String subject, Integer technicalRank) throws SerException {
        Integer[] technicals = new Integer[]{technicalRank};
        String[] systems = new String[]{system};
        String[] industrys = new String[]{industry};
        String[] subjects = new String[]{subject};
        List<SkillGrading> list = null;
        for (int i = 0; i < technicals.length; i++) {
            String sql = "SELECT max(technicalRank) technicalRank,skillLevel,totalAllowance " +
                    " FROM managepromotion_skillgrading WHERE system='"+systems[i]+"' and industry='"+industrys[i]+"' " +
                    "and subject='"+subjects[i]+"' and technicalRank='"+technicals[i]+"' GROUP BY skillLevel,totalAllowance ";
            String[] fields = new String[]{"technicalRank", "skillLevel", "totalAllowance"};
            list = super.findBySql(sql, SkillGrading.class, fields);
        }
        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }
}