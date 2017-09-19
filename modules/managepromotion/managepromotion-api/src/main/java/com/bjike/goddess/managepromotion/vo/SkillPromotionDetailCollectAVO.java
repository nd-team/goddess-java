package com.bjike.goddess.managepromotion.vo;

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
public class SkillPromotionDetailCollectAVO {

    /**
     * 周期
     */
    private String cycle;

    /**
     * 人数
     */
    private Integer people;
    private List<SkillPromotionDetailCollectBVO> skillPromotionDetailCollectBBOS;

    public List<SkillPromotionDetailCollectBVO> getSkillPromotionDetailCollectBBOS() {
        return skillPromotionDetailCollectBBOS;
    }

    public void setSkillPromotionDetailCollectBBOS(List<SkillPromotionDetailCollectBVO> skillPromotionDetailCollectBBOS) {
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
