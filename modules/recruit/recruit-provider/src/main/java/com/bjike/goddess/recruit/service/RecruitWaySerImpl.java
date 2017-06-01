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
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
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
    @Transactional(rollbackFor = SerException.class)
    public RecruitWayBO save(RecruitWayTO to) throws SerException {
        RecruitWay model = BeanTransform.copyProperties(to, RecruitWay.class, true);
        RecruitWay entity = super.save(model);
        RecruitWayBO bo = BeanTransform.copyProperties(entity, RecruitWayBO.class);
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
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新招聘渠道
     *
     * @param to    招聘渠道to
     * @param model 招聘渠道实体
     */
    private void updateRecruitWay(RecruitWayTO to, RecruitWay model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }

    /**
     *根据id删除招聘渠道
     *
     * @param id 招聘渠道唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}
