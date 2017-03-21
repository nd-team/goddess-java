package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 开标信息
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-03-16T14:17:14.799 ]
 * @Description: [ 开标信息 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "bidding_bidopeninginfo")
public class BidOpeningInfo extends BaseEntity {

    /**
     * 招标编号
     */
    @Column(name = "tenderNumber", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '招标编号'")
    private String tenderNumber;

    /**
     * 项目名称
     */
    @Column(name = "projectName", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 开标时间
     */
    @Column(name = "bidOpeningTime", nullable = false, columnDefinition = "DATE   COMMENT '开标时间String'")
    private LocalDate bidOpeningTime;

    /**
     * 委托人
     */
    @Column(name = "principal", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '委托人'")
    private String principal;

    /**
     * 记录人
     */
    @Column(name = "recorder", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '记录人'")
    private String recorder;

    /**
     * 竞争公司
     */
    @Column(name = "competitive", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '竞争公司'")
    private String competitive;

    /**
     * 地市
     */
    @Column(name = "cities", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '地市'")
    private String cities;

    /**
     * 竞争公司报价
     */
    @Column(name = "competitivePrice", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '竞争公司报价'")
    private String competitivePrice;

    /**
     * 比率(%)
     */
    @Column(name = "ratio", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '比率(%)'")
    private String ratio;


    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getBidOpeningTime() {
        return bidOpeningTime;
    }

    public void setBidOpeningTime(LocalDate bidOpeningTime) {
        this.bidOpeningTime = bidOpeningTime;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }

    public String getCompetitive() {
        return competitive;
    }

    public void setCompetitive(String competitive) {
        this.competitive = competitive;
    }

    public String getCities() {
        return cities;
    }

    public void setCities(String cities) {
        this.cities = cities;
    }

    public String getCompetitivePrice() {
        return competitivePrice;
    }

    public void setCompetitivePrice(String competitivePrice) {
        this.competitivePrice = competitivePrice;
    }

    public String getRatio() {
        return ratio;
    }

    public void setRatio(String ratio) {
        this.ratio = ratio;
    }
}