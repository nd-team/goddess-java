package com.bjike.goddess.recruit.vo;

/**
 * 未成功通话原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 16:49]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FailPhoneReasonVO {

    /**
     * 未成功通话原因类型
     */
    private String failPhoneReasonType;

    /**
     * 备注
     */
    private String comment;

    public String getFailPhoneReasonType() {
        return failPhoneReasonType;
    }

    public void setFailPhoneReasonType(String failPhoneReasonType) {
        this.failPhoneReasonType = failPhoneReasonType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
