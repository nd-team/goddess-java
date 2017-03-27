package com.bjike.goddess.marketdevelopment.vo;

/**
 * 年计划表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 05:57 ]
 * @Description: [ 年计划表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class YearPlanVO {

    /**
     * id
     */
    private String id;
    /**
     * 年份
     */
    private Integer year;

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
    private Double courseAccounted;

    /**
     * 年任务量
     */
    private Double quota;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public Double getCourseAccounted() {
        return courseAccounted;
    }

    public void setCourseAccounted(Double courseAccounted) {
        this.courseAccounted = courseAccounted;
    }

    public Double getQuota() {
        return quota;
    }

    public void setQuota(Double quota) {
        this.quota = quota;
    }
}