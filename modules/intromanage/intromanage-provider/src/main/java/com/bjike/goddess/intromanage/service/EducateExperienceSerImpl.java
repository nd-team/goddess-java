package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.EducateExperienceBO;
import com.bjike.goddess.intromanage.dto.EducateExperienceDTO;
import com.bjike.goddess.intromanage.entity.EducateExperience;
import com.bjike.goddess.intromanage.to.EducateExperienceTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 教育经历业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:40 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class EducateExperienceSerImpl extends ServiceImpl<EducateExperience, EducateExperienceDTO> implements EducateExperienceSer {

    /**
     * 分页查询教育经历
     *
     * @return class EducateExperienceBO
     * @throws SerException
     */
    @Override
    public List<EducateExperienceBO> list(EducateExperienceDTO dto) throws SerException {
        List<EducateExperience> list = super.findByPage(dto);
        List<EducateExperienceBO> listBO = BeanTransform.copyProperties(list, EducateExperienceBO.class);
        return listBO;
    }

    /**
     * 保存教育经历
     *
     * @param to 教育经历to
     * @return class EducateExperienceBO
     * @throws SerException
     */
    @Override
    @Transactional
    public EducateExperienceBO save(EducateExperienceTO to) throws SerException {
        EducateExperience entity = BeanTransform.copyProperties(to, EducateExperience.class, true);
        entity = super.save(entity);
        EducateExperienceBO bo = BeanTransform.copyProperties(entity, EducateExperienceBO.class);
        return bo;
    }

    /**
     * 更新教育经历
     *
     * @param to 教育经历to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(EducateExperienceTO to) throws SerException {
        EducateExperience entity = BeanTransform.copyProperties(to, EducateExperience.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除教育经历
     *
     * @param id 教育经历唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}