package com.bjike.goddess.attainment.entity;

import com.bjike.goddess.attainment.enums.AuditType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDateTime;


/**
 * 调研计划
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-06 10:41 ]
 * @Description: [ 调研计划 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attainment_survey_plan")
public class SurveyPlan extends BaseEntity {

    /**
     * 编号
     */
    @Column(name = "serialNumber", unique = true, nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '编号'")
    private String serialNumber;

    /**
     * 需求
     */
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "demand_id", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '需求'")
    private SurveyDemand demand;

    /**
     * 调研计划开始时间
     */
    @Column(name = "startTime", nullable = false, columnDefinition = "DATETIME   COMMENT '调研计划开始时间'")
    private LocalDateTime startTime;

    /**
     * 调研计划结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATETIME   COMMENT '调研计划结束时间'")
    private LocalDateTime endTime;

    /**
     * 调研表制作完成时间
     */
    @Column(name = "finishTime", nullable = false, columnDefinition = "DATETIME   COMMENT '调研表制作完成时间'")
    private LocalDateTime finishTime;

    /**
     * 审核状态
     */
    @Column(name = "audit", nullable = false, columnDefinition = "TINYINT(2)  DEFAULT 0  COMMENT '审核状态'", insertable = false)
    private AuditType audit;

    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "VARCHAR(255)   COMMENT '备注'")
    private String remark;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public SurveyDemand getDemand() {
        return demand;
    }

    public void setDemand(SurveyDemand demand) {
        this.demand = demand;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public AuditType getAudit() {
        return audit;
    }

    public void setAudit(AuditType audit) {
        this.audit = audit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}