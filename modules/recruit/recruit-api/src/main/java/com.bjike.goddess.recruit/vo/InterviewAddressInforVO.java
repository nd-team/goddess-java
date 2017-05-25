package com.bjike.goddess.recruit.vo;

/**
 * 面试地址信息
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 17:35]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class InterviewAddressInforVO {

    /**
     * id
     */
    private String id;

    /**
     * 面试地址
     */
    private String interviewAddress;

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

    public String getInterviewAddress() {
        return interviewAddress;
    }

    public void setInterviewAddress(String interviewAddress) {
        this.interviewAddress = interviewAddress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
