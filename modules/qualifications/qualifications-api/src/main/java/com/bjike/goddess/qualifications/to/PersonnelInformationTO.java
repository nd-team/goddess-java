package com.bjike.goddess.qualifications.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * 人员信息资料
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:24 ]
 * @Description: [ 人员信息资料 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PersonnelInformationTO extends BaseTO {

    /**
     * 人员分类
     */
    @NotNull(message = "人员分类不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 基本信息
     */
    @NotNull(message = "基本信息不能为空", groups = {ADD.class, EDIT.class})
    private String baseInfo;

    /**
     * 社保信息
     */
    @NotNull(message = "社保信息不能为空", groups = {ADD.class, EDIT.class})
    private String social;

    /**
     * 人员简历
     */
    @NotNull(message = "人员简历不能为空", groups = {ADD.class, EDIT.class})
    private String resume;

    /**
     * 人员基本证件
     */
    @NotNull(message = "人员基本证件不能为空", groups = {ADD.class, EDIT.class})
    private String certificate;

    /**
     * 证书信息分类
     */
    @NotNull(message = "证书信息分类不能为空", groups = {ADD.class, EDIT.class})
    private String classify;

    /**
     * 人员认证信息
     */
    @NotNull(message = "人员认证信息不能为空", groups = {ADD.class, EDIT.class})
    private String attestation;

    /**
     * 其他
     */
    private String other;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(String baseInfo) {
        this.baseInfo = baseInfo;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getAttestation() {
        return attestation;
    }

    public void setAttestation(String attestation) {
        this.attestation = attestation;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}