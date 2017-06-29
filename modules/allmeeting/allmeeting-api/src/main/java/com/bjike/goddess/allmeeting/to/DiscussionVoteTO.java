package com.bjike.goddess.allmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 意见投票
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 04:25 ]
 * @Description: [ 意见投票 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class DiscussionVoteTO extends BaseTO {

    /**
     * 意见Id
     */
    @NotBlank(message = "意见Id不能为空",groups = {ADD.class, EDIT.class})
    private String discussionId;

    /**
     * 纪要Id
     */
    @NotBlank(message = "纪要Id不能为空",groups = {ADD.class, EDIT.class})
    private String summaryId;

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

}