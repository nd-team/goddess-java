package com.bjike.goddess.moneyprepare.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 申请和审批
 *
 * @Author: [ zhuangkaiqin ]
 * @Date: [ 2017-07-08 04:16 ]
 * @Description: [ 申请和审批 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "moneyprepare_applyandexamination")
public class ApplyAndExamination extends BaseEntity {

    /**
     * 名称
     */
    @Column(name = "tableName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '名称'")
    private String tableName;

    /**
     * 申请人
     */
    @Column(name = "apply", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '申请人'")
    private String apply;

    /**
     * 申请时间
     */
    @Column(name = "applyTime", nullable = true, columnDefinition = "DATETIME   COMMENT '申请时间'")
    private LocalDateTime applyTime;

    /**
     * 审批人
     */
    @Column(name = "examination", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '审批人'")
    private String examination;

    /**
     * 审批时间
     */
    @Column(name = "examinationTime", nullable = true, columnDefinition = "DATETIME   COMMENT '审批时间'")
    private LocalDateTime examinationTime;

    /**
     * 审批意见
     */
    @Column(name = "examinationOpinion", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '审批意见'")
    private String examinationOpinion;

    /**
     * 审批状态
     */
    @Column(name = "examinationStatus", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '审批状态'")
    private boolean examinationStatus;

    /**
     * 备注
     */
    @Column(name = "remark", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;

    /**
     * 记录
     */
    @Column(name = "record", nullable = true, columnDefinition = "VARCHAR(255)   COMMENT '记录'")
    private String record;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getApply() {
        return apply;
    }

    public void setApply(String apply) {
        this.apply = apply;
    }

    public LocalDateTime getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(LocalDateTime applyTime) {
        this.applyTime = applyTime;
    }

    public String getExamination() {
        return examination;
    }

    public void setExamination(String examination) {
        this.examination = examination;
    }

    public LocalDateTime getExaminationTime() {
        return examinationTime;
    }

    public void setExaminationTime(LocalDateTime examinationTime) {
        this.examinationTime = examinationTime;
    }

    public String getExaminationOpinion() {
        return examinationOpinion;
    }

    public void setExaminationOpinion(String examinationOpinion) {
        this.examinationOpinion = examinationOpinion;
    }

    public boolean getExaminationStatus() {
        return examinationStatus;
    }

    public void setExaminationStatus(boolean examinationStatus) {
        this.examinationStatus = examinationStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}