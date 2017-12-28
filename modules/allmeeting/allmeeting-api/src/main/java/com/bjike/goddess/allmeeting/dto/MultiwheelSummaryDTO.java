package com.bjike.goddess.allmeeting.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.type.Status;

import javax.validation.constraints.NotNull;

/**
 * 简洁交流讨论纪要数据传输对象
 *
 * @Author: [ Jason ]
 * @Date: [ 2017-06-01 10:44 ]
 * @Description: [ 简洁交流讨论纪要数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class MultiwheelSummaryDTO extends BaseDTO {

    public interface SelectStatus {
    }

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = {MultiwheelSummaryDTO.SelectStatus.class})
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}