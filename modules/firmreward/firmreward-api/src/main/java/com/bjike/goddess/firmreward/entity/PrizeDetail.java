package com.bjike.goddess.firmreward.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;


/**
 * 奖品明细
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-13 09:16 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "firmreward_prizedetail")
public class PrizeDetail extends BaseEntity {

    /**
     * 奖品明细
     */
    @Column(name = "prizeDetail", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖品明细'")
    private String prizeDetail;

    /**
     * 奖品购置途径
     */
    @Column(name = "prizeBuyWay", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖品购置途径'")
    private String prizeBuyWay;

    /**
     * 奖品发放形式
     */
    @Column(name = "prizeIssueForm", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖品发放形式'")
    private String prizeIssueForm;

    /**
     * 颁奖时间
     */
    @Column(name = "awardTime", nullable = false, columnDefinition = "DATE COMMENT '颁奖时间'")
    private LocalDate awardTime;

    /**
     * 奖品申请id
     */
    @Column(name = "prizeApplyId", nullable = false, columnDefinition = "VARCHAR(255) COMMENT '奖品申请id'")
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

    public LocalDate getAwardTime() {
        return awardTime;
    }

    public void setAwardTime(LocalDate awardTime) {
        this.awardTime = awardTime;
    }

    public String getPrizeApplyId() {
        return prizeApplyId;
    }

    public void setPrizeApplyId(String prizeApplyId) {
        this.prizeApplyId = prizeApplyId;
    }
}