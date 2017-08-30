package com.bjike.goddess.reimbursementprepare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 资金准备
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-07-03 03:07 ]
 * @Description: [ 资金准备 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "reimbursementprepare_moneyready")
public class MoneyReady extends BaseEntity {

    /**
     * 时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATE   COMMENT '时间'")
    private LocalDate time;

    /**
     * 科目
     */
    @Column(name = "subject", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '科目'")
    private String subject;

    /**
     * 总准备金
     */
    @Column(name = "totalReserve", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '总准备金'")
    private Double totalReserve;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 比例分配
     */
    @Column(name = "prorate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '比例分配'")
    private Double prorate;

    /**
     * 准备金
     */
    @Column(name = "reserve", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '准备金'")
    private Double reserve;


    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getTotalReserve() {
        return totalReserve;
    }

    public void setTotalReserve(Double totalReserve) {
        this.totalReserve = totalReserve;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public Double getProrate() {
        return prorate;
    }

    public void setProrate(Double prorate) {
        this.prorate = prorate;
    }

    public Double getReserve() {
        return reserve;
    }

    public void setReserve(Double reserve) {
        this.reserve = reserve;
    }
}