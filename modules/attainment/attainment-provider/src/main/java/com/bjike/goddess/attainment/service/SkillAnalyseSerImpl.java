package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.SkillAnalyseBO;
import com.bjike.goddess.attainment.dto.SkillAnalyseDTO;
import com.bjike.goddess.attainment.entity.SkillAnalyse;
import com.bjike.goddess.attainment.to.SkillAnalyseTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 技能分析表业务实现
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 11:45 ]
 * @Description: [ 技能分析表业务实现 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "attainmentSerCache")
@Service
public class SkillAnalyseSerImpl extends ServiceImpl<SkillAnalyse, SkillAnalyseDTO> implements SkillAnalyseSer {

    @Autowired
    private UserAPI userAPI;

    @Override
    public SkillAnalyseBO save(SkillAnalyseTO to) throws SerException {
        SkillAnalyse entity = BeanTransform.copyProperties(to, SkillAnalyseTO.class, true);
        entity.setWriter(userAPI.currentUser().getUsername());
        entity.setWriterTime(LocalDateTime.now());
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Override
    public SkillAnalyseBO update(SkillAnalyseTO to) throws SerException {
        SkillAnalyse entity = BeanTransform.copyProperties(to, SkillAnalyseTO.class, true), analyse = super.findById(to.getId());
        if (null == analyse)
            throw new SerException("程序错误,请刷新重试");
        entity.setCreateTime(analyse.getCreateTime());
        entity.setModifyTime(LocalDateTime.now());
        entity.setWriter(analyse.getWriter());
        entity.setWriterTime(analyse.getWriterTime());
        UserBO user = userAPI.currentUser();
        if (!user.getUsername().equals(entity.getWriter())) {
            entity.setWriter(user.getUsername());
            entity.setWriterTime(LocalDateTime.now());
        }
        super.update(entity);
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Override
    public SkillAnalyseBO delete(String id) throws SerException {
        SkillAnalyse entity = super.findById(id);
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }
}