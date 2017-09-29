package com.bjike.goddess.dimission.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 离职管理面谈业务传输对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-28 02:39 ]
 * @Description: [ 离职管理面谈业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class InterviewBO extends BaseBO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 岗位层级
     */
    private String positionLever;

    /**
     * 姓名
     */
    private String name;

    /**
     * 申请离职离职时间
     */
    private String applyTime;

    /**
     * 正式离职时间
     */
    private String dismissTime;

    /**
     * 是否需项目经理信息离职面谈
     */
    private Boolean managerInterview;

    /**
     * 是否需模块负责人离职面谈
     */
    private Boolean principalInterview;

    /**
     * 是否需福利模块离职面谈
     */
    private Boolean welfareInterview;

    /**
     * 是否需要总经办离职面谈
     */
    private Boolean officeInterview;


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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPositionLever() {
        return positionLever;
    }

    public void setPositionLever(String positionLever) {
        this.positionLever = positionLever;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(String applyTime) {
        this.applyTime = applyTime;
    }

    public String getDismissTime() {
        return dismissTime;
    }

    public void setDismissTime(String dismissTime) {
        this.dismissTime = dismissTime;
    }

    public Boolean getManagerInterview() {
        return managerInterview;
    }

    public void setManagerInterview(Boolean managerInterview) {
        this.managerInterview = managerInterview;
    }

    public Boolean getPrincipalInterview() {
        return principalInterview;
    }

    public void setPrincipalInterview(Boolean principalInterview) {
        this.principalInterview = principalInterview;
    }

    public Boolean getWelfareInterview() {
        return welfareInterview;
    }

    public void setWelfareInterview(Boolean welfareInterview) {
        this.welfareInterview = welfareInterview;
    }

    public Boolean getOfficeInterview() {
        return officeInterview;
    }

    public void setOfficeInterview(Boolean officeInterview) {
        this.officeInterview = officeInterview;
    }
}