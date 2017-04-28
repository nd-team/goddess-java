package com.bjike.goddess.marketdevelopment.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 确定目标信息
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-22 07:12 ]
 * @Description: [ 确定目标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "marketdevelopment_target_information")
public class TargetInformation extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 业务类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String type;

    /**
     * 业务方向科目
     */
    @Column(name = "course", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String course;

    /**
     * 客户基本信息
     */
    @Column(name = "customer_id", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '客户基本信息'")
    private String customerId;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}