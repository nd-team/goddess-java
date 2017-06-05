package com.bjike.goddess.firmreward.bo;

import com.bjike.goddess.common.api.bo.BaseBO;

import javax.persistence.Column;

/**
 * 奖品明细业务传输对象
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:16 ]
 * @Description: [ 奖品明细业务传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class PrizeDetailBO extends BaseBO {

    /**
     * 奖品明细
     */
    private String prizeDetail;

    /**
     * 奖品购置途径
     */
    private String prizeBuyWay;

    /**
     * 奖品发放形式
     */
    private String prizeIssueForm;

    /**
     * 颁奖时间
     */
    private String awardTime;

    /**
     * 奖品申请id
     */
    private String prizeApplyId;

    public String getPrizeDetail() {
        return prizeDetail;
    }

    public void setPrizeDetail(String prizeDetail) {
        this.prizeDetail = prizeDetail;
    }

    public String getPrizeBuyWay() {
        return prizeBuyWay;
    }

    public void setPrizeBuyWay(String prizeBuyWay) {
        this.prizeBuyWay = prizeBuyWay;
    }

    public String getPrizeIssueForm() {
        return prizeIssueForm;
    }

    public void setPrizeIssueForm(String prizeIssueForm) {
        this.prizeIssueForm = prizeIssueForm;
    }

    public String getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(String awardTime) {
        this.awardTime = awardTime;
    }

    public String getPrizeApplyId() {
        return prizeApplyId;
    }

    public void setPrizeApplyId(String prizeApplyId) {
        this.prizeApplyId = prizeApplyId;
    }
}