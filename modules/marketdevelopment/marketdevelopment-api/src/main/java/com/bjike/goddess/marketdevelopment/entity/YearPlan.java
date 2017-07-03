package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 年计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 05:57 ]
 * @Description: [ 年计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_year_plan")
public class YearPlan extends BaseEntity {

    /**
     * 年份
     */
    @Range(min = 1990, max = 2999) //取范围
    @Column(name = "year", nullable = false, columnDefinition = "INT(11)  COMMENT '年份'")
    private Integer year;

    /**
     * 业务类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String type;

    /**
     * 工作量权重
     */
    @Column(name = "workloadWeight", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '工作量权重'")
    private Double workloadWeight;

    /**
     * 业务方向科目
     */
    @Column(name = "course", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String course;

    /**
     * 可发展对象
     */
    @Range(min = 0, max = 9999) //取范围
    @Column(name = "development", nullable = false, columnDefinition = "INT(11)   COMMENT '可发展对象'")
    private Integer development;

    /**
     * 同一类业务类型中占比
     */
    @Column(name = "businessAccounted", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '同一类业务类型中占比'")
    private Double businessAccounted;

    /**
     * 各业务科目年度占比
     */
    @Column(name = "courseAccounted", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '各业务科目年度占比'")
    private Double courseAccounted;

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

}