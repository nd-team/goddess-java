package com.bjike.goddess.checkhost.dto;

import com.bjike.goddess.checkhost.enums.CheckStatus;
import com.bjike.goddess.common.api.dto.BaseDTO;

import javax.validation.constraints.NotNull;

/**
 * 员工住宿天数汇总数据传输对象
 *
 * @Author: [ xiazhili ]
 * @Date: [ 2017-04-11 05:03 ]
 * @Description: [ 员工住宿天数汇总数据传输对象 ]
 * @Version: [ v1.0.0 ]
 * @Copy: [ com.bjike ]
 */
public class StayDaysDTO extends BaseDTO {
    public interface AUDIT {
    }

    /**
     * 审核状态
     */
    @NotNull(groups = StayDaysDTO.AUDIT.class, message = "审核状态不能为空")
    private CheckStatus checkStatus;

    public CheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(CheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }
}