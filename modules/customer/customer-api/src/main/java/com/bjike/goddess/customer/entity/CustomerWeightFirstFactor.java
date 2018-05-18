package com.bjike.goddess.customer.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 客户权重一层因素层设置
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-11-01 11:16 ]
 * @Description: [ 客户权重一层因素层设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "customer_customerweightfirstfactor")
public class CustomerWeightFirstFactor extends BaseEntity {

    /**
     * 职权职权比
     */
    @Column(name = "funPowersFunPowers", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '职权职权比'")
    private Double funPowersFunPowers;

    /**
     * 职权时效性比
     */
    @Column(name = "funPowersTimeliness", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '职权时效性比'")
    private Double funPowersTimeliness;

    /**
     * 职权亲密度比
     */
    @Column(name = "funPowersCloseness", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '职权亲密度比'")
    private Double funPowersCloseness;

    /**
     * 职权难易度比
     */
    @Column(name = "funPowersDiffLevel", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '职权难易度比'")
    private Double funPowersDiffLevel;

    /**
     * 时效性职权比
     */
    @Column(name = "timelinessFunPowers", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '时效性职权比'")
    private Double timelinessFunPowers;

    /**
     * 时效性时效性比
     */
    @Column(name = "timelinessTimeliness", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '时效性时效性比'")
    private Double timelinessTimeliness;

    /**
     * 时效性亲密度比
     */
    @Column(name = "timelinessCloseness", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '时效性亲密度比'")
    private Double timelinessCloseness;

    /**
     * 时效性难易度比
     */
    @Column(name = "timelinessDiffLevel", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '时效性难易度比'")
    private Double timelinessDiffLevel;

    /**
     * 亲密度职权比
     */
    @Column(name = "closenessFunPowers", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '亲密度职权比'")
    private Double closenessFunPowers;

    /**
     * 亲密度时效性比
     */
    @Column(name = "closenessTimeliness", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '亲密度时效性比'")
    private Double closenessTimeliness;

    /**
     * 亲密度亲密度比
     */
    @Column(name = "closenessCloseness", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '亲密度亲密度比'")
    private Double closenessCloseness;

    /**
     * 亲密度难易度比
     */
    @Column(name = "closenessDiffLevel", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '亲密度难易度比'")
    private Double closenessDiffLevel;

    /**
     * 难易度职权比
     */
    @Column(name = "diffLevelFunPowers", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '难易度职权比'")
    private Double diffLevelFunPowers;

    /**
     * 难易度时效性比
     */
    @Column(name = "diffLevelTimeliness", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '难易度时效性比'")
    private Double diffLevelTimeliness;

    /**
     * 难易度亲密度比
     */
    @Column(name = "diffLevelCloseness", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '难易度亲密度比'")
    private Double diffLevelCloseness;

    /**
     * 难易度难易度比
     */
    @Column(name = "diffLevelDiffLevel", nullable = false, columnDefinition = "DECIMAL(10,4)   COMMENT '难易度难易度比'")
    private Double diffLevelDiffLevel;


    public Double getFunPowersFunPowers() {
        return funPowersFunPowers;
    }

    public void setFunPowersFunPowers(Double funPowersFunPowers) {
        this.funPowersFunPowers = funPowersFunPowers;
    }

    public Double getFunPowersTimeliness() {
        return funPowersTimeliness;
    }

    public void setFunPowersTimeliness(Double funPowersTimeliness) {
        this.funPowersTimeliness = funPowersTimeliness;
    }

    public Double getFunPowersCloseness() {
        return funPowersCloseness;
    }

    public void setFunPowersCloseness(Double funPowersCloseness) {
        this.funPowersCloseness = funPowersCloseness;
    }

    public Double getFunPowersDiffLevel() {
        return funPowersDiffLevel;
    }

    public void setFunPowersDiffLevel(Double funPowersDiffLevel) {
        this.funPowersDiffLevel = funPowersDiffLevel;
    }

    public Double getTimelinessFunPowers() {
        return timelinessFunPowers;
    }

    public void setTimelinessFunPowers(Double timelinessFunPowers) {
        this.timelinessFunPowers = timelinessFunPowers;
    }

    public Double getTimelinessTimeliness() {
        return timelinessTimeliness;
    }

    public void setTimelinessTimeliness(Double timelinessTimeliness) {
        this.timelinessTimeliness = timelinessTimeliness;
    }

    public Double getTimelinessCloseness() {
        return timelinessCloseness;
    }

    public void setTimelinessCloseness(Double timelinessCloseness) {
        this.timelinessCloseness = timelinessCloseness;
    }

    public Double getTimelinessDiffLevel() {
        return timelinessDiffLevel;
    }

    public void setTimelinessDiffLevel(Double timelinessDiffLevel) {
        this.timelinessDiffLevel = timelinessDiffLevel;
    }

    public Double getClosenessFunPowers() {
        return closenessFunPowers;
    }

    public void setClosenessFunPowers(Double closenessFunPowers) {
        this.closenessFunPowers = closenessFunPowers;
    }

    public Double getClosenessTimeliness() {
        return closenessTimeliness;
    }

    public void setClosenessTimeliness(Double closenessTimeliness) {
        this.closenessTimeliness = closenessTimeliness;
    }

    public Double getClosenessCloseness() {
        return closenessCloseness;
    }

    public void setClosenessCloseness(Double closenessCloseness) {
        this.closenessCloseness = closenessCloseness;
    }

    public Double getClosenessDiffLevel() {
        return closenessDiffLevel;
    }

    public void setClosenessDiffLevel(Double closenessDiffLevel) {
        this.closenessDiffLevel = closenessDiffLevel;
    }

    public Double getDiffLevelFunPowers() {
        return diffLevelFunPowers;
    }

    public void setDiffLevelFunPowers(Double diffLevelFunPowers) {
        this.diffLevelFunPowers = diffLevelFunPowers;
    }

    public Double getDiffLevelTimeliness() {
        return diffLevelTimeliness;
    }

    public void setDiffLevelTimeliness(Double diffLevelTimeliness) {
        this.diffLevelTimeliness = diffLevelTimeliness;
    }

    public Double getDiffLevelCloseness() {
        return diffLevelCloseness;
    }

    public void setDiffLevelCloseness(Double diffLevelCloseness) {
        this.diffLevelCloseness = diffLevelCloseness;
    }

    public Double getDiffLevelDiffLevel() {
        return diffLevelDiffLevel;
    }

    public void setDiffLevelDiffLevel(Double diffLevelDiffLevel) {
        this.diffLevelDiffLevel = diffLevelDiffLevel;
    }
}