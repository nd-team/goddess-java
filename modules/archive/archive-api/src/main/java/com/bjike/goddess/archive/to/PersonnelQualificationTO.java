package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 人员资质
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-04-12 04:01 ]
 * @Description: [ 人员资质 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PersonnelQualificationTO extends BaseTO {

    /**
     * 姓名
     */
    private String username;

    /**
     * 劳动关系类型
     */
    private String labor_id;

    /**
     * 社保购买类型
     */
    private String social_id;

    /**
     * 资质证书1
     */
    private String certificateOne;

    /**
     * 查询网站链接1
     */
    private String websiteOne;

    /**
     * 证件有效期限1
     */
    private String effectiveOne;

    /**
     * 资质证书2
     */
    private String certificateTwo;

    /**
     * 查询网站链接2
     */
    private String websiteTwo;

    /**
     * 证件有效期限2
     */
    private String effectiveTwo;

    /**
     * 备注信息
     */
    private String remark;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLabor_id() {
        return labor_id;
    }

    public void setLabor_id(String labor_id) {
        this.labor_id = labor_id;
    }

    public String getSocial_id() {
        return social_id;
    }

    public void setSocial_id(String social_id) {
        this.social_id = social_id;
    }

    public String getCertificateOne() {
        return certificateOne;
    }

    public void setCertificateOne(String certificateOne) {
        this.certificateOne = certificateOne;
    }

    public String getWebsiteOne() {
        return websiteOne;
    }

    public void setWebsiteOne(String websiteOne) {
        this.websiteOne = websiteOne;
    }

    public String getEffectiveOne() {
        return effectiveOne;
    }

    public void setEffectiveOne(String effectiveOne) {
        this.effectiveOne = effectiveOne;
    }

    public String getCertificateTwo() {
        return certificateTwo;
    }

    public void setCertificateTwo(String certificateTwo) {
        this.certificateTwo = certificateTwo;
    }

    public String getWebsiteTwo() {
        return websiteTwo;
    }

    public void setWebsiteTwo(String websiteTwo) {
        this.websiteTwo = websiteTwo;
    }

    public String getEffectiveTwo() {
        return effectiveTwo;
    }

    public void setEffectiveTwo(String effectiveTwo) {
        this.effectiveTwo = effectiveTwo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}