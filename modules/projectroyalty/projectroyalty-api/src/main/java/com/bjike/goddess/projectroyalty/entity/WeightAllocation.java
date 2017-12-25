package com.bjike.goddess.projectroyalty.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 项目提成权重分配
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-06-07 09:12 ]
 * @Description: [ 项目提成权重分配 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "projectroyalty_weightallocation")
public class WeightAllocation extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目名称
     */
    @Column(name = "project", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String project;

    /**
     * 完工时间(月)
     */
    @Column(name = "complete", nullable = false, columnDefinition = "INT(11)   COMMENT '完工时间(月)'")
    private Integer complete;

    /**
     * 合同金额(万)
     */
    @Column(name = "money", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '合同金额(万)'")
    private Double money;

    /**
     * 回款周期(月)
     */
    @Column(name = "cycle", nullable = false, columnDefinition = "INT(11)   COMMENT '回款周期(月)'")
    private Integer cycle;

    /**
     * 重要性
     */
    @Column(name = "importance", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '重要性'")
    private Double importance;

    /**
     * 难易难度
     */
    @Column(name = "difficulty", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '难易难度'")
    private Double difficulty;

    /**
     * 毛利率
     */
    @Column(name = "rate", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '毛利率'")
    private Double rate;

    /**
     * 业务提成平均比例
     */
    @Column(name = "business", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '业务提成平均比例'")
    private Double business;

    /**
     * 管理提成平均比例
     */
    @Column(name = "manage", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '管理提成平均比例'")
    private Double manage;

    /**
     * 资金方平均比例
     */
    @Column(name = "capital", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '资金方平均比例'")
    private Double capital;

    /**
     * 员工持股平均比例
     */
    @Column(name = "staff", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '员工持股平均比例'")
    private Double staff;

    /**
     * 公司风控备用金平均比例
     */
    @Column(name = "risk", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '公司风控备用金平均比例'")
    private Double risk;

    /**
     * 利润额
     */
    @Column(name = "profit", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '利润额'")
    private Double profit;

    /**
     * 目前比例总和
     */
    @Column(name = "proportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '目前比例总和'")
    private Double proportion;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 目标或实际
     */
    @Column(name = "is_plan", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '目标或实际'")
    private Boolean plan;


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Double getImportance() {
        return importance;
    }

    public void setImportance(Double importance) {
        this.importance = importance;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double difficulty) {
        this.difficulty = difficulty;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
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

    public Double getStaff() {
        return staff;
    }

    public void setStaff(Double staff) {
        this.staff = staff;
    }

    public Double getRisk() {
        return risk;
    }

    public void setRisk(Double risk) {
        this.risk = risk;
    }

    public Double getProfit() {
        return profit;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getPlan() {
        return plan;
    }

    public void setPlan(Boolean plan) {
        this.plan = plan;
    }
}