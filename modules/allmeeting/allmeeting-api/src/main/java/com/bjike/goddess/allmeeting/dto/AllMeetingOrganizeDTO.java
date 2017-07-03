package com.bjike.goddess.allmeeting.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.type.Status;

import javax.validation.constraints.NotNull;

/**
 * 所有工作内容汇总会议组织内容数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-05-31 11:06 ]
 * @Description: [ 所有工作内容汇总会议组织内容数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class AllMeetingOrganizeDTO extends BaseDTO {

    public interface Select {
    }

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = {AllMeetingOrganizeDTO.Select.class})
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}