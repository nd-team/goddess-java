package com.bjike.goddess.checkhost.dto;

import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 离宿申请数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 04:51 ]
 * @Description: [ 离宿申请数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class HostApplyDTO extends BaseDTO {
    public interface ADUIT {
    }

    /**
     * 审核状态
     */
    @NotNull(groups = HostApplyDTO.ADUIT.class, message = "审核状态不能为空")
    private CheckStatus checkStatus;

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }
}