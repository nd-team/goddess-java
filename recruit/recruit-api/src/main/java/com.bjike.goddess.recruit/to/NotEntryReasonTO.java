package com.bjike.goddess.recruit.to;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 09:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NotEntryReasonTO {

    /**
     * 未入职原因类型
     */
    private String notEntryReasonType;

    /**
     * 备注
     */
    private String comment;

    public String getNotEntryReasonType() {
        return notEntryReasonType;
    }

    public void setNotEntryReasonType(String notEntryReasonType) {
        this.notEntryReasonType = notEntryReasonType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
