package com.bjike.goddess.managepromotion.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 人员技能&晋升汇总
 *
 * @Author: [xiazhili]
 * @Date: [2017-09-12 18:18]
 * @Description: [ 人员技能&晋升汇总]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StaffSkillCollectBO extends BaseBO{
    /**
     * 地区
     */
    private String area;

    /**
     * 部门/项目组
     */
    private String department;

    /**
     * 姓名
     */
    private String name;

    /**
     * 技能数
     */
    private Integer skillNum;

    /**
     * 已晋升次数
     */
    private Integer promotedNumber;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSkillNum() {
        return skillNum;
    }

    public void setSkillNum(Integer skillNum) {
        this.skillNum = skillNum;
    }

    public Integer getPromotedNumber() {
        return promotedNumber;
    }

    public void setPromotedNumber(Integer promotedNumber) {
        this.promotedNumber = promotedNumber;
    }
}
