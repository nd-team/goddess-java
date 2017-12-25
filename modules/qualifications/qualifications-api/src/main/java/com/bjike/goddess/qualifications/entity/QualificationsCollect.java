package com.bjike.goddess.qualifications.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;
import com.bjike.goddess.qualifications.enums.HandleStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 资质办理进度汇总
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-20 10:39 ]
 * @Description: [ 资质办理进度汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "qualifications_collect")
public class QualificationsCollect extends BaseEntity {

    /**
     * 资质名称
     */
    @Column(name = "qualifications", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '资质名称'")
    private String qualifications;

    /**
     * 公司名称
     */
    @Column(name = "company", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String company;

    /**
     * 认证时间
     */
    @Column(name = "attestation", nullable = false, columnDefinition = "DATE   COMMENT '认证时间'")
    private LocalDate attestation;

    /**
     * 办理开始时间
     */
    @Column(name = "handleTime", nullable = false, columnDefinition = "DATE   COMMENT '办理开始时间'")
    private LocalDate handleTime;

    /**
     * 计划结束时间
     */
    @Column(name = "endTime", nullable = false, columnDefinition = "DATE   COMMENT '计划结束时间'")
    private LocalDate endTime;

    /**
     * 负责经办人
     */
    @Column(name = "director", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '负责经办人'")
    private String director;

    /**
     * 办理状态
     */
    @Column(name = "handle", nullable = false, columnDefinition = "TINYINT(2)   COMMENT '办理状态'")
    private HandleStatus handle;

    /**
     * 证书
     */
    @Column(name = "certificate", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '证书'")
    private String certificate;

    /**
     * 办理费用(元)
     */
    @Column(name = "handleCost", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '办理费用(元)'")
    private Double handleCost;

    /**
     * 年检费用(元)
     */
    @Column(name = "renewal", nullable = false, columnDefinition = "DECIMAL(10,2)   COMMENT '年审费用(元)'")
    private Double renewal;

    /**
     * 办理使用材料
     */
    @Column(name = "material", columnDefinition = "VARCHAR(255)   COMMENT '办理使用材料'")
    private String material;

    /**
     * 付款记录登记
     */
    @Column(name = "record", columnDefinition = "VARCHAR(255)   COMMENT '付款记录登记'")
    private String record;

    /**
     * 填写人
     */
    @Column(name = "writer", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '填写人'")
    private String writer;


    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public LocalDate getAttestation() {
        return attestation;
    }

    public void setAttestation(LocalDate attestation) {
        this.attestation = attestation;
    }

    public LocalDate getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(LocalDate handleTime) {
        this.handleTime = handleTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public HandleStatus getHandle() {
        return handle;
    }

    public void setHandle(HandleStatus handle) {
        this.handle = handle;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Double getHandleCost() {
        return handleCost;
    }

    public void setHandleCost(Double handleCost) {
        this.handleCost = handleCost;
    }

    public Double getRenewal() {
        return renewal;
    }

    public void setRenewal(Double renewal) {
        this.renewal = renewal;
    }
}