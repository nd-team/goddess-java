package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.RewardIndicatorBO;
import com.bjike.goddess.firmreward.dto.RewardIndicatorDTO;
import com.bjike.goddess.firmreward.entity.RewardIndicator;
import com.bjike.goddess.firmreward.to.RewardIndicatorTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 奖励指标业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "firmrewardSerCache")
@Service
public class RewardIndicatorSerImpl extends ServiceImpl<RewardIndicator, RewardIndicatorDTO> implements RewardIndicatorSer {

    /**
     * 分页查询奖励指标
     *
     * @return class RewardIndicatorBO
     * @throws SerException
     */
    @Override
    public List<RewardIndicatorBO> list(RewardIndicatorDTO dto) throws SerException {
        List<RewardIndicator> list = super.findByPage(dto);
        List<RewardIndicatorBO> listBO = BeanTransform.copyProperties(list, RewardIndicatorBO.class);
        return listBO;
    }

    /**
     * 保存奖励指标
     *
     * @param to 奖励指标to
     * @return class RewardIndicatorBO
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public RewardIndicatorBO save(RewardIndicatorTO to) throws SerException {
        RewardIndicator entity = BeanTransform.copyProperties(to, RewardIndicator.class, true);
        entity = super.save(entity);
        RewardIndicatorBO bo = BeanTransform.copyProperties(entity, RewardIndicatorBO.class);
        return bo;
    }

    /**
     * 根据id删除奖励指标
     *
     * @param id 奖励指标唯一标识
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新奖励指标
     *
     * @param to 奖励指标to
     * @throws SerException
     */
    @Override
    @Transactional(rollbackFor = SerException.class)
    public void update(RewardIndicatorTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RewardIndicator model = super.findById(to.getId());
            if (model != null) {
                updateRewardIndicator(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新奖励指标
     *
     * @param to 奖励指标to
     * @param model 奖励指标
     */
    private void updateRewardIndicator(RewardIndicatorTO to, RewardIndicator model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}