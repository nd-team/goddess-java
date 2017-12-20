//package com.bjike.goddess.marketdevelopment.entity;
//
//import com.bjike.goddess.common.api.entity.BaseEntity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//
///**
// * 市场挖掘
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 07:15 ]
// * @Description: [ 市场挖掘 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Entity
//@Table(name = "marketdevelopment_market_channel")
//public class MarketChannel extends BaseEntity {
//
//    /**
//     * 业务类型
//     */
//    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
//    private String type;
//
//    /**
//     * 业务对象
//     */
//    @Column(name = "course", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务对象'")
//    private String course;
//
//    /**
//     * 人员
//     */
//    @Column(name = "staff", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '人员'")
//    private String staff;
//
//    /**
//     * 资质
//     */
//    @Column(name = "qualification", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '资质'")
//    private String qualification;
//
//    /**
//     * 其他
//     */
//    @Column(name = "other", columnDefinition = "VARCHAR(255)   COMMENT '其他'")
//    private String other;
//
//    /**
//     * 备注
//     */
//    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
//    private String remark;
//
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public String getCourse() {
//        return course;
//    }
//
//    public void setCourse(String course) {
//        this.course = course;
//    }
//
//    public String getStaff() {
//        return staff;
//    }
//
//    public void setStaff(String staff) {
//        this.staff = staff;
//    }
//
//    public String getQualification() {
//        return qualification;
//    }
//
//    public void setQualification(String qualification) {
//        this.qualification = qualification;
//    }
//
//    public String getOther() {
//        return other;
//    }
//
//    public void setOther(String other) {
//        this.other = other;
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark;
//    }
//}