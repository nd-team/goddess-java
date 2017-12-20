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
// * 市场调研
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 07:16 ]
// * @Description: [ 市场调研 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Entity
//@Table(name = "marketdevelopment_market_research")
//public class MarketResearch extends BaseEntity {
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
//     * 业务对象
//     */
//    @Column(name = "business", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '业务对象'")
//    private String business;
//
//    /**
//     * 竞争对手名称
//     */
//    @Column(name = "competitors", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '竞争对手名称'")
//    private String competitors;
//
//    /**
//     * 价格评估
//     */
//    @Column(name = "evaluate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '价格评估'")
//    private String evaluate;
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
//    public String getBusiness() {
//        return business;
//    }
//
//    public void setBusiness(String business) {
//        this.business = business;
//    }
//
//    public String getCompetitors() {
//        return competitors;
//    }
//
//    public void setCompetitors(String competitors) {
//        this.competitors = competitors;
//    }
//
//    public String getEvaluate() {
//        return evaluate;
//    }
//
//    public void setEvaluate(String evaluate) {
//        this.evaluate = evaluate;
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