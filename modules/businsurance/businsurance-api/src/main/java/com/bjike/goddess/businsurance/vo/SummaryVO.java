package com.bjike.goddess.businsurance.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 团体意外险购买详情业务传输对象
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2017-09-26 10:44 ]
 * @Description: [ 团体意外险购买详情业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class SummaryVO {

    /**
     * 地区
     */
    private String area;

    /**
     * 项目组/部门
     */
    private String department;

    /**
     * 入职人数
     */
    private Integer entryNum;

    /**
     * 放弃购买社保人数
     */
    private Integer givingBuyNum;

    /**
     * 意外险增员数
     */
    private Integer casualtyIncreaseNum;

    /**
     * 意外险购买人数
     */
    private Integer casualtyBuyNum;

    /**
     * 未购买意外险人数
     */
    private Integer noBuyCasualtyNum;

    /**
     * 离职人数
     */
    private Integer leaveOffNum;

    /**
     * 意外险减员人数
     */
    private Integer casualtyAttritionNum;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }

    public Integer getGivingBuyNum() {
        return givingBuyNum;
    }

    public void setGivingBuyNum(Integer givingBuyNum) {
        this.givingBuyNum = givingBuyNum;
    }

    public Integer getCasualtyIncreaseNum() {
        return casualtyIncreaseNum;
    }

    public void setCasualtyIncreaseNum(Integer casualtyIncreaseNum) {
        this.casualtyIncreaseNum = casualtyIncreaseNum;
    }

    public Integer getCasualtyBuyNum() {
        return casualtyBuyNum;
    }

    public void setCasualtyBuyNum(Integer casualtyBuyNum) {
        this.casualtyBuyNum = casualtyBuyNum;
    }

    public Integer getNoBuyCasualtyNum() {
        return noBuyCasualtyNum;
    }

    public void setNoBuyCasualtyNum(Integer noBuyCasualtyNum) {
        this.noBuyCasualtyNum = noBuyCasualtyNum;
    }

    public Integer getLeaveOffNum() {
        return leaveOffNum;
    }

    public void setLeaveOffNum(Integer leaveOffNum) {
        this.leaveOffNum = leaveOffNum;
    }

    public Integer getCasualtyAttritionNum() {
        return casualtyAttritionNum;
    }

    public void setCasualtyAttritionNum(Integer casualtyAttritionNum) {
        this.casualtyAttritionNum = casualtyAttritionNum;
    }
}