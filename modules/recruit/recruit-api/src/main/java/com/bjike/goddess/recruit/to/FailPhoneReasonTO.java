package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 未成功通话原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 09:21]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FailPhoneReasonTO extends BaseTO {

    /**
     * 未成功通话原因类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "未成功通话原因类型不能为空")
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
