package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.marketdevelopment.enums.Status;

/**
 * 月计划的业务科目表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-12-06 05:04 ]
 * @Description: [ 月计划的业务科目表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthSubjectVO {

    /**
     * id
     */
    private String id;

    /**
     * 业务方向科目
     */
    private String subject;

    /**
     * 业务方向科目id
     */
    private String subjectDataId;

    /**
     * 各业务科目同一业务类型中占比(%)
     */
    private Double proportion;

    /**
     * 各业务科目年度占比(%)
     */
    private Double yearProportion;

    /**
     * 现有业务可发展对象
     */
    private Integer deveBusiness;

    /**
     * 各科目月度计划总任务量
     */
    private Integer planTotal;

    /**
     * 各科目月度实际总任务量
     */
    private Integer actualTotal;

    /**
     * 各科目月度差异总任务量
     */
    private Integer diffTotal;

    /**
     * 状态
     */
    private Status status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectDataId() {
        return subjectDataId;
    }

    public void setSubjectDataId(String subjectDataId) {
        this.subjectDataId = subjectDataId;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Double getYearProportion() {
        return yearProportion;
    }

    public void setYearProportion(Double yearProportion) {
        this.yearProportion = yearProportion;
    }

    public Integer getDeveBusiness() {
        return deveBusiness;
    }

    public void setDeveBusiness(Integer deveBusiness) {
        this.deveBusiness = deveBusiness;
    }

    public Integer getPlanTotal() {
        return planTotal;
    }

    public void setPlanTotal(Integer planTotal) {
        this.planTotal = planTotal;
    }

    public Integer getActualTotal() {
        return actualTotal;
    }

    public void setActualTotal(Integer actualTotal) {
        this.actualTotal = actualTotal;
    }

    public Integer getDiffTotal() {
        return diffTotal;
    }

    public void setDiffTotal(Integer diffTotal) {
        this.diffTotal = diffTotal;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}