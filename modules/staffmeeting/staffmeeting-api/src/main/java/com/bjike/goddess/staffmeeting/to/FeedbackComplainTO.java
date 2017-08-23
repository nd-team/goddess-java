package com.bjike.goddess.staffmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 通告反馈投诉
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 04:23 ]
 * @Description: [ 通告反馈投诉 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FeedbackComplainTO extends BaseTO {

    /**
     * 纪要ID
     */
    @NotBlank(message = "纪要ID不能为空",groups = {ADD.class, EDIT.class})
    private String summaryId;

    /**
     * 异议内容
     */
    @NotBlank(message = "异议内容不能为空",groups = {ADD.class, EDIT.class})
    private String dissentContent;

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }


    public String getDissentContent() {
        return dissentContent;
    }

    public void setDissentContent(String dissentContent) {
        this.dissentContent = dissentContent;
    }
}