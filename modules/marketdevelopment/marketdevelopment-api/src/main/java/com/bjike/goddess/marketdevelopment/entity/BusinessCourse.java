package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.common.api.type.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 业务方向
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-03-22 07:21 ]
 * @Description: [ 业务方向 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_business_course")
public class BusinessCourse extends BaseEntity {

    /**
     * 业务方向编号
     */
    @Column(name = "businessNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向编号'")
    private String businessNum;

    /**
     * 业务方向分类
     */
    @Column(name = "businessType", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向分类'")
    private String businessType;

    /**
     * 科目编号
     */
    @Column(name = "subjectNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目编号'")
    private String subjectNum;

    /**
     * 业务方向科目
     */
    @Column(name = "course", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String course;

    /**
     * 所属类别
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所属类别'")
    private String type;

    /**
     * 可以做的具体业务
     */
    @Column(name = "business", nullable = false, columnDefinition = "TEXT  COMMENT '可以做的具体业务'")
    private String business;

    /**
     * 状态
     */
    @Column(columnDefinition = "TINYINT(2) DEFAULT 0 COMMENT '状态' ", nullable = false)
    private Status status;

    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getSubjectNum() {
        return subjectNum;
    }

    public void setSubjectNum(String subjectNum) {
        this.subjectNum = subjectNum;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}