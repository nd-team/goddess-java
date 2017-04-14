package com.bjike.goddess.firmreward.api;

import com.bjike.goddess.common.api.exception.SerException;
import com.bjike.goddess.firmreward.bo.AwardDetailBO;
import com.bjike.goddess.firmreward.bo.RewardPeopleNoStatBO;
import com.bjike.goddess.firmreward.dto.RewardPeopleNoStatDTO;
import com.bjike.goddess.firmreward.service.RewardPeopleNoStatSer;
import com.bjike.goddess.firmreward.to.RewardPeopleNoStatTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 奖励人数统计业务接口实现
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-12 05:45 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Service("rewardPeopleNoStatApiImpl")
public class RewardPeopleNoStatApiImpl implements RewardPeopleNoStatAPI {

    @Autowired
    private RewardPeopleNoStatSer rewardPeopleNoStatSer;

    /**
     * 分页查询奖励人数统计
     *
     * @return class RewardPeopleNoStatBO
     * @throws SerException
     */
    @Override
    public List<RewardPeopleNoStatBO> list(RewardPeopleNoStatDTO dto) throws SerException {
        return rewardPeopleNoStatSer.list(dto);
    }

    /**
     * 保存奖励人数统计
     *
     * @param to 奖励人数统计to
     * @return class RewardPeopleNoStatBO
     * @throws SerException
     */
    @Override
    public RewardPeopleNoStatBO save(RewardPeopleNoStatTO to) throws SerException {
        return rewardPeopleNoStatSer.save(to);
    }

    /**
     * 根据id删除奖励人数统计
     *
     * @param id 奖励人数统计唯一标识
     * @throws SerException
     */
    @Override
    public void remove(String id) throws SerException {
        rewardPeopleNoStatSer.remove(id);
    }

    /**
     * 更新奖励人数统计
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    @Override
    public void update(RewardPeopleNoStatTO to) throws SerException {
        rewardPeopleNoStatSer.update(to);
    }

    /**
     * 添加获奖明细
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    @Override
    public void addAwardDetails(RewardPeopleNoStatTO to) throws SerException {
        rewardPeopleNoStatSer.addAwardDetails(to);
    }

    /**
     * 更新获奖明细
     *
     * @param to 奖励人数统计to
     * @throws SerException
     */
    @Override
    public void updateAwardDetails(RewardPeopleNoStatTO to) throws SerException {
        rewardPeopleNoStatSer.updateAwardDetails(to);
    }

    /**
     * 查看获奖明细
     *
     * @param statId 奖励人数统计id
     * @return class RewardProgramRatioBO
     * @throws SerException
     */
    @Override
    public List<AwardDetailBO> checkAwardDetails(String statId) throws SerException {
        return rewardPeopleNoStatSer.checkAwardDetails(statId);
    }
}