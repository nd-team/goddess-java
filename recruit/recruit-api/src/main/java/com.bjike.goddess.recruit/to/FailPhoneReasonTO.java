package com.bjike.goddess.recruit.to;

/**
 * 未成功通话原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 09:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FailPhoneReasonTO {

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
