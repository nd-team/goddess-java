package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import com.bjike.goddess.qualifications.enums.HandleStatus;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 资质办理进度汇总
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-20 10:39 ]
 * @Description: [ 资质办理进度汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class    QualificationsCollectTO extends BaseTO {

    /**
     * 资质名称
     */
    @NotBlank(message = "资质名称不能为空", groups = {ADD.class})
    private String qualifications;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空", groups = {ADD.class})
    private String company;

    /**
     * 认证时间
     */
    @NotBlank(message = "认证时间不能为空", groups = {ADD.class, EDIT.class})
    private String attestation;

    /**
     * 办理开始时间
     */
    @NotBlank(message = "办理开始时间不能为空", groups = {ADD.class, EDIT.class})
    private String handleTime;

    /**
     * 计划结束时间
     */
    @NotBlank(message = "计划结束时间不能为空", groups = {ADD.class, EDIT.class})
    private String endTime;

    /**
     * 负责经办人
     */
    @NotBlank(message = "负责经办人不能为空", groups = {ADD.class, EDIT.class})
    private String director;


    /**
     * 办理费用(元)
     */
    @NotNull(message = "办理费用(元)不能为空", groups = {ADD.class, EDIT.class})
    private Double handleCost;

    /**
     * 年检费用(元)
     */
    @NotNull(message = "年检费用(元)不能为空", groups = {ADD.class, EDIT.class})
    private Double renewal;

    /**
     * 办理状态
     */
    @NotNull(message = "办理状态不能为空", groups = {ADD.class, EDIT.class})
    private HandleStatus handle;

    /**
     * 证书
     */
    @NotBlank(message = "证书不能为空", groups = {ADD.class, EDIT.class})
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