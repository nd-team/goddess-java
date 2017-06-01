package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.EducateExperienceBO;
import com.bjike.goddess.intromanage.dto.EducateExperienceDTO;
import com.bjike.goddess.intromanage.entity.EducateExperience;
import com.bjike.goddess.intromanage.service.EducateExperienceSer;
import com.bjike.goddess.intromanage.to.EducateExperienceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 教育经历业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:40 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("educateExperienceApiImpl")
public class EducateExperienceApiImpl implements EducateExperienceAPI {

    @Autowired
    private EducateExperienceSer educateExperienceSer;

    /**
     * 根据id查询教育经历
     *
     * @param id 教育经历唯一标识
     * @return class EducateExperienceBO
     * @throws SerException
     */
    @Override
    public EducateExperienceBO findById(String id) throws SerException {
        EducateExperience model = educateExperienceSer.findById(id);
        return BeanTransform.copyProperties(model, EducateExperienceBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 教育经历dto
     * @throws SerException
     */
    @Override
    public Long count(EducateExperienceDTO dto) throws SerException {
        return educateExperienceSer.count(dto);
    }

    /**
     * 分页查询教育经历
     *
     * @return class EducateExperienceBO
     * @throws SerException
     */
    @Override
    public List<EducateExperienceBO> list(EducateExperienceDTO dto) throws SerException {
        return educateExperienceSer.list(dto);
    }

    /**
     * 保存教育经历
     *
     * @param to 教育经历to
     * @return class EducateExperienceBO
     * @throws SerException
     */
    @Override
    public EducateExperienceBO save(EducateExperienceTO to) throws SerException {
        return educateExperienceSer.save(to);
    }

    /**
     * 根据id删除教育经历
     *
     * @param id 教育经历唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        educateExperienceSer.remove(id);
    }

    /**
     * 更新教育经历
     *
     * @param to 教育经历to
     * @throws SerException
     */
    @Override
    public void update(EducateExperienceTO to) throws SerException {
        educateExperienceSer.update(to);
    }
}