package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.AduitStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 请假审核表
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-10 10:56 ]
 * @Description: [ 请假审核表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_vacateaudit")
public class VacateAudit extends BaseEntity {

    /**
     * 审核时间
     */
    @Column(name = "date", columnDefinition = "DATE   COMMENT '审核时间'")
    private LocalDate date;
    /**
     * 审核人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String name;

    /**
     * 审核意见
     */
    @Column(name = "advice", columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String advice;

    /**
     * 审核状态
     */
    @Column(name = "aduitStatus", columnDefinition = "TINYINT(2)   COMMENT '审核状态'")
    private AduitStatus aduitStatus;

    /**
     * 请假申请id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vacate_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '请假申请id'")
    private Vacate vacate;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Vacate getVacate() {
        return vacate;
    }

    public void setVacate(Vacate vacate) {
        this.vacate = vacate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public AduitStatus getAduitStatus() {
        return aduitStatus;
    }

    public void setAduitStatus(AduitStatus aduitStatus) {
        this.aduitStatus = aduitStatus;
    }
}