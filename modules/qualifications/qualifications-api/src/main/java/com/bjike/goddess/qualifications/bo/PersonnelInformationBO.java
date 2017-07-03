package com.bjike.goddess.qualifications.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 人员信息资料业务传输对象
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-03-31 07:24 ]
 * @Description: [ 人员信息资料业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PersonnelInformationBO extends BaseBO {

    /**
     * 人员分类
     */
    private String type;

    /**
     * 基本信息
     */
    private String baseInfo;

    /**
     * 社保信息
     */
    private String social;

    /**
     * 人员简历
     */
    private String resume;

    /**
     * 人员基本证件
     */
    private String certificate;

    /**
     * 证书信息分类
     */
    private String classify;

    /**
     * 人员认证信息
     */
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