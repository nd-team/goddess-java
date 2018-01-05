package com.bjike.goddess.bidding.entity;

import com.bjike.goddess.bidding.enums.BusinessType;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
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
     * 编号
     */
    @Column(name = "biddingNumber", columnDefinition = "VARCHAR(255)   COMMENT '招标编号'")
    private String biddingNumber;
    /**
     * 年份
     */
    @Column(name = "year", columnDefinition = "INT(11)   COMMENT '年份'")
    private Integer year;
    /**
     * 月份
     */
    @Column(name = "month", columnDefinition = "INT(11)   COMMENT '月份'")
    private Integer month;

    /**
     * 项目名称
     */
    @Column(name = "projectName", columnDefinition = "VARCHAR(255)   COMMENT '项目名称'")
    private String projectName;
    /**
     * 业务类型
     */
    @Column(name = "businessType", columnDefinition = "VARCHAR(255)   COMMENT '业务类型'")
    private String businessType;

    /**
     * 业务方向科目
     */
    @Column(name = "businessDirectionSubject", columnDefinition = "VARCHAR(255)   COMMENT '业务方向科目'")
    private String businessDirectionSubject;
    /**
     * 开标时间
     */
    @Column(name = "bidOpeningTime", columnDefinition = "DATE   COMMENT '开标时间'")
    private LocalDate bidOpeningTime;
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
    /**
     * 招标价格
     */
    @Column(name = "biddingPrice", columnDefinition = "DECIMAL(10,2)   COMMENT '招标价格'")
    private Double biddingPrice;
    /**
     * 保证金金额
     */
    @Column(name = "marginPrice", columnDefinition = "DECIMAL(10,2)   COMMENT '保证金金额'")
    private Double marginPrice;
    /**
     * 保证金退回时间
     */
    @Column(name = "backTimeDeposit", columnDefinition = "DATE   COMMENT '保证金退回时间'")
    private LocalDate backTimeDeposit;

    /**
     * 退回保证金金额
     */
    @Column(name = "returnMarginPrice", columnDefinition = "DECIMAL(10,2)   COMMENT '退回保证金金额'")
    private Double returnMarginPrice;
    /**
     * 是否转为商机
     */
    @Column(name = "is_opportunity", columnDefinition = "TINYINT(1)   COMMENT '是否转为商机'")
    private Boolean opportunity;
    /**
     * 是否进行项目测算
     */
    @Column(name = "is_projectEstimates", columnDefinition = "TINYINT(1)   COMMENT '是否进行项目测算'")
    private Boolean projectEstimates;
    /**
     * 项目测算是否通过
     */
    @Column(name = "is_passProjectEstimates", columnDefinition = "TINYINT(1)   COMMENT '项目测算是否通过'")
    private Boolean passProjectEstimates;
    /**
     * 招标书是否制作完成
     */
    @Column(name = "is_finish", columnDefinition = "TINYINT(1)   COMMENT '招标书是否制作完成'")
    private Boolean finish;
    /**
     * 招标是否通过
     */
    @Column(name = "is_biddingPass", columnDefinition = "TINYINT(1)   COMMENT '招标是否通过'")
    private Boolean biddingPass;
    /**
     * 更新时间
     */
    @Column(name = "updateTime", columnDefinition = "DATE   COMMENT '更新时间'")
    private LocalDate updateTime;

    public LocalDate getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDate updateTime) {
        this.updateTime = updateTime;
    }

    public String getBiddingNumber() {
        return biddingNumber;
    }

    public void setBiddingNumber(String biddingNumber) {
        this.biddingNumber = biddingNumber;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessDirectionSubject() {
        return businessDirectionSubject;
    }

    public void setBusinessDirectionSubject(String businessDirectionSubject) {
        this.businessDirectionSubject = businessDirectionSubject;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Double getBiddingPrice() {
        return biddingPrice;
    }

    public void setBiddingPrice(Double biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public Double getMarginPrice() {
        return marginPrice;
    }

    public void setMarginPrice(Double marginPrice) {
        this.marginPrice = marginPrice;
    }

    public LocalDate getBackTimeDeposit() {
        return backTimeDeposit;
    }

    public void setBackTimeDeposit(LocalDate backTimeDeposit) {
        this.backTimeDeposit = backTimeDeposit;
    }

    public Double getReturnMarginPrice() {
        return returnMarginPrice;
    }

    public void setReturnMarginPrice(Double returnMarginPrice) {
        this.returnMarginPrice = returnMarginPrice;
    }

    public Boolean getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Boolean opportunity) {
        this.opportunity = opportunity;
    }

    public Boolean getProjectEstimates() {
        return projectEstimates;
    }

    public void setProjectEstimates(Boolean projectEstimates) {
        this.projectEstimates = projectEstimates;
    }

    public Boolean getPassProjectEstimates() {
        return passProjectEstimates;
    }

    public void setPassProjectEstimates(Boolean passProjectEstimates) {
        this.passProjectEstimates = passProjectEstimates;
    }

    public Boolean getFinish() {
        return finish;
    }

    public void setFinish(Boolean finish) {
        this.finish = finish;
    }

    public Boolean getBiddingPass() {
        return biddingPass;
    }

    public void setBiddingPass(Boolean biddingPass) {
        this.biddingPass = biddingPass;
    }
}