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
public class SecondDiscussionTO extends BaseTO {

    /**
     * 二轮意见
     */
    @NotBlank(message = "二轮意见不能为空",groups = {ADD.class, EDIT.class})
    private String secondDis;

    public String getSecondDis() {
        return secondDis;
    }

    public void setSecondDis(String secondDis) {
        this.secondDis = secondDis;
    }
}