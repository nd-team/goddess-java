package com.bjike.goddess.recruit.vo;

import com.bjike.goddess.common.api.bo.BaseBO;

/**
 * 未邀约成功原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 16:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FailInviteReasonVO {

    /**
     * 未邀约成功原因类型
     */
    private String failInviteReasonType;

    /**
     * 备注
     */
    private String comment;

    public String getFailInviteReasonType() {
        return failInviteReasonType;
    }

    public void setFailInviteReasonType(String failInviteReasonType) {
        this.failInviteReasonType = failInviteReasonType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
