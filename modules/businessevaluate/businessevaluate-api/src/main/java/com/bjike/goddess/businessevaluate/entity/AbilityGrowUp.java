package com.bjike.goddess.businessevaluate.entity;

import com.bjike.goddess.businessevaluate.enums.GroupUpType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 能力成长
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-03-28 04:13 ]
 * @Description: [ 能力成长 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessevaluate_abilitygrowup")
public class AbilityGrowUp extends BaseEntity {

    /**
     * 能力成长类型
     */
    @Column(name = "groupUpType", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '能力成长类型'")
    private GroupUpType groupUpType;

    /**
     * 一月
     */
    @Column(name = "januaryMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '一月'")
    private Double januaryMoney;

    /**
     * 二月
     */
    @Column(name = "februaryMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '二月'")
    private Double februaryMoney;

    /**
     * 三月
     */
    @Column(name = "marchMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '三月'")
    private Double marchMoney;

    /**
     * 四月
     */
    @Column(name = "aprilMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '四月'")
    private Double aprilMoney;

    /**
     * 五月
     */
    @Column(name = "mayMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '五月'")
    private Double mayMoney;

    /**
     * 六月
     */
    @Column(name = "juneMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '六月'")
    private Double juneMoney;

    /**
     * 七月
     */
    @Column(name = "julyMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '七月'")
    private Double julyMoney;

    /**
     * 八月
     */
    @Column(name = "augustMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '八月'")
    private Double augustMoney;

    /**
     * 九月
     */
    @Column(name = "septemberMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '九月'")
    private Double septemberMoney;

    /**
     * 十月
     */
    @Column(name = "octoberMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '十月'")
    private Double octoberMoney;

    /**
     * 十一月
     */
    @Column(name = "novemberMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '十一月'")
    private Double novemberMoney;

    /**
     * 十二月
     */
    @Column(name = "decemberMoney", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '十二月'")
    private Double decemberMoney;

    /**
     * 项目信息Id
     */
    @Column(name = "projectInfoId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '项目信息Id'")
    private String projectInfoId;


    public GroupUpType getGroupUpType() {
        return groupUpType;
    }

    public void setGroupUpType(GroupUpType groupUpType) {
        this.groupUpType = groupUpType;
    }

    public Double getJanuaryMoney() {
        return januaryMoney;
    }

    public void setJanuaryMoney(Double januaryMoney) {
        this.januaryMoney = januaryMoney;
    }

    public Double getFebruaryMoney() {
        return februaryMoney;
    }

    public void setFebruaryMoney(Double februaryMoney) {
        this.februaryMoney = februaryMoney;
    }

    public Double getMarchMoney() {
        return marchMoney;
    }

    public void setMarchMoney(Double marchMoney) {
        this.marchMoney = marchMoney;
    }

    public Double getAprilMoney() {
        return aprilMoney;
    }

    public void setAprilMoney(Double aprilMoney) {
        this.aprilMoney = aprilMoney;
    }

    public Double getMayMoney() {
        return mayMoney;
    }

    public void setMayMoney(Double mayMoney) {
        this.mayMoney = mayMoney;
    }

    public Double getJuneMoney() {
        return juneMoney;
    }

    public void setJuneMoney(Double juneMoney) {
        this.juneMoney = juneMoney;
    }

    public Double getJulyMoney() {
        return julyMoney;
    }

    public void setJulyMoney(Double julyMoney) {
        this.julyMoney = julyMoney;
    }

    public Double getAugustMoney() {
        return augustMoney;
    }

    public void setAugustMoney(Double augustMoney) {
        this.augustMoney = augustMoney;
    }

    public Double getSeptemberMoney() {
        return septemberMoney;
    }

    public void setSeptemberMoney(Double septemberMoney) {
        this.septemberMoney = septemberMoney;
    }

    public Double getOctoberMoney() {
        return octoberMoney;
    }

    public void setOctoberMoney(Double octoberMoney) {
        this.octoberMoney = octoberMoney;
    }

    public Double getNovemberMoney() {
        return novemberMoney;
    }

    public void setNovemberMoney(Double novemberMoney) {
        this.novemberMoney = novemberMoney;
    }

    public Double getDecemberMoney() {
        return decemberMoney;
    }

    public void setDecemberMoney(Double decemberMoney) {
        this.decemberMoney = decemberMoney;
    }

    public String getProjectInfoId() {
        return projectInfoId;
    }

    public void setProjectInfoId(String projectInfoId) {
        this.projectInfoId = projectInfoId;
    }
}