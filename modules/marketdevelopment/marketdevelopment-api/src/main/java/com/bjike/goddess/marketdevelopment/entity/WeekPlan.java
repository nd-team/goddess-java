//package com.bjike.goddess.marketdevelopment.entity;
//
//import com.bjike.goddess.common.api.entity.BaseEntity;
//
//import javax.persistence.*;
//import java.time.LocalDate;
//
//
///**
// * 周计划
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 06:49 ]
// * @Description: [ 周计划 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Entity
//@Table(name = "marketdevelopment_week_plan")
//public class WeekPlan extends BaseEntity {
//
//    /**
//     * 月计划
//     */
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "month_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '月计划'")
//    private MonthPlan month;
//
//    /**
//     * 周期(开始日期)
//     */
//    @Column(name = "startCycle", nullable = false, columnDefinition = "DATE   COMMENT '周期(开始日期)'")
//    private LocalDate startCycle;
//
//    /**
//     * 周期(结束日期)
//     */
//    @Column(name = "endCycle", nullable = false, columnDefinition = "DATE   COMMENT '周期(结束日期)'")
//    private LocalDate endCycle;
//
//    /**
//     * 各周工作量在整月占比
//     */
//    @Column(name = "accounted", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '各周工作量在整月占比'")
//    private Double accounted;
//
//    /**
//     * 查询
//     */
//    @Column(name = "inquire", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '查询'")
//    private Double inquire;
//
//    /**
//     * 了解
//     */
//    @Column(name = "know", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '了解'")
//    private Double know;
//
//    /**
//     * 接触
//     */
//    @Column(name = "contact", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '接触'")
//    private Double contact;
//
//    /**
//     * 拜访
//     */
//    @Column(name = "visit", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '拜访'")
//    private Double visit;
//
//    /**
//     * 活动
//     */
//    @Column(name = "activity", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '活动'")
//    private Double activity;
//
//    /**
//     * 合计
//     */
//    @Column(name = "total", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合计'")
//    private Double total;
//
//
//    public MonthPlan getMonth() {
//        return month;
//    }
//
//    public void setMonth(MonthPlan month) {
//        this.month = month;
//    }
//
//    public LocalDate getStartCycle() {
//        return startCycle;
//    }
//
//    public void setStartCycle(LocalDate startCycle) {
//        this.startCycle = startCycle;
//    }
//
//    public LocalDate getEndCycle() {
//        return endCycle;
//    }
//
//    public void setEndCycle(LocalDate endCycle) {
//        this.endCycle = endCycle;
//    }
//
//    public Double getAccounted() {
//        return accounted;
//    }
//
//    public void setAccounted(Double accounted) {
//        this.accounted = accounted;
//    }
//
//    public Double getInquire() {
//        return inquire;
//    }
//
//    public void setInquire(Double inquire) {
//        this.inquire = inquire;
//    }
//
//    public Double getKnow() {
//        return know;
//    }
//
//    public void setKnow(Double know) {
//        this.know = know;
//    }
//
//    public Double getContact() {
//        return contact;
//    }
//
//    public void setContact(Double contact) {
//        this.contact = contact;
//    }
//
//    public Double getVisit() {
//        return visit;
//    }
//
//    public void setVisit(Double visit) {
//        this.visit = visit;
//    }
//
//    public Double getActivity() {
//        return activity;
//    }
//
//    public void setActivity(Double activity) {
//        this.activity = activity;
//    }
//
//    public Double getTotal() {
//        return total;
//    }
//
//    public void setTotal(Double total) {
//        this.total = total;
//    }
//}