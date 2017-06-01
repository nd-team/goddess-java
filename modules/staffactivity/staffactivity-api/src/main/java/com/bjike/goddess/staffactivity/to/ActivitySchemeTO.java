package com.bjike.goddess.staffactivity.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 活动方案
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-09 08:42 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class ActivitySchemeTO extends BaseTO {

    /**
     * 活动主题
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动主题不能为空")
    private String theme;

    /**
     * 活动时间
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动时间不能为空")
    private String activityTime;

    /**
     * 活动地区
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "活动地区不能为空")
    private String area;

    /**
     * 开展形式
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "开展形式不能为空")
    private String developForm;

    /**
     * 参与人数
     */
    @NotNull(groups = {ADD.class, EDIT.class}, message = "参与人员不能为空")
    private Integer attendNo;

    /**
     * 总活动经费
     */
    private Double totalActivityFund;

    /**
     * 活动费用明细
     */
    private String chargeDetails;

    /**
     * 运营商务部意见
     */
    private String yYOpinion;

    /**
     * 方案是否通过
     */
    private Boolean ifSchemePass;

    /**
     * 总经办意见
     */
    private String zjbOpinion;

    /**
     * 是否有必要持续开展
     */
    private Boolean ifNeedContinue;

    /**
     * 原因及意见
     */
    private String reasonAndOpinion;

    /**
     * 活动总支出是否合理
     */
    private Boolean ifTotalOutlayRational;

    /**
     * 经费建议
     */
    private String fundProposal;

    /**
     * 活动流程是否存在缺陷
     */
    private Boolean ifFlowDefect;

    /**
     * 流程建议
     */
    private String flowProposal;

    /**
     * 活动效应
     */
    private String activityEffect;

    /**
     * 总经办评价及建议
     */
    private String zjbEvaluate;


    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
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