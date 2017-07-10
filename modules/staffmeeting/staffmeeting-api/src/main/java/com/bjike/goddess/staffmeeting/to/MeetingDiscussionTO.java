package com.bjike.goddess.staffmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * 通告反馈投诉
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 05:18 ]
 * @Description: [ 通告反馈投诉 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingDiscussionTO extends BaseTO {

    /**
     * 发言内容
     */
    @NotBlank(message = "发言内容不能为空",groups = {ADD.class, EDIT.class})
    private String discussion;

    /**
     * 是否修改发言内容
     */
    @NotNull(message = "是否修改发言内容不能为空",groups = {ADD.class, EDIT.class})
    private Boolean dissentContent;

    /**
     * 修改后的发言内容
     */
    private String modifyContent;

    /**
     * 纪要Id
     */
    @NotBlank(message = "纪要Id不能为空",groups = {ADD.class, EDIT.class})
    private String summaryId;

    public String getDiscussion() {
        return discussion;
    }

    public void setDiscussion(String discussion) {
        this.discussion = discussion;
    }

    public Boolean getDissentContent() {
        return dissentContent;
    }

    public void setDissentContent(Boolean dissentContent) {
        this.dissentContent = dissentContent;
    }

    public String getModifyContent() {
        return modifyContent;
    }

    public void setModifyContent(String modifyContent) {
        this.modifyContent = modifyContent;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }
}