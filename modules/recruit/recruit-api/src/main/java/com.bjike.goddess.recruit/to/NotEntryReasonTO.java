package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 未入职原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 09:45]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class NotEntryReasonTO extends BaseTO {

    /**
     * 未入职原因类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "未入职原因类型不能为空")
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
