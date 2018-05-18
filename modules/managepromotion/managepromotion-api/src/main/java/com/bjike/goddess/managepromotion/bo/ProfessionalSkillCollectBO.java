package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 各专业技能汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 16:49]
 * @Description: [各专业技能汇总 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class ProfessionalSkillCollectBO extends BaseBO{
    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 技能专业
     */
    private String major;

    /**
     * 技能级别
     */
    private String grade;

    /**
     * 人数
     */
    private Integer people;

    /**
     * 未申请晋升人数
     */
    private Integer promotionPeople;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Integer getPromotionPeople() {
        return promotionPeople;
    }

    public void setPromotionPeople(Integer promotionPeople) {
        this.promotionPeople = promotionPeople;
    }
}
