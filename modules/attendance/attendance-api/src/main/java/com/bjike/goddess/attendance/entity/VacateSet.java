package com.bjike.goddess.attendance.entity;

import com.bjike.goddess.attendance.enums.Status;
import com.bjike.goddess.attendance.enums.VacateType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 请假设置
 *
 * @Author: [ chenjunhao ]
 * @Date: [ 2017-10-12 01:46 ]
 * @Description: [ 请假设置 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "attendance_vacateset")
public class VacateSet extends BaseEntity {

    /**
     * 请假类型
     */
    @Column(name = "vacateType", nullable = false, unique = true, columnDefinition = "TINYINT(2)   COMMENT '请假类型'")
    private VacateType vacateType;

    /**
     * 是否有带薪天数
     */
    @Column(name = "is_payDay", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否有带薪天数'")
    private Boolean payDay;

    /**
     * 请假周期
     */
    @Column(name = "cycle", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '请假周期'")
    private String cycle;

    /**
     * 最大可选天数
     */
    @Column(name = "maxDay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '最大可选天数'")
    private Integer maxDay;

    /**
     * 总可享受带薪天数
     */
    @Column(name = "allPayDay", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '总可享受带薪天数'")
    private Integer allPayDay;

    /**
     * 带薪占比(%)
     */
    @Column(name = "proportion", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '带薪占比(%)'")
    private Double proportion;

    /**
     * 是否需要附件
     */
    @Column(name = "is_attachment", nullable = false, columnDefinition = "TINYINT(1)   COMMENT '是否需要附件'")
    private Boolean attachment;

    /**
     * 附件要求
     */
    @Column(name = "attachmentRequest", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '附件要求'")
    private String attachmentRequest;

    /**
     * 创建人
     */
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '创建人'")
    private String name;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '状态'")
    private Status status;


    public VacateType getVacateType() {
        return vacateType;
    }

    public void setVacateType(VacateType vacateType) {
        this.vacateType = vacateType;
    }

    public Boolean getPayDay() {
        return payDay;
    }

    public void setPayDay(Boolean payDay) {
        this.payDay = payDay;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public Integer getMaxDay() {
        return maxDay;
    }

    public void setMaxDay(Integer maxDay) {
        this.maxDay = maxDay;
    }

    public Integer getAllPayDay() {
        return allPayDay;
    }

    public void setAllPayDay(Integer allPayDay) {
        this.allPayDay = allPayDay;
    }

    public Double getProportion() {
        return proportion;
    }

    public void setProportion(Double proportion) {
        this.proportion = proportion;
    }

    public Boolean getAttachment() {
        return attachment;
    }

    public void setAttachment(Boolean attachment) {
        this.attachment = attachment;
    }

    public String getAttachmentRequest() {
        return attachmentRequest;
    }

    public void setAttachmentRequest(String attachmentRequest) {
        this.attachmentRequest = attachmentRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}