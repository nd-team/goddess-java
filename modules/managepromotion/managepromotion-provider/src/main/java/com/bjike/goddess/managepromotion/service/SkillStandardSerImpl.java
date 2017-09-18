package com.bjike.goddess.managepromotion.service;

import com.bjike.goddess.common.api.dto.Restrict;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.managepromotion.bo.SkillStandardBO;
import com.bjike.goddess.managepromotion.dto.SkillStandardDTO;
import com.bjike.goddess.managepromotion.entity.SkillStandard;
import com.bjike.goddess.managepromotion.to.GuidePermissionTO;
import com.bjike.goddess.managepromotion.to.SkillStandardTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import scala.util.parsing.combinator.testing.Str;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 技能评定标准业务实现
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-11 12:00 ]
 * @Description: [ 技能评定标准业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "managepromotionSerCache")
@Service
public class SkillStandardSerImpl extends ServiceImpl<SkillStandard, SkillStandardDTO> implements SkillStandardSer {
    @Override
    public Boolean sonPermission() throws SerException {
        return null;
    }

    @Override
    public Boolean guidePermission(GuidePermissionTO guidePermissionTO) throws SerException {
        return null;
    }

    @Override
    public Long count(SkillStandardDTO dto) throws SerException {
        search(dto);
        Long count = super.count(dto);
        return count;
    }

    @Override
    public SkillStandardBO getOne(String id) throws SerException {
        SkillStandard skillStandard = super.findById(id);
        return BeanTransform.copyProperties(skillStandard, SkillStandardBO.class);
    }

    @Override
    public List<SkillStandardBO> list(SkillStandardDTO dto) throws SerException {
        search(dto);
        List<SkillStandard> skillStandards = super.findByCis(dto);
        List<SkillStandardBO> skillStandardBOS = BeanTransform.copyProperties(skillStandards,SkillStandardBO.class);
        return skillStandardBOS;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillStandardBO save(SkillStandardTO to) throws SerException {
        SkillStandard skillStandard =  BeanTransform.copyProperties(to,SkillStandard.class,true);
        skillStandard.setCreateTime(LocalDateTime.now());
        SkillStandardBO bo = BeanTransform.copyProperties(skillStandard,SkillStandardBO.class);
        return bo;
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillStandardBO edit(SkillStandardTO to) throws SerException {
        if(StringUtils.isNotBlank(to.getId())){
            SkillStandard skillStandard = super.findById(to.getId());
            LocalDateTime createTime = skillStandard.getCreateTime();
            skillStandard = BeanTransform.copyProperties(to,SkillStandard.class,true);
            skillStandard.setCreateTime(createTime);
            skillStandard.setModifyTime(LocalDateTime.now());
            SkillStandardBO bo = BeanTransform.copyProperties(skillStandard,SkillStandardBO.class);
            return bo;
        }else {
            throw new SerException("id不能为空");
        }
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public void delete(String id) throws SerException {
        if(StringUtils.isNotBlank(id)){
            super.remove(id);
        }else {
            throw new SerException("id不能为空");
        }
    }
    private void search(SkillStandardDTO dto){
        if(StringUtils.isNotBlank(dto.getMajor())){
            dto.getConditions().add(Restrict.like("major",dto.getMajor()));
        }
    }
}