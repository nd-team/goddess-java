package com.bjike.goddess.staffing.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * 人工成本计划
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-31 11:47 ]
 * @Description: [ 人工成本计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffing_expendplan")
public class ExpendPlan extends BaseEntity {

    /**
     * 时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATE   COMMENT '时间'")
    private LocalDate time;

    /**
     * 计划人工成本
     */
    @Column(name = "planExpend", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '计划人工成本'")
    private Double planExpend;

    /**
     * 公司整体平均工资水平
     */
    @Column(name = "avg", columnDefinition = "DECIMAL(10,2)   COMMENT '公司整体平均工资水平'")
    private Double avg;

    /**
     * 公司总人数配置
     */
    @Column(name = "total",columnDefinition = "INT(11)   COMMENT '公司总人数配置'")
    private Integer total;

    /**
     * 总经办成本占比
     */
    @Column(name = "bossProportion", columnDefinition = "VARCHAR(255)   COMMENT '总经办成本占比'")
    private String bossProportion;

////    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "expendPlan")
//    private List<Detail> details = new ArrayList<>();
//
////    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, mappedBy = "expendPlan")
//    private List<Son> planSons = new ArrayList<>();
//
//    public List<Detail> getDetails() {
//        return details;
//    }
//
//    public void setDetails(List<Detail> details) {
//        this.details = details;
//    }
//
//    public List<Son> getPlanSons() {
//        return planSons;
//    }
//
//    public void setPlanSons(List<Son> planSons) {
//        this.planSons = planSons;
//    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Double getPlanExpend() {
        return planExpend;
    }

    public void setPlanExpend(Double planExpend) {
        this.planExpend = planExpend;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getBossProportion() {
        return bossProportion;
    }

    public void setBossProportion(String bossProportion) {
        this.bossProportion = bossProportion;
    }
}