package com.bjike.goddess.allmeeting.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.type.Status;

import javax.validation.constraints.NotNull;

/**
 * 工作汇总议题准备信息数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 03:44 ]
 * @Description: [ 工作汇总议题准备信息数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class WorkCollectPrepareDTO extends BaseDTO {

    public interface SelectStatus {
    }

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = {AllMeetingOrganizeDTO.SelectStatus.class})
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}