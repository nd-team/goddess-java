package com.bjike.goddess.recruit.to;

/**
 * 未邀约成功原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 09:19]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FailInviteReasonTO {

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
