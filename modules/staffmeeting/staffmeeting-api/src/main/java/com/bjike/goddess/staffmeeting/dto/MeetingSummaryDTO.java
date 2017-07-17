package com.bjike.goddess.staffmeeting.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.type.Status;

import javax.validation.constraints.NotNull;

/**
 * 员工代表大会纪要数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-06 11:33 ]
 * @Description: [ 员工代表大会纪要数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MeetingSummaryDTO extends BaseDTO {

    public interface SelectStatus {
    }

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = {MeetingSummaryDTO.SelectStatus.class})
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}