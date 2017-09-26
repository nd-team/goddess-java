package com.bjike.goddess.secure.vo;

/**
 * 社保购买情况（汇总明细表）表现层对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-23 02:59 ]
 * @Description: [ 社保购买情况（汇总明细表）表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SecureCaseVO {

    /**
     * id
     */
    private String id;
    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 参保单位
     */
    private String unit;

    /**
     * 参保地区
     */
    private String area;

    /**
     * 姓名
     */
    private String name;

    /**
     * 是否已购买社保
     */
    private Boolean haveBuySecure;

    /**
     * 是否签订放弃购买社保协议
     */
    private Boolean abandonSecure;

    /**
     * 是否为新增购买社保人员
     */
    private Boolean addSecure;

    /**
     * 是否需要挂靠
     */
    private Boolean needAttached;

    /**
     * 挂靠原因
     */
    private String cause;

    /**
     * 是否离职
     */
    private Boolean dimission;

    /**
     * 是否挂靠到期
     */
    private Boolean affiliated;

    /**
     * 是否为社保购买的减员人员
     */
    private Boolean addAttrition;

    /**
     * 是否购买社保
     */
    private Boolean buySecure;

    /**
     * 是否可领回社保卡
     */
    private Boolean backSecureCard;

    /**
     * 是否领取社保卡
     */
    private Boolean drawSecureCard;

    /**
     * 是否需补办社保卡
     */
    private Boolean replaceSecureCard;

    /**
     * 社保购买时间
     */
    private String buySecureTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getBuySecureTime() {
        return buySecureTime;
    }

    public void setBuySecureTime(String buySecureTime) {
        this.buySecureTime = buySecureTime;
    }
}