package com.bjike.goddess.secure.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 社保购买情况（汇总明细表）
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-23 02:59 ]
 * @Description: [ 社保购买情况（汇总明细表） ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "secure_securecase")
public class SecureCase extends BaseEntity {

    /**
     * 项目组
     */
    @Column(name = "projectGroup",  columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 参保单位
     */
    @Column(name = "unit",  columnDefinition = "VARCHAR(255)   COMMENT '参保单位'")
    private String unit;

    /**
     * 参保地区
     */
    @Column(name = "area",  columnDefinition = "VARCHAR(255)   COMMENT '参保地区'")
    private String area;

    /**
     * 姓名
     */
    @Column(name = "name",  columnDefinition = "VARCHAR(255)   COMMENT '姓名'")
    private String name;

    /**
     * 是否已购买社保
     */
    @Column(name = "is_haveBuySecure",  columnDefinition = "TINYINT(1)    COMMENT '是否已购买社保'")
    private Boolean haveBuySecure;

    /**
     * 是否签订放弃购买社保协议
     */
    @Column(name = "is_abandonSecure",  columnDefinition = "TINYINT(1)    COMMENT '是否签订放弃购买社保协议'")
    private Boolean abandonSecure;

    /**
     * 是否为新增购买社保人员
     */
    @Column(name = "is_addSecure",  columnDefinition = "TINYINT(1)    COMMENT '是否为新增购买社保人员'")
    private Boolean addSecure;

    /**
     * 是否需要挂靠
     */
    @Column(name = "is_needAttached",  columnDefinition = "TINYINT(1)    COMMENT '是否需要挂靠'")
    private Boolean needAttached;

    /**
     * 挂靠原因
     */
    @Column(name = "cause",  columnDefinition = "VARCHAR(255)   COMMENT '挂靠原因'")
    private String cause;

    /**
     * 是否离职
     */
    @Column(name = "is_dimission",  columnDefinition = "TINYINT(1)    COMMENT '是否离职'")
    private Boolean dimission;

    /**
     * 是否挂靠到期
     */
    @Column(name = "is_affiliated",  columnDefinition = "TINYINT(1)    COMMENT '是否挂靠到期'")
    private Boolean affiliated;

    /**
     * 是否为社保购买的减员人员
     */
    @Column(name = "is_addAttrition",  columnDefinition = "TINYINT(1)    COMMENT '是否为新增的社保购买减员人员'")
    private Boolean addAttrition;

    /**
     * 是否购买社保
     */
    @Column(name = "is_buySecure",  columnDefinition = "TINYINT(1)    COMMENT '是否购买社保'")
    private Boolean buySecure;

    /**
     * 是否可领回社保卡
     */
    @Column(name = "is_backSecureCard",  columnDefinition = "TINYINT(1)    COMMENT '是否可领回社保卡'")
    private Boolean backSecureCard;

    /**
     * 是否领取社保卡
     */
    @Column(name = "is_drawSecureCard",  columnDefinition = "TINYINT(1)    COMMENT '是否领取社保卡'")
    private Boolean drawSecureCard;

    /**
     * 是否需补办社保卡
     */
    @Column(name = "is_replaceSecureCard",  columnDefinition = "TINYINT(1)    COMMENT '是否需补办社保卡'")
    private Boolean replaceSecureCard;

    /**
     * 社保购买时间
     */
    @Column(name = "buySecureTime",  columnDefinition = "DATE   COMMENT '社保购买时间'")
    private LocalDate buySecureTime;


    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getHaveBuySecure() {
        return haveBuySecure;
    }

    public void setHaveBuySecure(Boolean haveBuySecure) {
        this.haveBuySecure = haveBuySecure;
    }

    public Boolean getAbandonSecure() {
        return abandonSecure;
    }

    public void setAbandonSecure(Boolean abandonSecure) {
        this.abandonSecure = abandonSecure;
    }

    public Boolean getAddSecure() {
        return addSecure;
    }

    public void setAddSecure(Boolean addSecure) {
        this.addSecure = addSecure;
    }

    public Boolean getNeedAttached() {
        return needAttached;
    }

    public void setNeedAttached(Boolean needAttached) {
        this.needAttached = needAttached;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Boolean getDimission() {
        return dimission;
    }

    public void setDimission(Boolean dimission) {
        this.dimission = dimission;
    }

    public Boolean getAffiliated() {
        return affiliated;
    }

    public void setAffiliated(Boolean affiliated) {
        this.affiliated = affiliated;
    }

    public Boolean getAddAttrition() {
        return addAttrition;
    }

    public void setAddAttrition(Boolean addAttrition) {
        this.addAttrition = addAttrition;
    }

    public Boolean getBuySecure() {
        return buySecure;
    }

    public void setBuySecure(Boolean buySecure) {
        this.buySecure = buySecure;
    }

    public Boolean getBackSecureCard() {
        return backSecureCard;
    }

    public void setBackSecureCard(Boolean backSecureCard) {
        this.backSecureCard = backSecureCard;
    }

    public Boolean getDrawSecureCard() {
        return drawSecureCard;
    }

    public void setDrawSecureCard(Boolean drawSecureCard) {
        this.drawSecureCard = drawSecureCard;
    }

    public Boolean getReplaceSecureCard() {
        return replaceSecureCard;
    }

    public void setReplaceSecureCard(Boolean replaceSecureCard) {
        this.replaceSecureCard = replaceSecureCard;
    }

    public LocalDate getBuySecureTime() {
        return buySecureTime;
    }

    public void setBuySecureTime(LocalDate buySecureTime) {
        this.buySecureTime = buySecureTime;
    }
}