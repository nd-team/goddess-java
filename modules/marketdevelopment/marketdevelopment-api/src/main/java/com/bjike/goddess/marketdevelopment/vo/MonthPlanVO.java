package com.bjike.goddess.marketdevelopment.vo;

import com.bjike.goddess.marketdevelopment.enums.MonthType;

/**
 * 月计划表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 06:41 ]
 * @Description: [ 月计划表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MonthPlanVO {

    /**
     * id
     */
    private String id;
    /**
     * 年计划ID
     */
    private String yearId;

    /**
     * 年
     */
    private Integer yearNumber;

    /**
     * 业务类型
     */
    private String type;

    /**
     * 工作量权重
     */
    private Double workloadWeight;

    /**
     * 业务方向科目
     */
    private String course;

    /**
     * 可发展对象
     */
    private Integer development;

    /**
     * 同一类业务类型中占比
     */
    private Double businessAccounted;

    /**
     * 各业务科目年度占比
     */
    private Double yearCourseAccounted;

    /**
     * 月份
     */
    private MonthType month;

    /**
     * 各科目年度任务量
     */
    private Double quota;

    /**
     * 年度占比
     */
    private Double accounted;

    /**
     * 业务方向科目中占比
     */
    private Double courseAccounted;

    /**
     * 计划最小任务量
     */
    private Double leastQuota;

    /**
     * 总计
     */
    private Double total;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public Integer getYearNumber() {
        return yearNumber;
    }

    public void setYearNumber(Integer yearNumber) {
        this.yearNumber = yearNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getWorkloadWeight() {
        return workloadWeight;
    }

    public void setWorkloadWeight(Double workloadWeight) {
        this.workloadWeight = workloadWeight;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Integer getDevelopment() {
        return development;
    }

    public void setDevelopment(Integer development) {
        this.development = development;
    }

    public Double getBusinessAccounted() {
        return businessAccounted;
    }

    public void setBusinessAccounted(Double businessAccounted) {
        this.businessAccounted = businessAccounted;
    }

    public Double getYearCourseAccounted() {
        return yearCourseAccounted;
    }

    public void setYearCourseAccounted(Double yearCourseAccounted) {
        this.yearCourseAccounted = yearCourseAccounted;
    }

    public MonthType getMonth() {
        return month;
    }

    public void setMonth(MonthType month) {
        this.month = month;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public Double getAccounted() {
        return accounted;
    }

    public void setAccounted(Double accounted) {
        this.accounted = accounted;
    }

    public Double getCourseAccounted() {
        return courseAccounted;
    }

    public void setCourseAccounted(Double courseAccounted) {
        this.courseAccounted = courseAccounted;
    }

    public Double getLeastQuota() {
        return leastQuota;
    }

    public void setLeastQuota(Double leastQuota) {
        this.leastQuota = leastQuota;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}