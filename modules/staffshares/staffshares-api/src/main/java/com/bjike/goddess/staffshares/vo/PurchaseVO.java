package com.bjike.goddess.staffshares.vo;

import com.bjike.goddess.staffshares.enums.Status1;

/**
 * 干股申购表表现层对象
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-05 09:51 ]
 * @Description: [ 干股申购表表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PurchaseVO {

    /**
     * id
     */
    private String id;
    /**
     * 状态
     */
    private Status1 status;

    /**
     * 申购人
     */
    private String name;

    /**
     * 申购时间
     */
    private String time;

    /**
     * 地区
     */
    private String area;

    /**
     * 项目
     */
    private String project;

    /**
     * 部门
     */
    private String department;

    /**
     * 岗位
     */
    private String position;

    /**
     * 在职时间（月数)
     */
    private int months;

    /**
     * 出售人
     */
    private String sellName;

    /**
     * 购入股数
     */
    private Long purchaseNum;

    /**
     * 应付额
     */
    private Double money;

    /**
     * 目前处罚记录次数
     */
    private int penalty;

    /**
     * 目前获得奖励次数
     */
    private int reward;

    /**
     * 各项晋升的次数
     */
    private int promotion;

    /**
     * 备注
     */
    private String remark;

    /**
     * 财务运营部负责人
     */
    private String financial;

    /**
     * 财务运营部审核意见
     */
    private String opinion;

    /**
     * 规划模块
     */
    private String planModule;

    /**
     * 规划模块审核意见
     */
    private String opinion1;

    /**
     * 总经办
     */
    private String manager;

    /**
     * 总经办审核意见
     */
    private String opinion2;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
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

    public Long getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Long purchaseNum) {
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
}