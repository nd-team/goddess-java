package com.bjike.goddess.intromanage.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.WorkExperienceBO;
import com.bjike.goddess.intromanage.dto.WorkExperienceDTO;
import com.bjike.goddess.intromanage.entity.WorkExperience;
import com.bjike.goddess.intromanage.service.WorkExperienceSer;
import com.bjike.goddess.intromanage.to.WorkExperienceTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作经历业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:47 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("workExperienceApiImpl")
public class WorkExperienceApiImpl implements WorkExperienceAPI {

    @Autowired
    private WorkExperienceSer workExperienceSer;

    /**
     * 根据id查询工作经历
     *
     * @param id 工作经历唯一标识
     * @return class WorkExperienceBO
     * @throws SerException
     */
    @Override
    public WorkExperienceBO findById(String id) throws SerException {
        WorkExperience model = workExperienceSer.findById(id);
        return BeanTransform.copyProperties(model, WorkExperienceBO.class);
    }

    /**
     * 计算总条数
     *
     * @param dto 工作经历dto
     * @throws SerException
     */
    @Override
    public Long count(WorkExperienceDTO dto) throws SerException {
        return workExperienceSer.count(dto);
    }

    /**
     * 分页查询工作经历
     *
     * @return class WorkExperienceBO
     * @throws SerException
     */
    @Override
    public List<WorkExperienceBO> list(WorkExperienceDTO dto) throws SerException {
        return workExperienceSer.list(dto);
    }

    /**
     * 保存工作经历
     *
     * @param to 工作经历to
     * @return class WorkExperienceBO
     * @throws SerException
     */
    @Override
    public WorkExperienceBO save(WorkExperienceTO to) throws SerException {
        return workExperienceSer.save(to);
    }

    /**
     * 根据id删除工作经历
     *
     * @param id 工作经历唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        workExperienceSer.remove(id);
    }

    /**
     * 更新工作经历
     *
     * @param to 工作经历to
     * @throws SerException
     */
    @Override
    public void update(WorkExperienceTO to) throws SerException {
        workExperienceSer.update(to);
    }
}