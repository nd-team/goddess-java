package com.bjike.goddess.firmreward.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.firmreward.bo.RewardProgramRatioBO;
import com.bjike.goddess.firmreward.dto.RewardProgramRatioDTO;
import com.bjike.goddess.firmreward.entity.RewardProgramRatio;
import com.bjike.goddess.firmreward.to.RewardProgramRatioTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 奖励项目比例业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:35 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "firmrewardSerCache")
@Service
public class RewardProgramRatioSerImpl extends ServiceImpl<RewardProgramRatio, RewardProgramRatioDTO> implements RewardProgramRatioSer {

    /**
     * 分页查询奖励项目比例
     *
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public List<RewardProgramRatioBO> list(RewardProgramRatioDTO dto) throws SerException {
        List<RewardProgramRatio> list = super.findByPage(dto);
        List<RewardProgramRatioBO> listBO = BeanTransform.copyProperties(list, RewardProgramRatioBO.class);
        return listBO;
    }

    /**
     * 保存奖励项目比例
     *
     * @param to 奖励项目比例to
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public RewardProgramRatioBO save(RewardProgramRatioTO to) throws SerException {
        RewardProgramRatio entity = BeanTransform.copyProperties(to, RewardProgramRatio.class, true);
        entity = super.save(entity);
        RewardProgramRatioBO bo = BeanTransform.copyProperties(entity, RewardProgramRatioBO.class);
        return bo;
    }

    /**
     * 根据id删除奖励项目比例
     *
     * @param id 奖励项目比例唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        super.remove(id);
    }

    /**
     * 更新奖励项目比例
     *
     * @param to 奖励项目比例to
     * @throws SerException
     */
    @Override
    public void update(RewardProgramRatioTO to) throws SerException {
        if (StringUtils.isNotEmpty(to.getId())) {
            RewardProgramRatio model = super.findById(to.getId());
            if (model != null) {
                updateRewardProgramRatio(to, model);
            } else {
                throw new SerException("更新对象不能为空!");
            }
        } else {
            throw new SerException("更新ID不能为空!");
        }
    }

    /**
     * 更新奖励项目比例
     *
     * @param to 奖励项目比例to
     * @param model 奖励项目比例
     * @throws SerException
     */
    private void updateRewardProgramRatio(RewardProgramRatioTO to, RewardProgramRatio model) throws SerException {
        BeanTransform.copyProperties(to, model, true);
        model.setModifyTime(LocalDateTime.now());
        super.update(model);
    }
}