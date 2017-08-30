package com.bjike.goddess.staffmeeting.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.type.Status;

import javax.validation.constraints.NotNull;

/**
 * 通告反馈投诉数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 04:23 ]
 * @Description: [ 通告反馈投诉数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class FeedbackComplainDTO extends BaseDTO {

    public interface SelectStatus {
    }

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = {FeedbackComplainDTO.SelectStatus.class})
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}