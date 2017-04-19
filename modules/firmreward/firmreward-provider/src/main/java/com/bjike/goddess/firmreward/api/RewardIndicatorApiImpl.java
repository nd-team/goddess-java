package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.firmreward.bo.RewardIndicatorBO;
import com.bjike.goddess.firmreward.dto.RewardIndicatorDTO;
import com.bjike.goddess.firmreward.service.RewardIndicatorSer;
import com.bjike.goddess.firmreward.to.RewardIndicatorTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 奖励指标业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:56 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("rewardIndicatorApiImpl")
public class RewardIndicatorApiImpl implements RewardIndicatorAPI {

    @Autowired
    private RewardIndicatorSer rewardIndicatorSer;

    /**
     * 分页查询奖励指标
     *
     * @return class RewardIndicatorBO
     * @throws SerException
     */
    @Override
    public List<RewardIndicatorBO> list(RewardIndicatorDTO dto) throws SerException {
        return rewardIndicatorSer.list(dto);
    }

    /**
     * 保存奖励指标
     *
     * @param to 奖励指标to
     * @return class RewardIndicatorBO
     * @throws SerException
     */
    @Override
    public RewardIndicatorBO save(RewardIndicatorTO to) throws SerException {
        return rewardIndicatorSer.save(to);
    }

    /**
     * 根据id删除奖励指标
     *
     * @param id 奖励指标唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        rewardIndicatorSer.remove(id);
    }

    /**
     * 更新奖励指标
     *
     * @param to 奖励指标to
     * @throws SerException
     */
    @Override
    public void update(RewardIndicatorTO to) throws SerException {
        rewardIndicatorSer.update(to);
    }
}