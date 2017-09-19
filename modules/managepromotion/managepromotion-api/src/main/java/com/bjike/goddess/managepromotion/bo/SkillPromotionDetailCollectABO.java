package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.util.List;

/**
 * 技能晋升明细汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 14:24]
 * @Description: [ 技能晋升明细汇总]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class SkillPromotionDetailCollectABO extends BaseBO {

    /**
     * 周期
     */
    private String cycle;

    /**
     * 人数
     */
    private Integer people;
    private List<SkillPromotionDetailCollectBBO> skillPromotionDetailCollectBBOS;

    public List<SkillPromotionDetailCollectBBO> getSkillPromotionDetailCollectBBOS() {
        return skillPromotionDetailCollectBBOS;
    }

    public void setSkillPromotionDetailCollectBBOS(List<SkillPromotionDetailCollectBBO> skillPromotionDetailCollectBBOS) {
        this.skillPromotionDetailCollectBBOS = skillPromotionDetailCollectBBOS;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }
}
