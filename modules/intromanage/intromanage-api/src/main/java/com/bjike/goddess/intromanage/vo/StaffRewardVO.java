package com.bjike.goddess.intromanage.vo;

/**
 * 员工奖励表现层对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-03-28 10:26 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffRewardVO {

    /**
     * id
     */
    private String id;

    /**
     * 员工编号
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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