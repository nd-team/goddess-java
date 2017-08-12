package com.bjike.goddess.staffshares.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.staffshares.enums.Status1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 干股申购表
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffshares_purchase")
public class Purchase extends BaseEntity {

    /**
     * 方案代码
     */
    @Column(name = "code", columnDefinition = "VARCHAR(255)   COMMENT '方案代码'")
    private String code;

    /**
     * 方案名称
     */
    @Column(name = "issueName", columnDefinition = "VARCHAR(255)   COMMENT '方案名称'")
    private String issueName;

    /**
     * 发行金额
     */
    @Column(name = "facevalue", columnDefinition = "DECIMAL(10,2)   COMMENT '票面价值'")
    private Double facevalue;

    /**
     * 发行价格
     */
    @Column(name = "price", columnDefinition = "DECIMAL(10,2)   COMMENT '发行价格'")
    private Double price;


    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '状态'")
    private Status1 status;

    /**
     * 申购人
     */
    @Column(name = "name", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '申购人'")
    private String name;

    /**
     * 申购时间
     */
    @Column(name = "time", nullable = true, columnDefinition = "DATETIME   COMMENT '申购时间'")
    private LocalDateTime time;

    /**
     * 地区
     */
    @Column(name = "area", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目
     */
    @Column(name = "project", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '项目'")
    private String project;

    /**
     * 部门
     */
    @Column(name = "department", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 在职时间（月数)
     */
    @Column(name = "months", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '在职时间（月数)'")
    private int months;

    /**
     * 出售人
     */
    @Column(name = "sellName", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '出售人'")
    private String sellName;

    /**
     * 购入股数
     */
    @Column(name = "purchaseNum", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '购入股数'")
    private int purchaseNum;

    /**
     * 应付额
     */
    @Column(name = "money", nullable = true, columnDefinition = "DECIMAL(10,2)   COMMENT '应付额'")
    private Double money;

    /**
     * 目前处罚记录次数
     */
    @Column(name = "penalty", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '目前处罚记录次数'")
    private int penalty;

    /**
     * 目前获得奖励次数
     */
    @Column(name = "reward", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '目前获得奖励次数'")
    private int reward;

    /**
     * 各项晋升的次数
     */
    @Column(name = "promotion", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '各项晋升的次数'")
    private int promotion;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 财务运营部负责人
     */
    @Column(name = "financial", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '财务运营部负责人'")
    private String financial;

    /**
     * 财务运营部审核意见
     */
    @Column(name = "opinion", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '财务运营部审核意见'")
    private String opinion;

    /**
     * 规划模块
     */
    @Column(name = "planModule", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '规划模块'")
    private String planModule;

    /**
     * 规划模块审核意见
     */
    @Column(name = "opinion1", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '规划模块审核意见'")
    private String opinion1;

    /**
     * 总经办
     */
    @Column(name = "manager", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '总经办'")
    private String manager;

    /**
     * 总经办审核意见
     */
    @Column(name = "opinion2", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '总经办审核意见'")
    private String opinion2;


    public Status1 getStatus() {
        return status;
    }

    public void setStatus(Status1 status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String getSellName() {
        return sellName;
    }

    public void setSellName(String sellName) {
        this.sellName = sellName;
    }

    public int getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(int purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFinancial() {
        return financial;
    }

    public void setFinancial(String financial) {
        this.financial = financial;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getPlanModule() {
        return planModule;
    }

    public void setPlanModule(String planModule) {
        this.planModule = planModule;
    }

    public String getOpinion1() {
        return opinion1;
    }

    public void setOpinion1(String opinion1) {
        this.opinion1 = opinion1;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getOpinion2() {
        return opinion2;
    }

    public void setOpinion2(String opinion2) {
        this.opinion2 = opinion2;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public Double getFacevalue() {
        return facevalue;
    }

    public void setFacevalue(Double facevalue) {
        this.facevalue = facevalue;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}