package com.bjike.goddess.lendreimbursement.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 报销审核日志表
 *
 * @Author: [ tanghaixiang ]
 * @Date: [ 2017-04-11 05:48 ]
 * @Description: [ 报销审核日志表 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "lendreimbursement_reimburseauditlog")
public class ReimburseAuditLog extends BaseEntity {

    /**
     * 审核人
     */
    @Column(name = "userName", columnDefinition = "VARCHAR(255)   COMMENT '审核人'")
    private String userName;

    /**
     * 审核人编号
     */
    @Column(name = "empNum", columnDefinition = "VARCHAR(255)   COMMENT '审核人编号'")
    private String empNum;

    /**
     * 审核人职位
     */
    @Column(name = "position", columnDefinition = "VARCHAR(255)   COMMENT '审核人职位'")
    private String position;

    /**
     * 审核意见
     */
    @Column(name = "content", columnDefinition = "VARCHAR(255)   COMMENT '审核意见'")
    private String content;

    /**
     * 审核状态
     */
    @Column(name = "auditStatus", columnDefinition = "VARCHAR(255)   COMMENT '审核状态'")
    private String auditStatus;

    /**
     * 审核时间
     */
    @Column(name = "auditTime", columnDefinition = "DATE   COMMENT '审核时间'")
    private LocalDate auditTime;

    /**
     * 报销记录Id
     */
    @Column(name = "reimrecordId", columnDefinition = "VARCHAR(255)   COMMENT '报销记录Id'")
    private String reimrecordId;




    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmpNum() {
        return empNum;
    }

    public void setEmpNum(String empNum) {
        this.empNum = empNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public LocalDate getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(LocalDate auditTime) {
        this.auditTime = auditTime;
    }

    public String getReimrecordId() {
        return reimrecordId;
    }

    public void setReimrecordId(String reimrecordId) {
        this.reimrecordId = reimrecordId;
    }


}