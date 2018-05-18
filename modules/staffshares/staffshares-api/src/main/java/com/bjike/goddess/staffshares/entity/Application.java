package com.bjike.goddess.staffshares.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 干股代表申请
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-08-04 10:27 ]
 * @Description: [ 干股代表申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffshares_application")
public class Application extends BaseEntity {

    /**
     * 持股人
     */
    @Column(name = "shareholder", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '持股人'")
    private String shareholder;

    /**
     * 申请时间
     */
    @Column(name = "time", nullable = false, columnDefinition = "DATETIME   COMMENT '申请时间'")
    private LocalDateTime time;

    /**
     * 地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组/部门
     */
    @Column(name = "department", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '项目组/部门'")
    private String department;

    /**
     * 岗位
     */
    @Column(name = "position", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '岗位'")
    private String position;

    /**
     * 在职时间（月数）
     */
    @Column(name = "", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '在职时间（月数）'")
    private int months;

    /**
     * 申请原因
     */
    @Column(name = "reason", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '申请原因'")
    private String reason;

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

    /**
     * 投票数
     */
    @Column(name = "", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '投票数'")
    private int votes;

    /**
     * 状态
     */
    @Column(name = "is_situation", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '状态' ")
    private Boolean situation;

    /**
     * 标记
     */
    @Column(name = "tar", columnDefinition = "TINYINT(1)  DEFAULT 0  COMMENT '状态' ")
    private Boolean tar;


    public String getShareholder() {
        return shareholder;
    }

    public void setShareholder(String shareholder) {
        this.shareholder = shareholder;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Boolean getSituation() {
        return situation;
    }

    public void setSituation(Boolean situation) {
        this.situation = situation;
    }

    public Boolean getTar() {
        return tar;
    }

    public void setTar(Boolean tar) {
        this.tar = tar;
    }
}