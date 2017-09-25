package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 14:16]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SkillPositionBO extends BaseBO{

    /**
     * 技能
     */
    private String skill;

    /**
     * 岗位
     */
    private String position;

    /**
     * 工作年限子集
     */
    private Set<WorkAgeBO> workAge;

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Set<WorkAgeBO> getWorkAge() {
        return workAge;
    }

    public void setWorkAge(Set<WorkAgeBO> workAge) {
        this.workAge = workAge;
    }
}
