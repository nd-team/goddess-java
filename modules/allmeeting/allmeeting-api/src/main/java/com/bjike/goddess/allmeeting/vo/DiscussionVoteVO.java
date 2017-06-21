package com.bjike.goddess.allmeeting.vo;

/**
 * 意见投票表现层对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 04:25 ]
 * @Description: [ 意见投票表现层对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DiscussionVoteVO {

    /**
     * id
     */
    private String id;
    /**
     * 意见Id
     */
    private String discussionId;

    /**
     * 纪要Id
     */
    private String summaryId;

    /**
     * 投票人员工编号
     */
    private String voteUserNum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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