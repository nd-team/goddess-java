package com.bjike.goddess.recruit.bo;

import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.common.api.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 未邀约成功原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-10 16:59]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FailInviteReasonBO extends BaseBO {

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
