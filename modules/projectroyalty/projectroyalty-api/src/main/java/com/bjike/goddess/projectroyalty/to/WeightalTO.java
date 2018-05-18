package com.bjike.goddess.projectroyalty.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.projectroyalty.enums.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 项目提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WeightalTO extends BaseTO {

//    /**
//     * 确定项目提成分配比例时间
//     */
////    @NotBlank(message = "确定项目提成分配比例时间不能为空", groups = {ADD.class, EDIT.class})
//    private String time;

    /**
     * 地区
     */
    @NotBlank(message = "地区不能为空", groups = {ADD.class, EDIT.class})
    private String area;

    /**
     * 项目组/部门
     */
    @NotBlank(message = "项目组/部门不能为空", groups = {ADD.class, EDIT.class})
    private String department;

    /**
     * 内部项目名称
     */
    @NotBlank(message = "内部项目名称不能为空", groups = {ADD.class, EDIT.class})
    private String project;

    /**
     * 是否立项
     */
    @NotNull(message = "是否立项不能为空", groups = {ADD.class, EDIT.class})
    private Boolean isBuild;

    /**
     * 立项时间
     */
    @NotBlank(message = "立项时间不能为空", groups = {ADD.class, EDIT.class})
    private String buildTime;

    /**
     * 是否完工
     */
    @NotNull(message = "是否完工不能为空", groups = {ADD.class, EDIT.class})
    private Boolean isComplete;

    /**
     * 类型
     */
    @NotNull(message = "类型不能为空", groups = {ADD.class, EDIT.class})
    private Type type;

    /**
     * 完工时间（月）
     */
    @NotNull(message = "完工时间（月）不能为空", groups = {ADD.class, EDIT.class})
    private Integer month;

    /**
     * 合同金额
     */
    @NotNull(message = "合同金额不能为空", groups = {ADD.class, EDIT.class})
    private Double contract;

    /**
     * 回款周期
     */
    @NotNull(message = "回款周期不能为空", groups = {ADD.class, EDIT.class})
    private Integer collection;

    /**
     * 重要性
     */
    @NotNull(message = "重要性不能为空", groups = {ADD.class, EDIT.class})
    private Double importance;

    /**
     * 难易程度
     */
    @NotNull(message = "难易程度不能为空", groups = {ADD.class, EDIT.class})
    private Double facility;

    /**
     * 毛利率
     */
    @NotNull(message = "毛利率不能为空", groups = {ADD.class, EDIT.class})
    private Double ratio;

    /**
     * 利润额
     */
    @NotNull(message = "利润额不能为空", groups = {ADD.class, EDIT.class})
    private Double profit;

    /**
     * 所选方案
     */
    @NotBlank(message = "所选方案不能为空", groups = {ADD.class, EDIT.class})
    private String program;

//    /**
//     * 干股和公司预留利润总比例
//     */
//    @NotNull(message = "干股和公司预留利润总比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double totalRatio;

    /**
     * 干股和公司预留利润因素量
     */
    @NotBlank(message = "干股和公司预留利润因素量不能为空", groups = {ADD.class, EDIT.class})
    private Double profitFactors;

//    /**
//     * 干股和公司预留利润综合比例
//     */
//    @NotNull(message = "干股和公司预留利润综合比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double compreRatio;
//
//    /**
//     * 调整后的干股和公司预留利润综合比例
//     */
//    @NotNull(message = "调整后的干股和公司预留利润综合比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double adjCompreRatio;
//
//    /**
//     * 干股和公司预留利润提成额
//     */
//    @NotNull(message = "干股和公司预留利润提成额不能为空", groups = {ADD.class, EDIT.class})
//    private Double amountProfit;
//
//    /**
//     * 业务提成总比例
//     */
//    @NotNull(message = "业务提成总比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double businessRatio;

    /**
     * 业务提成因素量
     */
    @NotBlank(message = "业务提成因素量不能为空", groups = {ADD.class, EDIT.class})
    private Double businessFactors;

//    /**
//     * 业务提成综合比例
//     */
//    @NotNull(message = "业务提成综合比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double busCompreRatio;
//
//    /**
//     * 调整后的业务提成综合比例
//     */
//    @NotNull(message = "调整后的业务提成综合比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double adjBusCompRatio;
//
//    /**
//     * 业务提成额
//     */
//    @NotNull(message = "业务提成额不能为空", groups = {ADD.class, EDIT.class})
//    private Double business;
//
//    /**
//     * 管理提成总比例
//     */
//    @NotNull(message = "管理提成总比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double menageRatio;

    /**
     * 管理提成因素量
     */
    @NotBlank(message = "管理提成因素量不能为空", groups = {ADD.class, EDIT.class})
    private Double menageFactors;

//    /**
//     * 管理提成综合比例
//     */
//    @NotNull(message = "管理提成综合比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double meCompreRatio;
//
//    /**
//     * 调整后的管理提成综合比例
//     */
//    @NotNull(message = "调整后的管理提成综合比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double adjMeCompreRatio;
//
//    /**
//     * 管理提成额
//     */
//    @NotNull(message = "管理提成额不能为空", groups = {ADD.class, EDIT.class})
//    private Double menage;
//
//    /**
//     * 资金方总比例
//     */
//    @NotNull(message = "资金方总比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double capitalRatio;

    /**
     * 资金方因素量
     */
    @NotBlank(message = "资金方因素量不能为空", groups = {ADD.class, EDIT.class})
    private Double capitalFactors;

//    /**
//     * 资金方综合比例
//     */
//    @NotNull(message = "资金方综合比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double caCompreRatio;
//
//    /**
//     * 调整后的资金方综合比例
//     */
//    @NotNull(message = "调整后的资金方综合比例不能为空", groups = {ADD.class, EDIT.class})
//    private Double adjCaCompreRatio;
//
//    /**
//     * 资金方提成额
//     */
//    @NotNull(message = "资金方提成额不能为空", groups = {ADD.class, EDIT.class})
//    private Double capital;
//
//    /**
//     * 目前比例总和
//     */
//    @NotNull(message = "目前比例总和不能为空", groups = {ADD.class, EDIT.class})
//    private Double allRatio;

    /**
     * 备注
     */
    private String remark;

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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Boolean getBuild() {
        return isBuild;
    }

    public void setBuild(Boolean build) {
        isBuild = build;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getContract() {
        return contract;
    }

    public void setContract(Double contract) {
        this.contract = contract;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    public Double getFacility() {
        return facility;
    }

    public void setFacility(Double facility) {
        this.facility = facility;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Double getProfitFactors() {
        return profitFactors;
    }

    public void setProfitFactors(Double profitFactors) {
        this.profitFactors = profitFactors;
    }

    public Double getBusinessFactors() {
        return businessFactors;
    }

    public void setBusinessFactors(Double businessFactors) {
        this.businessFactors = businessFactors;
    }

    public Double getMenageFactors() {
        return menageFactors;
    }

    public void setMenageFactors(Double menageFactors) {
        this.menageFactors = menageFactors;
    }

    public Double getCapitalFactors() {
        return capitalFactors;
    }

    public void setCapitalFactors(Double capitalFactors) {
        this.capitalFactors = capitalFactors;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}