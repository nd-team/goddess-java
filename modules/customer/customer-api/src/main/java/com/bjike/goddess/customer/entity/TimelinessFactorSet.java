package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 时效性因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 01:49 ]
 * @Description: [ 时效性因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_timelinessfactorset")
public class TimelinessFactorSet extends BaseEntity {

    /**
     * 十分紧急十分紧急比
     */
    @Column(name = "urgentUrgent", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '十分紧急十分紧急比'")
    private Double urgentUrgent;

    /**
     * 十分紧急紧急比
     */
    @Column(name = "urgentUrgency", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '十分紧急紧急比'")
    private Double urgentUrgency;

    /**
     * 十分紧急一般比
     */
    @Column(name = "urgentGeneral", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '十分紧急一般比'")
    private Double urgentGeneral;

    /**
     * 十分紧急不紧急比
     */
    @Column(name = "urgentNoUrgency", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '十分紧急不紧急比'")
    private Double urgentNoUrgency;

    /**
     * 紧急十分紧急比
     */
    @Column(name = "urgencyUrgent", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '紧急十分紧急比'")
    private Double urgencyUrgent;

    /**
     * 紧急紧急比
     */
    @Column(name = "urgencyUrgency", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '紧急紧急比'")
    private Double urgencyUrgency;

    /**
     * 紧急一般比
     */
    @Column(name = "urgencyGeneral", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '紧急一般比'")
    private Double urgencyGeneral;

    /**
     * 紧急不紧急比
     */
    @Column(name = "urgencyNoUrgency", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '紧急不紧急比'")
    private Double urgencyNoUrgency;

    /**
     * 一般十分紧急比
     */
    @Column(name = "generalUrgent", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '一般十分紧急比'")
    private Double generalUrgent;

    /**
     * 一般紧急比
     */
    @Column(name = "generalUrgency", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '一般紧急比'")
    private Double generalUrgency;

    /**
     * 一般一般比
     */
    @Column(name = "generalGeneral", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '一般一般比'")
    private Double generalGeneral;

    /**
     * 一般不紧急比
     */
    @Column(name = "generalNoUrgency", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '一般不紧急比'")
    private Double generalNoUrgency;

    /**
     * 不紧急十分紧急比
     */
    @Column(name = "noUrgencyUrgent", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '不紧急十分紧急比'")
    private Double noUrgencyUrgent;

    /**
     * 不紧急紧急比
     */
    @Column(name = "noUrgencyUrgency", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '不紧急紧急比'")
    private Double noUrgencyUrgency;

    /**
     * 不紧急一般比
     */
    @Column(name = "noUrgencyGeneral", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '不紧急一般比'")
    private Double noUrgencyGeneral;

    /**
     * 不紧急不紧急比
     */
    @Column(name = "noUrgencyNoUrgency", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '不紧急不紧急比'")
    private Double noUrgencyNoUrgency;


    public Double getUrgentUrgent() {
        return urgentUrgent;
    }

    public void setUrgentUrgent(Double urgentUrgent) {
        this.urgentUrgent = urgentUrgent;
    }

    public Double getUrgentUrgency() {
        return urgentUrgency;
    }

    public void setUrgentUrgency(Double urgentUrgency) {
        this.urgentUrgency = urgentUrgency;
    }

    public Double getUrgentGeneral() {
        return urgentGeneral;
    }

    public void setUrgentGeneral(Double urgentGeneral) {
        this.urgentGeneral = urgentGeneral;
    }

    public Double getUrgentNoUrgency() {
        return urgentNoUrgency;
    }

    public void setUrgentNoUrgency(Double urgentNoUrgency) {
        this.urgentNoUrgency = urgentNoUrgency;
    }

    public Double getUrgencyUrgent() {
        return urgencyUrgent;
    }

    public void setUrgencyUrgent(Double urgencyUrgent) {
        this.urgencyUrgent = urgencyUrgent;
    }

    public Double getUrgencyUrgency() {
        return urgencyUrgency;
    }

    public void setUrgencyUrgency(Double urgencyUrgency) {
        this.urgencyUrgency = urgencyUrgency;
    }

    public Double getUrgencyGeneral() {
        return urgencyGeneral;
    }

    public void setUrgencyGeneral(Double urgencyGeneral) {
        this.urgencyGeneral = urgencyGeneral;
    }

    public Double getUrgencyNoUrgency() {
        return urgencyNoUrgency;
    }

    public void setUrgencyNoUrgency(Double urgencyNoUrgency) {
        this.urgencyNoUrgency = urgencyNoUrgency;
    }

    public Double getGeneralUrgent() {
        return generalUrgent;
    }

    public void setGeneralUrgent(Double generalUrgent) {
        this.generalUrgent = generalUrgent;
    }

    public Double getGeneralUrgency() {
        return generalUrgency;
    }

    public void setGeneralUrgency(Double generalUrgency) {
        this.generalUrgency = generalUrgency;
    }

    public Double getGeneralGeneral() {
        return generalGeneral;
    }

    public void setGeneralGeneral(Double generalGeneral) {
        this.generalGeneral = generalGeneral;
    }

    public Double getGeneralNoUrgency() {
        return generalNoUrgency;
    }

    public void setGeneralNoUrgency(Double generalNoUrgency) {
        this.generalNoUrgency = generalNoUrgency;
    }

    public Double getNoUrgencyUrgent() {
        return noUrgencyUrgent;
    }

    public void setNoUrgencyUrgent(Double noUrgencyUrgent) {
        this.noUrgencyUrgent = noUrgencyUrgent;
    }

    public Double getNoUrgencyUrgency() {
        return noUrgencyUrgency;
    }

    public void setNoUrgencyUrgency(Double noUrgencyUrgency) {
        this.noUrgencyUrgency = noUrgencyUrgency;
    }

    public Double getNoUrgencyGeneral() {
        return noUrgencyGeneral;
    }

    public void setNoUrgencyGeneral(Double noUrgencyGeneral) {
        this.noUrgencyGeneral = noUrgencyGeneral;
    }

    public Double getNoUrgencyNoUrgency() {
        return noUrgencyNoUrgency;
    }

    public void setNoUrgencyNoUrgency(Double noUrgencyNoUrgency) {
        this.noUrgencyNoUrgency = noUrgencyNoUrgency;
    }
}