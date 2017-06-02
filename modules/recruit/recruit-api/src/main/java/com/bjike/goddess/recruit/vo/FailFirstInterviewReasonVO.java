package com.bjike.goddess.recruit.vo;

/**
 * 未应约初试原因
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FailFirstInterviewReasonVO {

    /**
     * id
     */
    private String id;

    /**
     * 未应约初试原因类型
     */
    private String failFirstInterviewReasonType;

    /**
     * 备注
     */
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFailFirstInterviewReasonType() {
        return failFirstInterviewReasonType;
    }

    public void setFailFirstInterviewReasonType(String failFirstInterviewReasonType) {
        this.failFirstInterviewReasonType = failFirstInterviewReasonType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
