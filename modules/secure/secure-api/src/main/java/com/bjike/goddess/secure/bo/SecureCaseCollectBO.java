package com.bjike.goddess.secure.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import java.time.LocalDate;

/**
 * 汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-09-23 02:12 ]
 * @Description: [ 汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SecureCaseCollectBO extends BaseBO {

    /**
     * 地区
     */
    private String area;
    /**
     * 项目组
     */
    private String projectGroup;

    /**
     * 参保单位
     */
    private String unit;

    /**
     * 购买社保人数
     */
    private Integer buySecure;

    /**
     * 已购买社保人数
     */
    private Integer haveBuySecure;

    /**
     * 增员人数
     */
    private Integer addNum;

    /**
     * 减员人数
     */
    private Integer attritionNum;

    /**
     * 挂靠人数
     */
    private Integer attachedNum;

    /**
     * 不购买社保人数
     */
    private Integer notbuySecure;

    /**
     * 需领回的社保卡数
     */
    private Integer backSecureCard;

    /**
     * 社保购买者已领取的社保卡数
     */
    private Integer drawNum;

    /**
     * 需补办社保卡数
     */
    private Integer replaceNum;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public Integer getBuySecure() {
        return buySecure;
    }

    public void setBuySecure(Integer buySecure) {
        this.buySecure = buySecure;
    }

    public Integer getHaveBuySecure() {
        return haveBuySecure;
    }

    public void setHaveBuySecure(Integer haveBuySecure) {
        this.haveBuySecure = haveBuySecure;
    }

    public Integer getAddNum() {
        return addNum;
    }

    public void setAddNum(Integer addNum) {
        this.addNum = addNum;
    }

    public Integer getAttritionNum() {
        return attritionNum;
    }

    public void setAttritionNum(Integer attritionNum) {
        this.attritionNum = attritionNum;
    }

    public Integer getAttachedNum() {
        return attachedNum;
    }

    public void setAttachedNum(Integer attachedNum) {
        this.attachedNum = attachedNum;
    }

    public Integer getNotbuySecure() {
        return notbuySecure;
    }

    public void setNotbuySecure(Integer notbuySecure) {
        this.notbuySecure = notbuySecure;
    }

    public Integer getBackSecureCard() {
        return backSecureCard;
    }

    public void setBackSecureCard(Integer backSecureCard) {
        this.backSecureCard = backSecureCard;
    }

    public Integer getDrawNum() {
        return drawNum;
    }

    public void setDrawNum(Integer drawNum) {
        this.drawNum = drawNum;
    }

    public Integer getReplaceNum() {
        return replaceNum;
    }

    public void setReplaceNum(Integer replaceNum) {
        this.replaceNum = replaceNum;
    }
}