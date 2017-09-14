package com.bjike.goddess.projectroyalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.projectroyalty.enums.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 项目提成权重分配表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-09-14 01:55 ]
 * @Description: [ 项目提成权重分配表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectroyalty_weightal")
public class Weightal extends BaseEntity {

    /**
     * 确定项目提成分配比例时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATETIME   COMMENT '确定项目提成分配比例时间'")
    private LocalDateTime time;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 内部项目名称
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '内部项目名称'")
    private String project;

    /**
     * 是否立项
     */
    @Column(name = "isBuild", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否立项'")
    private Boolean isBuild;

    /**
     * 立项时间
     */
    @Column(name = "buildTime", nullable = false, columnDefinition = "DATETIME   COMMENT '立项时间'")
    private LocalDateTime buildTime;

    /**
     * 是否完工
     */
    @Column(name = "isComplete", nullable = false, columnDefinition = "TINYINT(1)  COMMENT '是否完工'")
    private Boolean isComplete;

    /**
     * 类型
     */
    @Column(name = "type", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '类型'")
    private Type type;

    /**
     * 完工时间（月）
     */
    @Column(name = "month", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '完工时间（月）'")
    private Integer month;

    /**
     * 合同金额
     */
    @Column(name = "contract", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合同金额'")
    private Double contract;

    /**
     * 回款周期
     */
    @Column(name = "", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '回款周期'")
    private Integer collection;

    /**
     * 重要性
     */
    @Column(name = "importance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '重要性'")
    private Double importance;

    /**
     * 难易程度
     */
    @Column(name = "facility", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '难易程度'")
    private Double facility;

    /**
     * 毛利率
     */
    @Column(name = "ratio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '毛利率'")
    private Double ratio;

    /**
     * 利润额
     */
    @Column(name = "profit", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '利润额'")
    private Double profit;

    /**
     * 所选方案
     */
    @Column(name = "program", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '所选方案'")
    private String program;

    /**
     * 干股和公司预留利润总比例
     */
    @Column(name = "totalRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '干股和公司预留利润总比例'")
    private Double totalRatio;

    /**
     * 干股和公司预留利润因素量
     */
    @Column(name = "profitFactors", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '干股和公司预留利润因素量'")
    private Double profitFactors;

    /**
     * 干股和公司预留利润综合比例
     */
    @Column(name = "compreRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '干股和公司预留利润综合比例'")
    private Double compreRatio;

    /**
     * 调整后的干股和公司预留利润综合比例
     */
    @Column(name = "adjCompreRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '调整后的干股和公司预留利润综合比例'")
    private Double adjCompreRatio;

    /**
     * 干股和公司预留利润提成额
     */
    @Column(name = "amountProfit", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '干股和公司预留利润提成额'")
    private Double amountProfit;

    /**
     * 业务提成总比例
     */
    @Column(name = "businessRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务提成总比例'")
    private Double businessRatio;

    /**
     * 业务提成因素量
     */
    @Column(name = "businessFactors", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务提成因素量'")
    private Double businessFactors;

    /**
     * 业务提成综合比例
     */
    @Column(name = "busCompreRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务提成综合比例'")
    private Double busCompreRatio;

    /**
     * 调整后的业务提成综合比例
     */
    @Column(name = "adjBusCompRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '调整后的业务提成综合比例'")
    private Double adjBusCompRatio;

    /**
     * 业务提成额
     */
    @Column(name = "business", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务提成额'")
    private Double business;

    /**
     * 管理提成总比例
     */
    @Column(name = "menageRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理提成总比例'")
    private Double menageRatio;

    /**
     * 管理提成因素量
     */
    @Column(name = "menageFactors", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理提成因素量'")
    private Double menageFactors;

    /**
     * 管理提成综合比例
     */
    @Column(name = "meCompreRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理提成综合比例'")
    private Double meCompreRatio;

    /**
     * 调整后的管理提成综合比例
     */
    @Column(name = "adjMeCompreRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '调整后的管理提成综合比例'")
    private Double adjMeCompreRatio;

    /**
     * 管理提成额
     */
    @Column(name = "menage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理提成额'")
    private Double menage;

    /**
     * 资金方总比例
     */
    @Column(name = "capitalRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金方总比例'")
    private Double capitalRatio;

    /**
     * 资金方因素量
     */
    @Column(name = "capitalFactors", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金方因素量'")
    private Double capitalFactors;

    /**
     * 资金方综合比例
     */
    @Column(name = "caCompreRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金方综合比例'")
    private Double caCompreRatio;

    /**
     * 调整后的资金方综合比例
     */
    @Column(name = "adjCaCompreRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '调整后的资金方综合比例'")
    private Double adjCaCompreRatio;

    /**
     * 资金方提成额
     */
    @Column(name = "capital", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金方提成额'")
    private Double capital;

    /**
     * 目前比例总和
     */
    @Column(name = "allRatio", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目前比例总和'")
    private Double allRatio;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

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

    public LocalDateTime getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(LocalDateTime buildTime) {
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

    public Double getTotalRatio() {
        return totalRatio;
    }

    public void setTotalRatio(Double totalRatio) {
        this.totalRatio = totalRatio;
    }

    public Double getProfitFactors() {
        return profitFactors;
    }

    public void setProfitFactors(Double profitFactors) {
        this.profitFactors = profitFactors;
    }

    public Double getCompreRatio() {
        return compreRatio;
    }

    public void setCompreRatio(Double compreRatio) {
        this.compreRatio = compreRatio;
    }

    public Double getAdjCompreRatio() {
        return adjCompreRatio;
    }

    public void setAdjCompreRatio(Double adjCompreRatio) {
        this.adjCompreRatio = adjCompreRatio;
    }

    public Double getAmountProfit() {
        return amountProfit;
    }

    public void setAmountProfit(Double amountProfit) {
        this.amountProfit = amountProfit;
    }

    public Double getBusinessRatio() {
        return businessRatio;
    }

    public void setBusinessRatio(Double businessRatio) {
        this.businessRatio = businessRatio;
    }

    public Double getBusinessFactors() {
        return businessFactors;
    }

    public void setBusinessFactors(Double businessFactors) {
        this.businessFactors = businessFactors;
    }

    public Double getBusCompreRatio() {
        return busCompreRatio;
    }

    public void setBusCompreRatio(Double busCompreRatio) {
        this.busCompreRatio = busCompreRatio;
    }

    public Double getAdjBusCompRatio() {
        return adjBusCompRatio;
    }

    public void setAdjBusCompRatio(Double adjBusCompRatio) {
        this.adjBusCompRatio = adjBusCompRatio;
    }

    public Double getBusiness() {
        return business;
    }

    public void setBusiness(Double business) {
        this.business = business;
    }

    public Double getMenageRatio() {
        return menageRatio;
    }

    public void setMenageRatio(Double menageRatio) {
        this.menageRatio = menageRatio;
    }

    public Double getMenageFactors() {
        return menageFactors;
    }

    public void setMenageFactors(Double menageFactors) {
        this.menageFactors = menageFactors;
    }

    public Double getMeCompreRatio() {
        return meCompreRatio;
    }

    public void setMeCompreRatio(Double meCompreRatio) {
        this.meCompreRatio = meCompreRatio;
    }

    public Double getAdjMeCompreRatio() {
        return adjMeCompreRatio;
    }

    public void setAdjMeCompreRatio(Double adjMeCompreRatio) {
        this.adjMeCompreRatio = adjMeCompreRatio;
    }

    public Double getMenage() {
        return menage;
    }

    public void setMenage(Double menage) {
        this.menage = menage;
    }

    public Double getCapitalRatio() {
        return capitalRatio;
    }

    public void setCapitalRatio(Double capitalRatio) {
        this.capitalRatio = capitalRatio;
    }

    public Double getCapitalFactors() {
        return capitalFactors;
    }

    public void setCapitalFactors(Double capitalFactors) {
        this.capitalFactors = capitalFactors;
    }

    public Double getCaCompreRatio() {
        return caCompreRatio;
    }

    public void setCaCompreRatio(Double caCompreRatio) {
        this.caCompreRatio = caCompreRatio;
    }

    public Double getAdjCaCompreRatio() {
        return adjCaCompreRatio;
    }

    public void setAdjCaCompreRatio(Double adjCaCompreRatio) {
        this.adjCaCompreRatio = adjCaCompreRatio;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public Double getAllRatio() {
        return allRatio;
    }

    public void setAllRatio(Double allRatio) {
        this.allRatio = allRatio;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}