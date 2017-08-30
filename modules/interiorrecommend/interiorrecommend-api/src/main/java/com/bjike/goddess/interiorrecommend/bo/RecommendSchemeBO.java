package com.bjike.goddess.interiorrecommend.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 推荐方案业务传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendSchemeBO extends BaseBO {

    /**
     * 方案制作时间
     */
    private String makeTime;

    /**
     * 方案类型
     */
    private String type;

    /**
     * 推荐目的
     */
    private String purpose;

    /**
     * 推荐开通时间
     */
    private String openTime;

    /**
     * 推荐关闭时间
     */
    private String closeTime;

    /**
     * 适用对象
     */
    private String suitableObj;

    /**
     * 推荐要求明细
     */
    private String requireDetail;

    /**
     * 推荐采纳指标
     */
    private String acceptTarget;

    /**
     * 奖励标准明细
     */
    private String awardDetail;

    /**
     * 综合资源部意见
     */
    private String resourcesSuggest;

    /**
     * 综合资源部审核
     */
    private Boolean resourcesAudit;

    /**
     * 运营商务部意见
     */
    private String operateSuggest;

    /**
     * 运营商务部审核
     */
    private Boolean operateAudit;

    /**
     * 总经办意见
     */
    private String generalSuggest;

    /**
     * 总经办审核
     */
    private Boolean generalAudit;

    /**
     * 备注
     */
    private String remark;


    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getSuitableObj() {
        return suitableObj;
    }

    public void setSuitableObj(String suitableObj) {
        this.suitableObj = suitableObj;
    }

    public String getRequireDetail() {
        return requireDetail;
    }

    public void setRequireDetail(String requireDetail) {
        this.requireDetail = requireDetail;
    }

    public String getAcceptTarget() {
        return acceptTarget;
    }

    public void setAcceptTarget(String acceptTarget) {
        this.acceptTarget = acceptTarget;
    }

    public String getAwardDetail() {
        return awardDetail;
    }

    public void setAwardDetail(String awardDetail) {
        this.awardDetail = awardDetail;
    }

    public String getResourcesSuggest() {
        return resourcesSuggest;
    }

    public void setResourcesSuggest(String resourcesSuggest) {
        this.resourcesSuggest = resourcesSuggest;
    }

    public Boolean getResourcesAudit() {
        return resourcesAudit;
    }

    public void setResourcesAudit(Boolean resourcesAudit) {
        this.resourcesAudit = resourcesAudit;
    }

    public String getOperateSuggest() {
        return operateSuggest;
    }

    public void setOperateSuggest(String operateSuggest) {
        this.operateSuggest = operateSuggest;
    }

    public Boolean getOperateAudit() {
        return operateAudit;
    }

    public void setOperateAudit(Boolean operateAudit) {
        this.operateAudit = operateAudit;
    }

    public String getGeneralSuggest() {
        return generalSuggest;
    }

    public void setGeneralSuggest(String generalSuggest) {
        this.generalSuggest = generalSuggest;
    }

    public Boolean getGeneralAudit() {
        return generalAudit;
    }

    public void setGeneralAudit(Boolean generalAudit) {
        this.generalAudit = generalAudit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}