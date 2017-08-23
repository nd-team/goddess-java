package com.bjike.goddess.allmeeting.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 会议讨论意见
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-05 03:10 ]
 * @Description: [ 会议讨论意见 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FirstDiscussionTO extends BaseTO {

    /**
     * 一轮意见
     */
    @NotBlank(message = "一轮意见不能为空",groups = {ADD.class, EDIT.class})
    private String firstDis;

    /**
     * 纪要Id
     */
    @NotBlank(message = "纪要Id不能为空",groups = {ADD.class, EDIT.class})
    private String summaryId;


    public String getFirstDis() {
        return firstDis;
    }

    public void setFirstDis(String firstDis) {
        this.firstDis = firstDis;
    }

    public String getSummaryId() {
        return summaryId;
    }

    public void setSummaryId(String summaryId) {
        this.summaryId = summaryId;
    }
}