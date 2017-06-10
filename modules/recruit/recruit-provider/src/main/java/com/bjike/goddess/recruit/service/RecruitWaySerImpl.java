package com.bjike.goddess.recruit.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.recruit.bo.RecruitWayBO;
import com.bjike.goddess.recruit.dto.RecruitWayDTO;
import com.bjike.goddess.recruit.entity.RecruitWay;
import com.bjike.goddess.recruit.to.RecruitWayTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 招聘渠道
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-14 09:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Service
public class RecruitWaySerImpl extends ServiceImpl<RecruitWay, RecruitWayDTO> implements RecruitWaySer {

    /**
     * 分页查询招聘渠道
     *
     * @param dto
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public List<RecruitWayBO> list(RecruitWayDTO dto) throws SerException {
        List<RecruitWay> list = super.findByPage(dto);
        List<RecruitWayBO> listBO = BeanTransform.copyProperties(list, RecruitWayBO.class);
        return listBO;
    }

    /**
     * 保存招聘渠道
     *
     * @param to
     * @return
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public RecruitWayBO save(RecruitWayTO to) throws SerException {
        RecruitWay failFirstInterviewReason = BeanTransform.copyProperties(to, RecruitWay.class, true);
        failFirstInterviewReason = super.save(failFirstInterviewReason);
        RecruitWayBO bo = BeanTransform.copyProperties(failFirstInterviewReason, RecruitWayBO.class);
        return bo;
    }

    /**
     * 更新招聘渠道
     *
     * @param to 招聘渠道to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RecruitWayTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RecruitWay model = super.findById(to.getId());
            if (model != null) {
                updateRecruitWay(to, model);
            } else {
                throw new SerException("更新对象不能为空");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }

    }

    /**
     * 更新招聘渠道
     *
     * @param to
     * @param model
     * @throws SerException
     */
    private void updateRecruitWay(RecruitWayTO to, RecruitWay model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     * 删除招聘渠道
     *
     * @param entity
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = {SerException.class})
    public void remove(RecruitWay entity) throws SerException {
        super.remove(entity);
    }

}
