package com.bjike.goddess.interiorrecommend.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 推荐方案
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-04-09 10:31 ]
 * @Description: [ 推荐方案 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecommendSchemeTO extends BaseTO {

    /**
     * 方案制作时间
     */
    @NotBlank(message = "方案制作时间不能为空", groups = {ADD.class, EDIT.class})
    private String makeTime;

    /**
     * 方案类型
     */
    @NotBlank(message = "方案类型不能为空", groups = {ADD.class, EDIT.class})
    private String type;

    /**
     * 推荐目的
     */
    @NotBlank(message = "推荐目的不能为空", groups = {ADD.class, EDIT.class})
    private String purpose;

    /**
     * 推荐开通时间
     */
    @NotBlank(message = "推荐开通时间不能为空", groups = {ADD.class, EDIT.class})
    private String openTime;

    /**
     * 推荐关闭时间
     */
    @NotBlank(message = "推荐关闭时间不能为空", groups = {ADD.class, EDIT.class})
    private String closeTime;

    /**
     * 适用对象
     */
    @NotBlank(message = "适用对象不能为空", groups = {ADD.class, EDIT.class})
    private String suitableObj;

    /**
     * 推荐要求明细
     */
    @NotBlank(message = "推荐要求明细不能为空", groups = {ADD.class, EDIT.class})
    private String requireDetail;

    /**
     * 推荐采纳指标
     */
    @NotBlank(message = "推荐采纳指标不能为空", groups = {ADD.class, EDIT.class})
    private String acceptTarget;

    /**
     * 奖励标准明细
     */
    @NotBlank(message = "奖励标准明细不能为空", groups = {ADD.class, EDIT.class})
    private String awardDetail;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}