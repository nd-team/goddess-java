package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 技能定级B
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-05-23 10:22 ]
 * @Description: [ 技能定级B ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SkillGradingBBO extends BaseBO {

    /**
     * 技能等级
     */
    private String skillLevel;

    /**
     * 档次
     */
    private Integer grade;
    /**
     * 技能定级C
     */
    private List<SkillGradingCBO> skillGradingCBOS;

    public String getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public List<SkillGradingCBO> getSkillGradingCBOS() {
        return skillGradingCBOS;
    }

    public void setSkillGradingCBOS(List<SkillGradingCBO> skillGradingCBOS) {
        this.skillGradingCBOS = skillGradingCBOS;
    }
}