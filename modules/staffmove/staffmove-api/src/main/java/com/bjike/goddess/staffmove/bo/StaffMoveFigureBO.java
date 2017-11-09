package com.bjike.goddess.staffmove.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 人员调动实施业务传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-11-03 02:39 ]
 * @Description: [ 人员调动实施业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StaffMoveFigureBO extends BaseBO {


    /**
     * 项目组/部门
     */
    private String projectGroup;
    /**
     * 被调动人数
     */
    private Integer mobilizedNum;

    /**
     * 项目需求调动次数
     */
    private Integer projectMobilizedNum;

    /**
     * 公司发展需求调动次数
     */
    private Integer companyMobilizedNum;

    /**
     * 人员需求调动次数
     */
    private Integer staffMobilizedNum;

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Integer getMobilizedNum() {
        return mobilizedNum;
    }

    public void setMobilizedNum(Integer mobilizedNum) {
        this.mobilizedNum = mobilizedNum;
    }

    public Integer getProjectMobilizedNum() {
        return projectMobilizedNum;
    }

    public void setProjectMobilizedNum(Integer projectMobilizedNum) {
        this.projectMobilizedNum = projectMobilizedNum;
    }

    public Integer getCompanyMobilizedNum() {
        return companyMobilizedNum;
    }

    public void setCompanyMobilizedNum(Integer companyMobilizedNum) {
        this.companyMobilizedNum = companyMobilizedNum;
    }

    public Integer getStaffMobilizedNum() {
        return staffMobilizedNum;
    }

    public void setStaffMobilizedNum(Integer staffMobilizedNum) {
        this.staffMobilizedNum = staffMobilizedNum;
    }
}