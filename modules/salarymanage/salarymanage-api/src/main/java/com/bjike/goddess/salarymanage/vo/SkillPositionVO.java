package com.bjike.goddess.salarymanage.vo;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-18 14:57]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SkillPositionVO {

    /**
     * 工作年限
     */
    private String position;

    /**
     * 技能
     */
    private String skill;

    /**
     * 基础子集
     */
    private List<WorkAgeVO> workAge;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public List<WorkAgeVO> getWorkAge() {
        return workAge;
    }

    public void setWorkAge(List<WorkAgeVO> workAge) {
        this.workAge = workAge;
    }
}
