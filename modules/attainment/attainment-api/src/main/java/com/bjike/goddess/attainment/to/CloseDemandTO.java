package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.entity.ADD;
import com.bjike.goddess.common.api.entity.EDIT;
import com.bjike.goddess.common.api.to.BaseTO;

import javax.validation.constraints.NotNull;

/**
 * @Author: [dengjunren]
 * @Date: [2017-04-06 17:49]
 * @Description: [ 关闭需求传输对象 ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CloseDemandTO extends BaseTO {

    /**
     * 关闭原因
     */
    @NotNull(message = "关闭原因不能为空", groups = {ADD.class, EDIT.class})
    private String closeReason;

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }
}
