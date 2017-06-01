package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitPlanBO;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.RecruitPlanDTO;
import com.bjike.goddess.recruit.entity.RecruitPlan;
import com.bjike.goddess.recruit.to.RecruitPlanTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 招聘计划
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service
public class RecruitPlanSerImpl extends ServiceImpl<RecruitPlan, RecruitPlanDTO> implements RecruitPlanSer {

    /**
     * 分页查询计划
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    public List<RecruitPlanBO> list(RecruitPlanDTO dto) throws SerException {
        List<RecruitPlan> list = super.findByPage(dto);
        List<RecruitPlanBO> listBO = BeanTransform.copyProperties(list, RecruitPlanBO.class);
        return listBO;
    }

    /**
     * 保存招聘计划
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public RecruitPlanBO save(RecruitPlanTO to) throws SerException {
        RecruitPlan model = BeanTransform.copyProperties(to, RecruitPlan.class, true);
        RecruitPlan entity = super.save(model);
        RecruitPlanBO bo = BeanTransform.copyProperties(entity, RecruitWayBO.class);
        return bo;
    }

    /**
     * 更新招聘计划
     *
     * @param to 招聘计划to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RecruitPlanTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RecruitPlan model = super.findById(to.getId());
            if (model != null) {
                updateRecruitPlan(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新招聘计划
     *
     * @param to 招聘计划to
     * @param model 招聘计划实体
     */
    private void updateRecruitPlan(RecruitPlanTO to, RecruitPlan model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 根据id删除招聘计划
     *
     * @param id
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
