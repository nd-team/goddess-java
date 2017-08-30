package com.bjike.goddess.intromanage.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;


/**
 * 员工奖励
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:26 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "intromanage_staffreward")
public class StaffReward extends BaseEntity {

    /**
     * 员工id
     */
    @Column(name = "staffId", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '员工id'")
    private String staffId;

    /**
     * 奖励名称
     */
    @Column(name = "rewardsName", columnDefinition = "VARCHAR(255) COMMENT '奖励名称'")
    private String rewardsName;

    /**
     * 奖品
     */
    @Column(name = "prize", columnDefinition = "VARCHAR(255) COMMENT '奖品'")
    private String prize;

    /**
     * 奖金
     */
    @Column(name = "bonus", columnDefinition = "VARCHAR(255) COMMENT '奖金'")
    private String bonus;


    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

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