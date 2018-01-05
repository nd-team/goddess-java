package com.bjike.goddess.checkhost.entity;

import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 住宿申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 03:38 ]
 * @Description: [ 住宿申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "checkhost_stayapply")
public class StayApply extends BaseEntity {

    /**
     * 新入职员工姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '新入职员工姓名'")
    private String name;

    /**
     * 申请入住日期
     */
    @Column(name = "stayDate", columnDefinition = "DATE   COMMENT '申请入住日期'")
    private LocalDate stayDate;

    /**
     * 申请入住地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '申请入住地区'")
    private String area;

    /**
     * 申请入住宿舍
     */
    @Column(name = "stayDormitory", columnDefinition = "VARCHAR(255)   COMMENT '申请入住宿舍'")
    private String stayDormitory;
    /**
     * 入住床位
     */
    @Column(columnDefinition = "INT(5)   COMMENT '入住床位'")
    private Integer stayBed;

    /**
     * 床上3件套（件）
     */
    @Column(columnDefinition = "INT(5)   COMMENT '床上3件套（件）'")
    private Integer suit;

    /**
     * 被褥（件）
     */
    @Column(columnDefinition = "INT(5)   COMMENT '被褥（件）'")
    private Integer bedding;

    /**
     * 床垫
     */
    @Column(columnDefinition = "INT(5)   COMMENT '床垫'")
    private Integer mattress;

    /**
     * 宿舍钥匙
     */
    @Column(name = "stayKey", columnDefinition = "VARCHAR(255)  COMMENT '宿舍钥匙'")
    private String stayKey;

    /**
     * 申请入住原因
     */
    @Column(name = "stayCause", columnDefinition = "VARCHAR(255)   COMMENT '申请入住原因'")
    private String stayCause;

    /**
     * 住宿负责人
     */
    @Column(name = "stayHead", columnDefinition = "VARCHAR(255)   COMMENT '住宿负责人'")
    private String stayHead;

    /**
     * 福利模块负责人
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '福利模块负责人'")
    private String headAudit;
//    /**
//     * 福利模块负责人审核
//     */
//    @Column(columnDefinition = "VARCHAR(255) COMMENT '福利模块负责人审核'")
//    private String headAuditPass;
    /**
     * 新员工确认入住
     */
    @Column(name = "is_stay", columnDefinition = "TINYINT(2)  COMMENT '新员工确认入住'")
    private Boolean stay;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 审核状态
     */
    @Column(name = "checkStatus", columnDefinition = "TINYINT(2)   COMMENT '审核状态'")
    private CheckStatus checkStatus;
    /**
     * 离宿原因
     */
    @Column(name = "hostCause", columnDefinition = "VARCHAR(255)   COMMENT '离宿原因'")
    private String hostCause;
    /**
     * 离宿时间
     */
    @Column(name = "hostTime", columnDefinition = "DATE   COMMENT '离宿时间'")
    private LocalDate hostTime;
    /**
     * 收费金额
     */
    @Column(name = "tollMoney", columnDefinition = "DECIMAL(10,2)   COMMENT '收费金额'")
    private Double tollMoney;
    /**
     * 水电费，燃气费总额
     */
    @Column(name = "amount", columnDefinition = "DECIMAL(10,2)   COMMENT '水电费，燃气费总额'")
    private Double amount;

    /**
     * 合计总额（收费金额+水电费，燃气费总额）
     */
    @Column(name = "totalAmount", columnDefinition = "DECIMAL(10,2)   COMMENT '合计总额（收费金额+水电费，燃气费总额）'")
    private Double totalAmount;
    /**
     * 模块负责人审核
     */
    @Column(name = "moduleAudit", columnDefinition = "VARCHAR(255)   COMMENT '模块负责人审核'")
    private String moduleAudit;
    /**
     * 审核状态
     */
    @Column(name = "moduleCheckStatus", columnDefinition = "TINYINT(2)  COMMENT '审核状态'")
    private CheckStatus moduleCheckStatus;

    public String getHostCause() {
        return hostCause;
    }

    public void setHostCause(String hostCause) {
        this.hostCause = hostCause;
    }

    public LocalDate getHostTime() {
        return hostTime;
    }

    public void setHostTime(LocalDate hostTime) {
        this.hostTime = hostTime;
    }

    public Double getTollMoney() {
        return tollMoney;
    }

    public void setTollMoney(Double tollMoney) {
        this.tollMoney = tollMoney;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getModuleAudit() {
        return moduleAudit;
    }

    public void setModuleAudit(String moduleAudit) {
        this.moduleAudit = moduleAudit;
    }

    public CheckStatus getModuleCheckStatus() {
        return moduleCheckStatus;
    }

    public void setModuleCheckStatus(CheckStatus moduleCheckStatus) {
        this.moduleCheckStatus = moduleCheckStatus;
    }

    public Integer getStayBed() {
        return stayBed;
    }

    public void setStayBed(Integer stayBed) {
        this.stayBed = stayBed;
    }

    public Integer getSuit() {
        return suit;
    }

    public void setSuit(Integer suit) {
        this.suit = suit;
    }

    public Integer getBedding() {
        return bedding;
    }

    public void setBedding(Integer bedding) {
        this.bedding = bedding;
    }

    public Integer getMattress() {
        return mattress;
    }

    public void setMattress(Integer mattress) {
        this.mattress = mattress;
    }

    public String getStayKey() {
        return stayKey;
    }

    public void setStayKey(String stayKey) {
        this.stayKey = stayKey;
    }

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStayDate() {
        return stayDate;
    }

    public void setStayDate(LocalDate stayDate) {
        this.stayDate = stayDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStayDormitory() {
        return stayDormitory;
    }

    public void setStayDormitory(String stayDormitory) {
        this.stayDormitory = stayDormitory;
    }

    public String getStayCause() {
        return stayCause;
    }

    public void setStayCause(String stayCause) {
        this.stayCause = stayCause;
    }

    public String getStayHead() {
        return stayHead;
    }

    public void setStayHead(String stayHead) {
        this.stayHead = stayHead;
    }

    public String getHeadAudit() {
        return headAudit;
    }

    public void setHeadAudit(String headAudit) {
        this.headAudit = headAudit;
    }

//    public String getHeadAuditPass() {
//        return headAuditPass;
//    }
//
//    public void setHeadAuditPass(String headAuditPass) {
//        this.headAuditPass = headAuditPass;
//    }

    public Boolean getStay() {
        return stay;
    }

    public void setStay(Boolean stay) {
        this.stay = stay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}