package com.bjike.goddess.attainment.service;

import com.bjike.goddess.attainment.bo.AttainmentTypeBO;
import com.bjike.goddess.attainment.bo.SkillAnalyseBO;
import com.bjike.goddess.attainment.dto.SkillAnalyseDTO;
import com.bjike.goddess.attainment.entity.SkillAnalyse;
import com.bjike.goddess.attainment.to.SkillAnalyseTO;
import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.user.api.UserAPI;
import com.bjike.goddess.user.bo.UserBO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillAnalyseBO save(SkillAnalyseTO to) throws SerException {
        UserBO user = userAPI.currentUser();
        SkillAnalyse entity = BeanTransform.copyProperties(to, SkillAnalyse.class, true);
        entity.setWriter(user.getUsername());
        entity.setWriterTime(LocalDateTime.now());
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillAnalyseBO update(SkillAnalyseTO to) throws SerException {
        if (StringUtils.isNotBlank(to.getId())) {
            try {
                UserBO user = userAPI.currentUser();
                SkillAnalyse entity = super.findById(to.getId());
                BeanTransform.copyProperties(to, entity, true);
                entity.setModifyTime(LocalDateTime.now());
                if (!user.getUsername().equals(entity.getWriter())) {
                    entity.setWriter(user.getUsername());
                    entity.setWriterTime(LocalDateTime.now());
                }
                super.update(entity);
                return BeanTransform.copyProperties(entity, AttainmentTypeBO.class);
            } catch (SerException e) {
                throw new SerException("数据对象不能为空");
            }

        } else
            throw new SerException("数据ID不能为空");
    }

    @Transactional(rollbackFor = SerException.class)
    @Override
    public SkillAnalyseBO delete(String id) throws SerException {
        SkillAnalyse entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        super.remove(entity);
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Override
    public List<SkillAnalyseBO> maps(SkillAnalyseDTO dto) throws SerException {
        dto.getSorts().add("writerTime=desc");
        return BeanTransform.copyProperties(super.findByPage(dto), SkillAnalyseBO.class);
    }

    @Override
    public SkillAnalyseBO getById(String id) throws SerException {
        SkillAnalyse entity = super.findById(id);
        if (null == entity)
            throw new SerException("数据不存在");
        return BeanTransform.copyProperties(entity, SkillAnalyseBO.class);
    }

    @Override
    public Long getTotal() throws SerException {
        SkillAnalyseDTO dto = new SkillAnalyseDTO();
        return super.count(dto);
    }
}