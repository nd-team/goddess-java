package com.bjike.goddess.recruit.vo;

/**
 * 招聘汇总
 *
 * @Author: [chenjunhao]
 * @Date: [2017-07-21 17:29]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CountVO {
    /**
     * 日期
     */
    private String date;
    /**
     * 招聘地区
     */
    private String area;
    /**
     * 招聘部门/项目组
     */
    private String depart;
    /**
     * 招聘岗位
     */
    private String position;
    /**
     * 计划招聘人数
     */
    private Integer planRecruitNo;
    /**
     * 实际日简历筛选量
     */
    private Integer actualSel;
    /**
     * 实际日邀约面试量
     */
    private Integer actualInterview;
    /**
     * 实际日面试量
     */
    private Integer actualFace;
    /**
     * 实际日成功通过面试量
     */
    private Integer actualSuccess;
    /**
     * 录用量
     */
    private Integer employ;
    /**
     * 入职量
     */
    private Integer entry;
    /**
     * 邀约成功率
     */
    private Double success;
    /**
     * 前来面试率
     */
    private Double comeFace;
    /**
     * 录用率
     */
    private Double employLV;
    /**
     * 入职率
     */
    private Double staffLV;

    public Integer getPlanRecruitNo() {
        return planRecruitNo;
    }

    public void setPlanRecruitNo(Integer planRecruitNo) {
        this.planRecruitNo = planRecruitNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Integer getActualSel() {
        return actualSel;
    }

    public void setActualSel(Integer actualSel) {
        this.actualSel = actualSel;
    }

    public Integer getActualInterview() {
        return actualInterview;
    }

    public void setActualInterview(Integer actualInterview) {
        this.actualInterview = actualInterview;
    }

    public Integer getActualFace() {
        return actualFace;
    }

    public void setActualFace(Integer actualFace) {
        this.actualFace = actualFace;
    }

    public Integer getActualSuccess() {
        return actualSuccess;
    }

    public void setActualSuccess(Integer actualSuccess) {
        this.actualSuccess = actualSuccess;
    }

    public Integer getEmploy() {
        return employ;
    }

    public void setEmploy(Integer employ) {
        this.employ = employ;
    }

    public Integer getEntry() {
        return entry;
    }

    public void setEntry(Integer entry) {
        this.entry = entry;
    }

    public Double getSuccess() {
        return success;
    }

    public void setSuccess(Double success) {
        this.success = success;
    }

    public Double getComeFace() {
        return comeFace;
    }

    public void setComeFace(Double comeFace) {
        this.comeFace = comeFace;
    }

    public Double getEmployLV() {
        return employLV;
    }

    public void setEmployLV(Double employLV) {
        this.employLV = employLV;
    }

    public Double getStaffLV() {
        return staffLV;
    }

    public void setStaffLV(Double staffLV) {
        this.staffLV = staffLV;
    }
}
