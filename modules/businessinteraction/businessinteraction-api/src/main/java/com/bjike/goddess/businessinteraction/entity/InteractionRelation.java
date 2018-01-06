package com.bjike.goddess.businessinteraction.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;


/**
 * 公司信息
 *
 * @Author: [ lijuntao ]
 * @Date: [ 2018-01-05 08:58 ]
 * @Description: [ 公司信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "businessinteraction_interactionrelation")
public class InteractionRelation extends BaseEntity {

    /**
     * 互动信息更新时间
     */
    @Column(name = "interactiveInfoDate", nullable = false, columnDefinition = "DATE   COMMENT '互动信息更新时间'")
    private LocalDate interactiveInfoDate;

    /**
     * 地区
     */
    @Column(name = "area", columnDefinition = "VARCHAR(255)   COMMENT '地区'")
    private String area;

    /**
     * 公司名称
     */
    @Column(name = "companyName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司名称'")
    private String companyName;

    /**
     * 公司电话
     */
    @Column(name = "companyTel", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '公司电话'")
    private String companyTel;

    /**
     * 公司邮箱
     */
    @Column(name = "companyEmail", columnDefinition = "VARCHAR(255)   COMMENT '公司邮箱'")
    private String companyEmail;

    /**
     * 公司主页
     */
    @Column(name = "companyMajorPage", columnDefinition = "VARCHAR(255)   COMMENT '公司主页'")
    private String companyMajorPage;

    /**
     * 公司业务方向
     */
    @Column(name = "companyBussWay",  columnDefinition = "VARCHAR(255)   COMMENT '公司业务方向'")
    private String companyBussWay;

    /**
     * 公司微信号
     */
    @Column(name = "companyWebchat",  columnDefinition = "VARCHAR(255)   COMMENT '公司微信号'")
    private String companyWebchat;

    /**
     * 公司公众号
     */
    @Column(name = "companyPublic",  columnDefinition = "VARCHAR(255)   COMMENT '公司公众号'")
    private String companyPublic;

    /**
     * 公司QQ号
     */
    @Column(name = "companyQQ",  columnDefinition = "VARCHAR(255)   COMMENT '公司QQ号'")
    private String companyQQ;

    /**
     * 公司论坛
     */
    @Column(name = "companyTalk",  columnDefinition = "VARCHAR(255)   COMMENT '公司论坛'")
    private String companyTalk;


    public LocalDate getInteractiveInfoDate() {
        return interactiveInfoDate;
    }

    public void setInteractiveInfoDate(LocalDate interactiveInfoDate) {
        this.interactiveInfoDate = interactiveInfoDate;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyMajorPage() {
        return companyMajorPage;
    }

    public void setCompanyMajorPage(String companyMajorPage) {
        this.companyMajorPage = companyMajorPage;
    }

    public String getCompanyBussWay() {
        return companyBussWay;
    }

    public void setCompanyBussWay(String companyBussWay) {
        this.companyBussWay = companyBussWay;
    }

    public String getCompanyWebchat() {
        return companyWebchat;
    }

    public void setCompanyWebchat(String companyWebchat) {
        this.companyWebchat = companyWebchat;
    }

    public String getCompanyPublic() {
        return companyPublic;
    }

    public void setCompanyPublic(String companyPublic) {
        this.companyPublic = companyPublic;
    }

    public String getCompanyQQ() {
        return companyQQ;
    }

    public void setCompanyQQ(String companyQQ) {
        this.companyQQ = companyQQ;
    }

    public String getCompanyTalk() {
        return companyTalk;
    }

    public void setCompanyTalk(String companyTalk) {
        this.companyTalk = companyTalk;
    }
}