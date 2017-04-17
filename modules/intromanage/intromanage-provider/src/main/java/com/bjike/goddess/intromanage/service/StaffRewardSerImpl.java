package com.bjike.goddess.intromanage.service;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.common.jpa.service.ServiceImpl;
import com.bjike.goddess.common.utils.bean.BeanTransform;
import com.bjike.goddess.intromanage.bo.StaffRewardBO;
import com.bjike.goddess.intromanage.dto.StaffRewardDTO;
import com.bjike.goddess.intromanage.entity.StaffReward;
import com.bjike.goddess.intromanage.to.StaffRewardTO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工奖励业务实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@CacheConfig(cacheNames = "intromanageSerCache")
@Service
public class StaffRewardSerImpl extends ServiceImpl<StaffReward, StaffRewardDTO> implements StaffRewardSer {

    /**
     * 分页查询员工奖励
     *
     * @return class StaffRewardBO
     * @throws SerException
     */
    @Override
    public List<StaffRewardBO> list(StaffRewardDTO dto) throws SerException {
        List<StaffReward> list = super.findByPage(dto);
        List<StaffRewardBO> listBO = BeanTransform.copyProperties(list, StaffRewardBO.class);
        return listBO;
    }

    /**
     * 保存员工奖励
     *
     * @param to 员工奖励to
     * @return class StaffRewardBO
     * @throws SerException
     */
    @Override
    @Transactional
    public StaffRewardBO save(StaffRewardTO to) throws SerException {
        StaffReward entity = BeanTransform.copyProperties(to, StaffReward.class, true);
        entity = super.save(entity);
        StaffRewardBO bo = BeanTransform.copyProperties(entity, StaffRewardBO.class);
        return bo;
    }

    /**
     * 更新员工奖励
     *
     * @param to 员工奖励to
     * @throws SerException
     */
    @Override
    @Transactional
    public void update(StaffRewardTO to) throws SerException {
        StaffReward entity = BeanTransform.copyProperties(to, StaffReward.class, true);
        entity.setModifyTime(LocalDateTime.now());
        super.update(entity);
    }

    /**
     * 根据id删除员工奖励
     *
     * @param id 员工奖励唯一标识
     * @throws SerException
     */
    @Override
    @Transactional
    public void remove(String id) throws SerException {
        super.remove(id);
    }
}