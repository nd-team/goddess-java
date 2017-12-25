//package com.bjike.goddess.marketdevelopment.entity;
//
//import com.bjike.goddess.common.api.entity.BaseEntity;
//import com.bjike.goddess.marketdevelopment.enums.MonthType;
//
//import javax.persistence.*;
//
//
///**
// * 月计划
// *
// * @Author: [ dengjunren ]
// * @Date: [ 2017-03-22 06:41 ]
// * @Description: [ 月计划 ]
// * @Version: [ v1.0.0 ]
// * @Copy: [ com.bjike ]
// */
//@Entity
//@Table(name = "marketdevelopment_month_plan")
//public class MonthPlan extends BaseEntity {
//
//    /**
//     * 年计划
//     */
//    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    @JoinColumn(name = "year_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '年计划'")
//    private YearPlan year;
//
//    /**
//     * 月份
//     */
//    @Column(name = "month", columnDefinition = "TINYINT(2) COMMENT '状态' ", nullable = false)
//    private MonthType month;
//
//    /**
//     * 各科目年度任务量
//     */
//    @Column(name = "quota", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '各科目年度任务量'")
//    private Double quota;
//
//    /**
//     * 年度占比
//     */
//    @Column(name = "accounted", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '年度占比'")
//    private Double accounted;
//
//    /**
//     * 业务方向科目中占比
//     */
//    @Column(name = "courseAccounted", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务方向科目中占比'")
//    private Double courseAccounted;
//
//    /**
//     * 计划最小任务量
//     */
//    @Column(name = "leastQuota", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划最小任务量'")
//    private Double leastQuota;
//
//    /**
//     * 总计
//     */
//    @Column(name = "total", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总计'")
//    private Double total;
//
//
//
//    public YearPlan getYear() {
//        return year;
//    }
//
//    public void setYear(YearPlan year) {
//        this.year = year;
//    }
//
//    public MonthType getMonth() {
//        return month;
//    }
//
//    public void setMonth(MonthType month) {
//        this.month = month;
//    }
//
//    public Double getQuota() {
//        return quota;
//    }
//
//    public void setQuota(Double quota) {
//        this.quota = quota;
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
//    public Double getCourseAccounted() {
//        return courseAccounted;
//    }
//
//    public void setCourseAccounted(Double courseAccounted) {
//        this.courseAccounted = courseAccounted;
//    }
//
//    public Double getLeastQuota() {
//        return leastQuota;
//    }
//
//    public void setLeastQuota(Double leastQuota) {
//        this.leastQuota = leastQuota;
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