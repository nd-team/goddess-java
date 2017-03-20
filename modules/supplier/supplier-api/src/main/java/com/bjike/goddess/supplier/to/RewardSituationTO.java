package com.bjike.goddess.supplier.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 获奖情况
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-20T10:53:15.054 ]
 * @Description: [ 获奖情况 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RewardSituationTO extends BaseTO {

    /**
     * 获奖情况
     */
    private String[] reward_ids;

    /**
     * 获奖名称
     */
    private String[] rewardName;

    /**
     * 获得时间
     */
    private String[] acquisition;

    public String[] getRewardName() {
        return rewardName;
    }

    public void setRewardName(String[] rewardName) {
        this.rewardName = rewardName;
    }

    public String[] getAcquisition() {
        return acquisition;
    }

    public void setAcquisition(String[] acquisition) {
        this.acquisition = acquisition;
    }

    public String[] getReward_ids() {
        return reward_ids;
    }

    public void setReward_ids(String[] reward_ids) {
        this.reward_ids = reward_ids;
    }
}