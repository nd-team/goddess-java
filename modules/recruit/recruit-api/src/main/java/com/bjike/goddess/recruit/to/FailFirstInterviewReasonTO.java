package com.bjike.goddess.recruit.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 未应约初试原因
 *
 * @Author: [sunfengtao]
 * @Date: [2017-03-13 09:17]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class FailFirstInterviewReasonTO extends BaseTO{
    /**
     * 未应约初试原因类型
     */
    @NotBlank(groups = {ADD.class, EDIT.class}, message = "未应约初试原因类型不能为空")
    private String failFirstInterviewReasonType;

    /**
     * 备注
     */
    private String comment;

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
