package com.bjike.goddess.staffactivity.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 活动方案
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:42 ]
 * @Description: [ ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "staffactivity_activityscheme")
public class ActivityScheme extends BaseEntity {

    /**
     * 活动主题
     */
    @Column(name = "theme", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动主题'")
    private String theme;

    /**
     * 活动时间
     */
    @Column(name = "activityTime", nullable = false, columnDefinition = "DATETIME COMMENT '活动时间'")
    private LocalDateTime activityTime;

    /**
     * 活动地区
     */
    @Column(name = "area", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '活动地区'")
    private String area;

    /**
     * 开展形式
     */
    @Column(name = "developForm", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '开展形式'")
    private String developForm;

    /**
     * 参与人数
     */
    @Column(name = "attendNo", nullable = false, columnDefinition = "INT(11) COMMENT '参与人数'")
    private Integer attendNo;

    /**
     * 总活动经费
     */
    @Column(name = "totalActivityFund", columnDefinition = "DECIMAL(10,2) COMMENT '总活动经费'")
    private Double totalActivityFund;

    /**
     * 活动费用明细
     */
    @Column(name = "chargeDetails", columnDefinition = "VARCHAR(255) COMMENT '活动费用明细'")
    private String chargeDetails;

    /**
     * 运营商务部意见
     */
    @Column(name = "yYOpinion", columnDefinition = "VARCHAR(255) COMMENT '运营商务部意见'")
    private String yYOpinion;

    /**
     * 方案是否通过
     */
    @Column(name = "ifSchemePass", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '方案是否通过'")
    private Boolean ifSchemePass;

    /**
     * 总经办意见
     */
    @Column(name = "zjbOpinion", columnDefinition = "VARCHAR(255) COMMENT '总经办意见'")
    private String zjbOpinion;

    /**
     * 是否有必要持续开展
     */
    @Column(name = "ifNeedContinue", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '是否有必要持续开展'")
    private Boolean ifNeedContinue;

    /**
     * 原因及意见
     */
    @Column(name = "reasonAndOpinion", columnDefinition = "VARCHAR(255) COMMENT '原因及意见'")
    private String reasonAndOpinion;

    /**
     * 活动总支出是否合理
     */
    @Column(name = "ifTotalOutlayRational", columnDefinition = "TINYINT(1) DEFAULT 0  COMMENT '活动总支出是否合理'")
    private Boolean ifTotalOutlayRational;

    /**
     * 经费建议
     */
    @Column(name = "fundProposal", columnDefinition = "VARCHAR(255) COMMENT '经费建议'")
    private String fundProposal;

    /**
     * 活动流程是否存在缺陷
     */
    @Column(name = "ifFlowDefect", columnDefinition = "TINYINT(1) DEFAULT 0 COMMENT '活动流程是否存在缺陷'")
    private Boolean ifFlowDefect;

    /**
     * 流程建议
     */
    @Column(name = "flowProposal", columnDefinition = "VARCHAR(255) COMMENT '流程建议'")
    private String flowProposal;

    /**
     * 活动效应
     */
    @Column(name = "activityEffect", columnDefinition = "VARCHAR(255) COMMENT '活动效应'")
    private String activityEffect;

    /**
     * 总经办评价及建议
     */
    @Column(name = "zjbEvaluate", columnDefinition = "VARCHAR(255) COMMENT '总经办评价及建议'")
    private String zjbEvaluate;


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public LocalDateTime getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(LocalDateTime activityTime) {
        this.activityTime = activityTime;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDevelopForm() {
        return developForm;
    }

    public void setDevelopForm(String developForm) {
        this.developForm = developForm;
    }

    public Integer getAttendNo() {
        return attendNo;
    }

    public void setAttendNo(Integer attendNo) {
        this.attendNo = attendNo;
    }

    public Double getTotalActivityFund() {
        return totalActivityFund;
    }

    public void setTotalActivityFund(Double totalActivityFund) {
        this.totalActivityFund = totalActivityFund;
    }

    public String getChargeDetails() {
        return chargeDetails;
    }

    public void setChargeDetails(String chargeDetails) {
        this.chargeDetails = chargeDetails;
    }

    public String getYYOpinion() {
        return yYOpinion;
    }

    public void setYYOpinion(String yYOpinion) {
        this.yYOpinion = yYOpinion;
    }

    public Boolean getIfSchemePass() {
        return ifSchemePass;
    }

    public void setIfSchemePass(Boolean ifSchemePass) {
        this.ifSchemePass = ifSchemePass;
    }

    public String getZjbOpinion() {
        return zjbOpinion;
    }

    public void setZjbOpinion(String zjbOpinion) {
        this.zjbOpinion = zjbOpinion;
    }

    public Boolean getIfNeedContinue() {
        return ifNeedContinue;
    }

    public void setIfNeedContinue(Boolean ifNeedContinue) {
        this.ifNeedContinue = ifNeedContinue;
    }

    public String getReasonAndOpinion() {
        return reasonAndOpinion;
    }

    public void setReasonAndOpinion(String reasonAndOpinion) {
        this.reasonAndOpinion = reasonAndOpinion;
    }

    public Boolean getIfTotalOutlayRational() {
        return ifTotalOutlayRational;
    }

    public void setIfTotalOutlayRational(Boolean ifTotalOutlayRational) {
        this.ifTotalOutlayRational = ifTotalOutlayRational;
    }

    public String getFundProposal() {
        return fundProposal;
    }

    public void setFundProposal(String fundProposal) {
        this.fundProposal = fundProposal;
    }

    public Boolean getIfFlowDefect() {
        return ifFlowDefect;
    }

    public void setIfFlowDefect(Boolean ifFlowDefect) {
        this.ifFlowDefect = ifFlowDefect;
    }

    public String getFlowProposal() {
        return flowProposal;
    }

    public void setFlowProposal(String flowProposal) {
        this.flowProposal = flowProposal;
    }

    public String getActivityEffect() {
        return activityEffect;
    }

    public void setActivityEffect(String activityEffect) {
        this.activityEffect = activityEffect;
    }

    public String getZjbEvaluate() {
        return zjbEvaluate;
    }

    public void setZjbEvaluate(String zjbEvaluate) {
        this.zjbEvaluate = zjbEvaluate;
    }
}