package com.bjike.goddess.archive.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

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
    @NotNull(message = "姓名不能为空", groups = {ADD.class, EDIT.class})
    private String username;

    /**
     * 劳动关系类型
     */
    @NotNull(message = "劳动关系类型不能为空", groups = {ADD.class, EDIT.class})
    private String laborId;

    /**
     * 社保购买类型
     */
    @NotNull(message = "社保购买类型不能为空", groups = {ADD.class, EDIT.class})
    private String socialId;

    /**
     * 资质证书1
     */
    @NotNull(message = "资质证书1不能为空", groups = {ADD.class, EDIT.class})
    private String certificateOne;

    /**
     * 查询网站链接1
     */
    @NotNull(message = "查询网站链接1不能为空", groups = {ADD.class, EDIT.class})
    private String websiteOne;

    /**
     * 证件有效期限1
     */
    @NotNull(message = "证件有效期限1不能为空", groups = {ADD.class, EDIT.class})
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

    public String getLaborId() {
        return laborId;
    }

    public void setLaborId(String laborId) {
        this.laborId = laborId;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
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