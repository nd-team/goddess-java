package com.bjike.goddess.contractcommunicat.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.contractcommunicat.bo.HistoryAppraiseBO;
import com.bjike.goddess.contractcommunicat.bo.SkillLibraryBO;
import com.bjike.goddess.contractcommunicat.dto.SkillLibraryDTO;
import com.bjike.goddess.contractcommunicat.entity.SkillLibrary;
import com.bjike.goddess.contractcommunicat.to.GuidePermissionTO;
import com.bjike.goddess.contractcommunicat.to.SkillLibraryTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 谈判技巧库业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-25 04:55 ]
 * @Description: [ 谈判技巧库业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "contractcommunicatSerCache")
@Service
public class SkillLibrarySerImpl extends ServiceImpl<SkillLibrary, SkillLibraryDTO> implements SkillLibrarySer {
    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    @Override
    public Long count(SkillLibraryDTO dto) throws SerException {
        Long count = super.count(dto);
        return count;
    }

    @Override
    public SkillLibraryBO getOne(String id) throws SerException {
        SkillLibrary skillLibrary = super.findById(id);
        SkillLibraryBO skillLibraryBO = BeanTransform.copyProperties(skillLibrary, SkillLibraryBO.class);
        return skillLibraryBO;
    }

    @Override
    public List<SkillLibraryBO> list(SkillLibraryDTO dto) throws SerException {
        if (StringUtils.isNotBlank(dto.getPhase())) {
            dto.getConditions().add(Restrict.eq("phase", dto.getPhase()));
        }
        if (StringUtils.isNotBlank(dto.getSceneType())) {
            dto.getConditions().add(Restrict.eq("sceneType", dto.getSceneType()));
        }
        if (StringUtils.isNotBlank(dto.getSkills())) {
            dto.getConditions().add(Restrict.eq("skills", dto.getSkills()));
        }
        if (StringUtils.isNotBlank(dto.getStrategy())) {
            dto.getConditions().add(Restrict.eq("strategy", dto.getStrategy()));
        }
        if (StringUtils.isNotBlank(dto.getSource())) {
            dto.getConditions().add(Restrict.eq("source", dto.getSource()));
        }
        List<SkillLibrary> skillLibraries = super.findByCis(dto);
        List<SkillLibraryBO> skillLibraryBOS = BeanTransform.copyProperties(skillLibraries, SkillLibraryBO.class);
        return skillLibraryBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillLibraryBO insert(SkillLibraryTO to) throws SerException {
        SkillLibrary skillLibrary = BeanTransform.copyProperties(to, SkillLibrary.class, true);
        skillLibrary.setCreateTime(LocalDateTime.now());
        super.save(skillLibrary);
        SkillLibraryBO skillLibraryBO = BeanTransform.copyProperties(skillLibrary, SkillLibraryBO.class);
        return skillLibraryBO;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillLibraryBO edit(SkillLibraryTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            SkillLibrary skillLibrary = super.findById(to.getId());
            LocalDateTime createTime = skillLibrary.getCreateTime();
            skillLibrary = BeanTransform.copyProperties(to, SkillLibrary.class, true);
            skillLibrary.setCreateTime(createTime);
            skillLibrary.setModifyTime(LocalDateTime.now());
            super.update(skillLibrary);
            SkillLibraryBO skillLibraryBO = BeanTransform.copyProperties(skillLibrary, SkillLibraryBO.class);
            return skillLibraryBO;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void remove(String id) throws SerException {
        if (StringUtils.isNotBlank(id)) {
            super.remove(id);
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public SkillLibraryBO appraise(SkillLibraryTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            SkillLibrary skillLibrary = super.findById(to.getId());
            LocalDateTime createTime = skillLibrary.getCreateTime();
            skillLibrary = BeanTransform.copyProperties(to, SkillLibrary.class, true);
            skillLibrary.setCreateTime(createTime);
            skillLibrary.setModifyTime(LocalDateTime.now());
            super.update(skillLibrary);

            SkillLibraryBO skillLibraryBO = BeanTransform.copyProperties(skillLibrary, SkillLibraryBO.class);
            return skillLibraryBO;
        } else {
            throw new SerException("id不能为空");
        }
    }

    @Override
    public List<HistoryAppraiseBO> historyAppraise(String id) throws SerException {
        List<HistoryAppraiseBO> historyAppraiseBOS = new ArrayList<>();
        if (null == id) {
            throw new SerException("id不能为空");
        }
        SkillLibraryDTO dto = new SkillLibraryDTO();
        dto.getConditions().add(Restrict.eq("id", id));
        List<SkillLibrary> skillLibraries = super.findByCis(dto);
        for (SkillLibrary skillLibrary : skillLibraries) {
            HistoryAppraiseBO bo = new HistoryAppraiseBO();
            bo.setHistoryAppraise(skillLibrary.getAppraise());
            historyAppraiseBOS.add(bo);
        }
        return historyAppraiseBOS;

    }
}