package com.bjike.goddess.allmeeting.entity;

import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 意见投票
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 04:25 ]
 * @Description: [ 意见投票 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
@Entity
@Table(name = "allmeeting_vote")
public class DiscussionVote extends BaseEntity {

    /**
     * 意见Id
     */
    @Column(name = "discussionId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '意见Id'")
    private String discussionId;

    /**
     * 纪要Id
     */
    @Column(name = "summaryId", nullable = false, columnDefinition = "VARCHAR(36)   COMMENT '纪要Id'")
    private String summaryId;



    /**
     * 投票人员工编号
     */
    @Column(name = "voteUserNum", nullable = false, columnDefinition = "VARCHAR(255)   COMMENT '投票人员工编号'")
    private String voteUserNum;


    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }

    public String getVoteUserNum() {
        return voteUserNum;
    }

    public void setVoteUserNum(String voteUserNum) {
        this.voteUserNum = voteUserNum;
    }
}