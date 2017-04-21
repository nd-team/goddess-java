package com.bjike.goddess.qualifications.vo;

import com.bjike.goddess.qualifications.enums.HandleStatus;

/**
 * 资质办理进度汇总表现层对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-20 10:39 ]
 * @Description: [ 资质办理进度汇总表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class QualificationsCollectVO {

    /**
     * id
     */
    private String id;
    /**
     * 资质名称
     */
    private String qualifications;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 认证时间
     */
    private String attestation;

    /**
     * 办理开始时间
     */
    private String handleTime;

    /**
     * 计划结束时间
     */
    private String endTime;

    /**
     * 负责经办人
     */
    private String director;


    /**
     * 办理费用(元)
     */
    private Double handleCost;

    /**
     * 年检费用(元)
     */
    private Double renewal;

    /**
     * 办理状态
     */
    private HandleStatus handle;

    /**
     * 证书
     */
    private String certificate;

    /**
     * 办理使用材料
     */
    private String material;

    /**
     * 付款记录登记
     */
    private String record;

    /**
     * 填写人
     */
    private String writer;

    /**
     * 修改时间
     */
    protected String modifyTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAttestation() {
        return attestation;
    }

    public void setAttestation(String attestation) {
        this.attestation = attestation;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
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