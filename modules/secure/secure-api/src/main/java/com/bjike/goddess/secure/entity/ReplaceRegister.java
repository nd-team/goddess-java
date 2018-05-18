package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 社保卡补办登记表
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-25 06:09 ]
 * @Description: [ 社保卡补办登记表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_replaceregister")
public class ReplaceRegister extends BaseEntity {

    /**
     * 补办时间
     */
    @Column(name = "replaceTime", columnDefinition = "DATE   COMMENT '补办时间'")
    private LocalDate replaceTime;

    /**
     * 持卡人姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '持卡人姓名'")
    private String name;

    /**
     * 身份证号码
     */
    @Column(name = "card", columnDefinition = "INT(11)   COMMENT '身份证号码'")
    private Integer card;

    /**
     * 原社保账户号码
     */
    @Column(name = "oldSecure", columnDefinition = "INT(11)   COMMENT '原社保账户号码'")
    private Integer oldSecure;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 补办机构
     */
    @Column(name = "replaceMechanism", columnDefinition = "VARCHAR(255)   COMMENT '补办机构'")
    private String replaceMechanism;

    /**
     * 领用时间
     */
    @Column(name = "drawTime", columnDefinition = "DATE   COMMENT '领用时间'")
    private LocalDate drawTime;

    /**
     * 补办原因
     */
    @Column(name = "cause", columnDefinition = "VARCHAR(255)   COMMENT '补办原因'")
    private String cause;

    /**
     * 补办人
     */
    @Column(name = "replaceName", columnDefinition = "VARCHAR(255)   COMMENT '补办人'")
    private String replaceName;

    /**
     * 综合资源部意见
     */
    @Column(name = "resourcesOpinion", columnDefinition = "VARCHAR(255)   COMMENT '综合资源部意见'")
    private String resourcesOpinion;

    /**
     * 运营财务部意见
     */
    @Column(name = "financeOpinion", columnDefinition = "VARCHAR(255)   COMMENT '运营财务部意见'")
    private String financeOpinion;

    /**
     * 总经办意见
     */
    @Column(name = "generalOpinion", columnDefinition = "VARCHAR(255)   COMMENT '总经办意见'")
    private String generalOpinion;

    /**
     * 预挂失有效时限
     */
    @Column(name = "lossTime", columnDefinition = "INT(11)   COMMENT '预挂失有效时限'")
    private Integer lossTime;

    /**
     * 补办人是否领取新卡
     */
    @Column(name = "is_newCard", columnDefinition = "TINYINT(1)  COMMENT '补办人是否领取新卡'")
    private Boolean newCard;

    /**
     * 现社保账户号码
     */
    @Column(name = "newSecure", columnDefinition = "INT(11)   COMMENT '现社保账户号码'")
    private Integer newSecure;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public LocalDate getReplaceTime() {
        return replaceTime;
    }

    public void setReplaceTime(LocalDate replaceTime) {
        this.replaceTime = replaceTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    public Integer getOldSecure() {
        return oldSecure;
    }

    public void setOldSecure(Integer oldSecure) {
        this.oldSecure = oldSecure;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getReplaceMechanism() {
        return replaceMechanism;
    }

    public void setReplaceMechanism(String replaceMechanism) {
        this.replaceMechanism = replaceMechanism;
    }

    public LocalDate getDrawTime() {
        return drawTime;
    }

    public void setDrawTime(LocalDate drawTime) {
        this.drawTime = drawTime;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }

    public String getResourcesOpinion() {
        return resourcesOpinion;
    }

    public void setResourcesOpinion(String resourcesOpinion) {
        this.resourcesOpinion = resourcesOpinion;
    }

    public String getFinanceOpinion() {
        return financeOpinion;
    }

    public void setFinanceOpinion(String financeOpinion) {
        this.financeOpinion = financeOpinion;
    }

    public String getGeneralOpinion() {
        return generalOpinion;
    }

    public void setGeneralOpinion(String generalOpinion) {
        this.generalOpinion = generalOpinion;
    }

    public Integer getLossTime() {
        return lossTime;
    }

    public void setLossTime(Integer lossTime) {
        this.lossTime = lossTime;
    }

    public Boolean getNewCard() {
        return newCard;
    }

    public void setNewCard(Boolean newCard) {
        this.newCard = newCard;
    }

    public Integer getNewSecure() {
        return newSecure;
    }

    public void setNewSecure(Integer newSecure) {
        this.newSecure = newSecure;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}