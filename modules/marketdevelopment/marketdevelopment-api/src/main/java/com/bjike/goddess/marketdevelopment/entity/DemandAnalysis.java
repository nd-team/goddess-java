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
// * 市场需求分析
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 07:10 ]
// * @Description: [ 市场需求分析 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Entity
//@Table(name = "marketdevelopment_demand_analysis")
//public class DemandAnalysis extends BaseEntity {
//
//    /**
//     * 业务类型
//     */
//    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
//    private String type;
//
//    /**
//     * 业务方向科目
//     */
//    @Column(name = "course", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
//    private String course;
//
//    /**
//     * 目标人群
//     */
//    @Column(name = "target", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '目标人群'")
//    private String target;
//
//    /**
//     * 目标定位
//     */
//    @Column(name = "orientation", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '目标定位'")
//    private String orientation;
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
//    public String getTarget() {
//        return target;
//    }
//
//    public void setTarget(String target) {
//        this.target = target;
//    }
//
//    public String getOrientation() {
//        return orientation;
//    }
//
//    public void setOrientation(String orientation) {
//        this.orientation = orientation;
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