package com.bjike.goddess.managepromotion.vo;

/**
 * 技能定级B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillGradingBVO {

    /**
     * 技能等级
     */
    private String skillLevel;
    /**
     * 转正后间隔时间
     */
    private Integer intervalAfterTransfer;

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getIntervalAfterTransfer() {
        return intervalAfterTransfer;
    }

    public void setIntervalAfterTransfer(Integer intervalAfterTransfer) {
        this.intervalAfterTransfer = intervalAfterTransfer;
    }
}