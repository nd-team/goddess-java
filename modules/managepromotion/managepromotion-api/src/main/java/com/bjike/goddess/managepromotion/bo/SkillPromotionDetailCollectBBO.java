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
public class SkillPromotionDetailCollectBBO extends BaseBO {

    /**
     * 项目组/部门
     */
    private String department;


    /**
     * 项目组/部门合计
     */
    private Integer departmentTotal;
    /**
     * 增加总成本
     */
    private Integer totalCost;
    List<SkillPromotionDetailCollectCBO> skillPromotionDetailCollectCBOS;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<SkillPromotionDetailCollectCBO> getSkillPromotionDetailCollectCBOS() {
        return skillPromotionDetailCollectCBOS;
    }

    public void setSkillPromotionDetailCollectCBOS(List<SkillPromotionDetailCollectCBO> skillPromotionDetailCollectCBOS) {
        this.skillPromotionDetailCollectCBOS = skillPromotionDetailCollectCBOS;
    }

    public Integer getDepartmentTotal() {
        return departmentTotal;
    }

    public void setDepartmentTotal(Integer departmentTotal) {
        this.departmentTotal = departmentTotal;
    }

    public Integer getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Integer totalCost) {
        this.totalCost = totalCost;
    }
}