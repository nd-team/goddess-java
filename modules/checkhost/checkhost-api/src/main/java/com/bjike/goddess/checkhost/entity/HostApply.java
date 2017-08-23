package com.bjike.goddess.checkhost.entity;

import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 离宿申请
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 04:51 ]
 * @Description: [ 离宿申请 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "checkhost_hostapply")
public class HostApply extends BaseEntity {

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 项目组
     */
    @Column(name = "projectGroup", columnDefinition = "VARCHAR(255)   COMMENT '项目组'")
    private String projectGroup;

    /**
     * 宿舍地址
     */
    @Column(name = "address", columnDefinition = "VARCHAR(255)   COMMENT '宿舍地址'")
    private String address;

    /**
     * 员工姓名
     */
    @Column(name = "name", columnDefinition = "VARCHAR(255)   COMMENT '员工姓名'")
    private String name;

    /**
     * 员工编号
     */
    @Column(name = "num", columnDefinition = "VARCHAR(255)   COMMENT '员工编号'")
    private String num;
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
     * 是否归还钥匙
     */
    @Column(name = "is_returnKey", columnDefinition = "TINYINT(2)  COMMENT '是否归还钥匙'")
    private Boolean returnKey;
    /**
     * 三件套是否收费
     */
    @Column(name = "is_suitToll", columnDefinition = "TINYINT(2)  COMMENT '三件套是否收费'")
    private Boolean suitToll;
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

//    /**
//     * 模块责任人审核
//     */
//    @Column(name = "headAudit", columnDefinition = "VARCHAR(255)   COMMENT '模块责任人审核'")
//    private String headAudit;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 审核状态
     */
    @Column(name = "checkStatus", columnDefinition = "TINYINT(2)  COMMENT '审核状态'")
    private CheckStatus checkStatus;

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProjectGroup() {
        return projectGroup;
    }

    public void setProjectGroup(String projectGroup) {
        this.projectGroup = projectGroup;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

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

    public Boolean getReturnKey() {
        return returnKey;
    }

    public void setReturnKey(Boolean returnKey) {
        this.returnKey = returnKey;
    }

    public Boolean getSuitToll() {
        return suitToll;
    }

    public void setSuitToll(Boolean suitToll) {
        this.suitToll = suitToll;
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

//    public String getHeadAudit() {
//        return headAudit;
//    }
//
//    public void setHeadAudit(String headAudit) {
//        this.headAudit = headAudit;
//    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}