package com.bjike.goddess.salarymanage.vo;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultSkillPositionVO {
    /**
     * 岗位
     */
    private String position;

    /**
     * 技能
     */
    private String skill;

    /**
     * 技能等级总份额
     */
    private Integer  allSkillRankLot;

    /**
     * 管理等级总份额
     */
    private Integer  allManageRankLot;

    /**
     * 工作年限子集
     */
    private List<ResultWorkAgeVO> resultWorkAge;

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

    public List<ResultWorkAgeVO> getResultWorkAge() {
        return resultWorkAge;
    }

    public void setResultWorkAge(List<ResultWorkAgeVO> resultWorkAge) {
        this.resultWorkAge = resultWorkAge;
    }

    public Integer getAllSkillRankLot() {
        return allSkillRankLot;
    }

    public void setAllSkillRankLot(Integer allSkillRankLot) {
        this.allSkillRankLot = allSkillRankLot;
    }

    public Integer getAllManageRankLot() {
        return allManageRankLot;
    }

    public void setAllManageRankLot(Integer allManageRankLot) {
        this.allManageRankLot = allManageRankLot;
    }
}
