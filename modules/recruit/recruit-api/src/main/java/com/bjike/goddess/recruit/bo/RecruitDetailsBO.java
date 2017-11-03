package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 招聘管理详情汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 招聘管理详情汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecruitDetailsBO extends BaseBO {
    /**
     * 招聘地区
     */
    private String area;
    /**
     * 招聘部门/项目组
     */
    private String projectGroup;
    /**
     * 招聘岗位
     */
    private String position;
    /**
     * 计划招聘人数
     */
    private Integer planInterviewNum;

    /**
     * 成功入职数
     */
    private Integer successEntryNum;

    /**
     * 还需招聘人数
     */
    private Integer hireNum;

    /**
     * 计划筛选简历数
     */
    private Integer planScreenNum;

    /**
     * 实际筛选简历数
     */
    private Integer realityScreenNum;
    /**
     * 下载简历数差异
     */
    private Integer downloadBalance;
    /**
     * 简历筛选数量
     */
    private Integer resumeNum;
    /**
     * 电访数量
     */
    private Integer phoneNum;

    /**
     * 预约面试量
     */
    private Integer orderNum;

    /**
     * 实际成功通过面试量
     */
    private Integer successPassNum;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getPlanInterviewNum() {
        return planInterviewNum;
    }

    public void setPlanInterviewNum(Integer planInterviewNum) {
        this.planInterviewNum = planInterviewNum;
    }

    public Integer getSuccessEntryNum() {
        return successEntryNum;
    }

    public void setSuccessEntryNum(Integer successEntryNum) {
        this.successEntryNum = successEntryNum;
    }

    public Integer getHireNum() {
        return hireNum;
    }

    public void setHireNum(Integer hireNum) {
        this.hireNum = hireNum;
    }

    public Integer getPlanScreenNum() {
        return planScreenNum;
    }

    public void setPlanScreenNum(Integer planScreenNum) {
        this.planScreenNum = planScreenNum;
    }

    public Integer getRealityScreenNum() {
        return realityScreenNum;
    }

    public void setRealityScreenNum(Integer realityScreenNum) {
        this.realityScreenNum = realityScreenNum;
    }

    public Integer getDownloadBalance() {
        return downloadBalance;
    }

    public void setDownloadBalance(Integer downloadBalance) {
        this.downloadBalance = downloadBalance;
    }

    public Integer getResumeNum() {
        return resumeNum;
    }

    public void setResumeNum(Integer resumeNum) {
        this.resumeNum = resumeNum;
    }

    public Integer getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Integer phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getSuccessPassNum() {
        return successPassNum;
    }

    public void setSuccessPassNum(Integer successPassNum) {
        this.successPassNum = successPassNum;
    }
}