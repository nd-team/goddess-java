package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目提成分配因素
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 11:39 ]
 * @Description: [ 项目提成分配因素 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ProjectFactorsTO extends BaseTO {

    /**
     * 方案编号
     */
    @NotBlank(message = "方案编号",groups = {ADD.class, EDIT.class})
    private String code;

    /**
     * 方案内容描述
     */
    @NotBlank(message = "方案内容描述",groups = {ADD.class, EDIT.class})
    private String description;

    /**
     * 干股和公司预留利润比例
     */
    @NotNull(message = "干股和公司预留利润比例",groups = {ADD.class, EDIT.class})
    private Double profitsProportion;

    /**
     * 业务提成比例
     */
    @NotNull(message = "业务提成比例",groups = {ADD.class, EDIT.class})
    private Double business;

    /**
     * 管理提成比例
     */
    @NotNull(message = "管理提成比例",groups = {ADD.class, EDIT.class})
    private Double manage;

    /**
     * 资金方比例
     */
    @NotNull(message = "资金方比例",groups = {ADD.class, EDIT.class})
    private Double capital;

    /**
     * 业务提成最高
     */
    @NotNull(message = "业务提成最高",groups = {ADD.class, EDIT.class})
    private Double businessHighest;

    /**
     * 业务提成最低
     */
    @NotNull(message = "业务提成最低",groups = {ADD.class, EDIT.class})
    private Double businessMinimum;

    /**
     * 管理提成最高
     */
    @NotNull(message = "管理提成最高",groups = {ADD.class, EDIT.class})
    private Double manageHighest;

    /**
     * 管理提成最低
     */
    @NotNull(message = "管理提成最低",groups = {ADD.class, EDIT.class})
    private Double manageMinimum;

    /**
     * 资金方最高
     */
    @NotNull(message = "资金方最高",groups = {ADD.class, EDIT.class})
    private Double capitalHighest;

    /**
     * 资金方最低
     */
    @NotNull(message = "资金方最低",groups = {ADD.class, EDIT.class})
    private Double capitalMinimum;

    /**
     * 备注
     */
    private String remark;

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
}