package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 各渠道汇总
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-10-30 03:52 ]
 * @Description: [ 各渠道汇总 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class RecruitChannelBO extends BaseBO {
    /**
     * 简历来源
     */
    private String channel;
    /**
     * 邀约面试量
     */
    private Integer inviteNum;

    /**
     * 初试面试量
     */
    private Integer firstNum;

    /**
     * 复试面试量
     */
    private Integer secondNum;

    /**
     * 成功通过面试量
     */
    private Integer successPassNum;

    /**
     * 入职量
     */
    private Integer entryNum;
    /**
     * 入职率
     */
    private Double entryRate;

    /**
     * 面试率
     */
    private Double faceRate;

    public Double getEntryRate() {
        return entryRate;
    }

    public void setEntryRate(Double entryRate) {
        this.entryRate = entryRate;
    }

    public Double getFaceRate() {
        return faceRate;
    }

    public void setFaceRate(Double faceRate) {
        this.faceRate = faceRate;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Integer getInviteNum() {
        return inviteNum;
    }

    public void setInviteNum(Integer inviteNum) {
        this.inviteNum = inviteNum;
    }

    public Integer getFirstNum() {
        return firstNum;
    }

    public void setFirstNum(Integer firstNum) {
        this.firstNum = firstNum;
    }

    public Integer getSecondNum() {
        return secondNum;
    }

    public void setSecondNum(Integer secondNum) {
        this.secondNum = secondNum;
    }

    public Integer getSuccessPassNum() {
        return successPassNum;
    }

    public void setSuccessPassNum(Integer successPassNum) {
        this.successPassNum = successPassNum;
    }

    public Integer getEntryNum() {
        return entryNum;
    }

    public void setEntryNum(Integer entryNum) {
        this.entryNum = entryNum;
    }
}