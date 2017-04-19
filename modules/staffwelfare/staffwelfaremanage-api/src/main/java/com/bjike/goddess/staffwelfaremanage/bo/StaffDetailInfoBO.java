package com.bjike.goddess.staffwelfaremanage.bo;

/**
 * 员工信息明细
 *
 * @Author: [Jason]
 * @Date: [17-4-5 下午2:39]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class StaffDetailInfoBO {

    private Integer birthMonth;

    private String name;

    private String area;

    private String projectGroup;

    private String dimission;

    private String dimissionTime;

    public Integer getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getDimission() {
        return dimission;
    }

    public void setDimission(String dimission) {
        this.dimission = dimission;
    }

    public String getDimissionTime() {
        return dimissionTime;
    }

    public void setDimissionTime(String dimissionTime) {
        this.dimissionTime = dimissionTime;
    }
}
