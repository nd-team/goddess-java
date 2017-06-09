package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitDemandBO;
import com.bjike.goddess.recruit.dto.RecruitDemandDTO;
import com.bjike.goddess.recruit.entity.RecruitDemand;
import com.bjike.goddess.recruit.to.RecruitDemandTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 招聘需求
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:14]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecruitDemandSerImpl extends ServiceImpl<RecruitDemand, RecruitDemandDTO> implements RecruitDemandSer {

    /**
     * 分页查询招聘需求
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<RecruitDemandBO> list(RecruitDemandDTO dto) throws SerException {
        List<RecruitDemand> list = super.findByPage(dto);
        List<RecruitDemandBO> listBO = BeanTransform.copyProperties(list, RecruitDemandBO.class);
        return listBO;
    }

    /**
     * 保存招聘需求
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RecruitDemandBO save(RecruitDemandTO to) throws SerException {
        RecruitDemand failFirstInterviewReason = BeanTransform.copyProperties(to, RecruitDemand.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        RecruitDemandBO bo = BeanTransform.copyProperties(failFirstInterviewReason, RecruitDemandBO.class);
        return bo;
    }

    /**
     * 更新招聘需求
     *
     * @param to 招聘需求to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RecruitDemandTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RecruitDemand model = super.findById(to.getId());
            if (model != null) {
                updateRecruitDemand(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新招聘需求
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateRecruitDemand(RecruitDemandTO to, RecruitDemand model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除招聘需求
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(RecruitDemand entity) throws SerException {
        super.remove(entity);
    }
}
