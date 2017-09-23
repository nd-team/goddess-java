package com.bjike.goddess.salarymanage.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;
import java.util.Set;

/**
 * @Author: [jiangzaixuan]
 * @Date: [2017-09-19 14:04]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ResultSkillPositionBO extends BaseBO{

    /**
     * 技能
     */
    private String skill;

    /**
     * 岗位
     */
    private String position;

    /**
     * 总区间
     */
    private Integer  totalInterval;

    /**
     * 技能等级总份额
     */
    private Integer  allSkillRankLot;

    /**
     * 管理等级总份额
     */
    private Integer  allManageRankLot;

    /**
     * 工作年限
     */

    private List<ResultWorkAgeBO> resultWorkAge;

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

    public List<ResultWorkAgeBO> getResultWorkAge() {
        return resultWorkAge;
    }

    public void setResultWorkAge(List<ResultWorkAgeBO> resultWorkAge) {
        this.resultWorkAge = resultWorkAge;
    }

    public Integer getTotalInterval() {
        return totalInterval;
    }

    public void setTotalInterval(Integer totalInterval) {
        this.totalInterval = totalInterval;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ResultSkillPositionBO){
            ResultSkillPositionBO bo=(ResultSkillPositionBO) obj;
            if (this.hashCode()==bo.hashCode()){
                if (this.skill.equals(bo.getSkill()) && this.position.equals(bo.getPosition())){
                    return true;
                }
            }
        }
        return false;
    }
}
