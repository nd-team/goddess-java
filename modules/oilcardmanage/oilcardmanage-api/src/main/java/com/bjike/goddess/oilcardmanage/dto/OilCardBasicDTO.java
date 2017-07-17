package com.bjike.goddess.oilcardmanage.dto;

import com.bjike.goddess.common.api.dto.BaseDTO;
import com.bjike.goddess.common.api.type.Status;

import javax.validation.constraints.NotNull;

/**
 * 油卡基础信息查询对象
 *
 * @Author: [Jason]
 * @Date: [17-3-11 上午10:35]
 * @Package:[ com.bjike.goddess.com.bjike.goddess.com.bjike.goddess.oilcardmanage.dto ]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class OilCardBasicDTO extends BaseDTO {

    /**
     * 状态
     */
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
