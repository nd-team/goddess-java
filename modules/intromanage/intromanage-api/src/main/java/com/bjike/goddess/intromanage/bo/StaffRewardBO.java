package com.bjike.goddess.intromanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 员工奖励业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffRewardBO extends BaseBO {

    /**
     * 员工id
     */
    private String staffId;

    /**
     * 奖励名称
     */
    private String rewardsName;

    /**
     * 奖品
     */
    private String prize;

    /**
     * 奖金
     */
    private String bonus;



    public String getRewardsName() {
        return rewardsName;
    }

    public void setRewardsName(String rewardsName) {
        this.rewardsName = rewardsName;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }
}