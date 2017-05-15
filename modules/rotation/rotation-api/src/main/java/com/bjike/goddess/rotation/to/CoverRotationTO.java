package com.bjike.goddess.rotation.to;

import com.bjike.goddess.common.api.to.BaseTO;

/**
 * 岗位轮换自荐
 *
 * @Author: [ dengjunren ]
 * @Date: [ 2017-05-13 02:18 ]
 * @Description: [ 岗位轮换自荐 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class CoverRotationTO extends BaseTO {

    /**
     * 申请轮换等级
     */
    private String applyLevelId;

    /**
     * 申请轮换原因
     */
    private String reason;

    /**
     * 轮换后岗位等级
     */
    private String rotationLevelId;

    /**
     * 总经办
     */
    private String general;

    /**
     * 总经办意见
     */
    private String opinion;

    /**
     * 是否通过
     */
    private Boolean pass;

    /**
     * 轮换时间
     */
    private String rotationDate;

    public String getApplyLevelId() {
        return applyLevelId;
    }

    public void setApplyLevelId(String applyLevelId) {
        this.applyLevelId = applyLevelId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRotationLevelId() {
        return rotationLevelId;
    }

    public void setRotationLevelId(String rotationLevelId) {
        this.rotationLevelId = rotationLevelId;
    }

    public String getGeneral() {
        return general;
    }

    public void setGeneral(String general) {
        this.general = general;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getRotationDate() {
        return rotationDate;
    }

    public void setRotationDate(String rotationDate) {
        this.rotationDate = rotationDate;
    }
}