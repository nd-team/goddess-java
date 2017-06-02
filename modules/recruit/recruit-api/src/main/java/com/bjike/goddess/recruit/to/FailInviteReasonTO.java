package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 未邀约成功原因
 *
 * @Author: [ sunfengtao ]
 * @Date: [ 2017-04-08 05:10 ]
 * @Description: [  ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FailInviteReasonTO extends BaseTO {

    /**
     * 未邀约成功原因类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "未邀约成功原因类型不能为空")
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
