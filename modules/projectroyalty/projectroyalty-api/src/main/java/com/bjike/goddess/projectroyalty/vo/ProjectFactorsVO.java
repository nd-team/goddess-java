package com.bjike.goddess.projectroyalty.vo;

/**
 * 项目提成分配因素表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 11:39 ]
 * @Description: [ 项目提成分配因素表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectFactorsVO {

    /**
     * id
     */
    private String id;

    /**
     * 更新时间
     */
    private String modifyTime;

    /**
     * 更新人
     */
    private String name;

    /**
     * 方案编号
     */
    private String code;

    /**
     * 方案内容描述
     */
    private String description;

    /**
     * 干股和公司预留利润比例
     */
    private Double profitsProportion;

    /**
     * 业务提成比例
     */
    private Double business;

    /**
     * 管理提成比例
     */
    private Double manage;

    /**
     * 资金方比例
     */
    private Double capital;

    /**
     * 业务提成最高
     */
    private Double businessHighest;

    /**
     * 业务提成最低
     */
    private Double businessMinimum;

    /**
     * 管理提成最高
     */
    private Double manageHighest;

    /**
     * 管理提成最低
     */
    private Double manageMinimum;

    /**
     * 资金方最高
     */
    private Double capitalHighest;

    /**
     * 资金方最低
     */
    private Double capitalMinimum;

    /**
     * 备注
     */
    private String remark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getProfitsProportion() {
        return profitsProportion;
    }

    public void setProfitsProportion(Double profitsProportion) {
        this.profitsProportion = profitsProportion;
    }

    public Double getBusiness() {
        return business;
    }

    public void setBusiness(Double business) {
        this.business = business;
    }

    public Double getManage() {
        return manage;
    }

    public void setManage(Double manage) {
        this.manage = manage;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getBusinessHighest() {
        return businessHighest;
    }

    public void setBusinessHighest(Double businessHighest) {
        this.businessHighest = businessHighest;
    }

    public Double getBusinessMinimum() {
        return businessMinimum;
    }

    public void setBusinessMinimum(Double businessMinimum) {
        this.businessMinimum = businessMinimum;
    }

    public Double getManageHighest() {
        return manageHighest;
    }

    public void setManageHighest(Double manageHighest) {
        this.manageHighest = manageHighest;
    }

    public Double getManageMinimum() {
        return manageMinimum;
    }

    public void setManageMinimum(Double manageMinimum) {
        this.manageMinimum = manageMinimum;
    }

    public Double getCapitalHighest() {
        return capitalHighest;
    }

    public void setCapitalHighest(Double capitalHighest) {
        this.capitalHighest = capitalHighest;
    }

    public Double getCapitalMinimum() {
        return capitalMinimum;
    }

    public void setCapitalMinimum(Double capitalMinimum) {
        this.capitalMinimum = capitalMinimum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
}