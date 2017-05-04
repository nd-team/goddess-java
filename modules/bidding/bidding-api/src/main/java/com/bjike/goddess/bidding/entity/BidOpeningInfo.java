package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


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
    @Column(name = "tenderNumber", columnDefinition = "VARCHAR(255)   COMMENT '招标编号'")
    private String tenderNumber;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;

    /**
     * 开标时间
     */
    @Column(name = "bidOpeningTime", columnDefinition = "DATETIME   COMMENT '开标时间String'")
    private LocalDateTime bidOpeningTime;
    /**
     * 开标地点
     */
    @Column(name = "bidOpeningPlace", columnDefinition = "VARCHAR(255)   COMMENT '开标地点'")
    private String bidOpeningPlace;

    /**
     * 委托人
     */
    @Column(name = "principal", columnDefinition = "VARCHAR(255)   COMMENT '委托人'")
    private String principal;

    /**
     * 记录人
     */
    @Column(name = "recorder", columnDefinition = "VARCHAR(255)   COMMENT '记录人'")
    private String recorder;

    /**
     * 竞争公司
     */
    @Column(name = "competitive", columnDefinition = "VARCHAR(255)   COMMENT '竞争公司'")
    private String competitive;

    /**
     * 地市
     */
    @Column(name = "cities", columnDefinition = "VARCHAR(255)   COMMENT '地市'")
    private String cities;

    /**
     * 竞争公司报价
     */
    @Column(name = "competitivePrice", columnDefinition = "DECIMAL(10,2)   COMMENT '竞争公司报价'")
    private Double competitivePrice;

    /**
     * 比率(%)
     */
    @Column(name = "ratio", columnDefinition = "DECIMAL(10,2)   COMMENT '比率(%)'")
    private Double ratio;


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

    public LocalDateTime getBidOpeningTime() {
        return bidOpeningTime;
    }

    public void setBidOpeningTime(LocalDateTime bidOpeningTime) {
        this.bidOpeningTime = bidOpeningTime;
    }

    public String getBidOpeningPlace() {
        return bidOpeningPlace;
    }

    public void setBidOpeningPlace(String bidOpeningPlace) {
        this.bidOpeningPlace = bidOpeningPlace;
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

    public Double getCompetitivePrice() {
        return competitivePrice;
    }

    public void setCompetitivePrice(Double competitivePrice) {
        this.competitivePrice = competitivePrice;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }
}