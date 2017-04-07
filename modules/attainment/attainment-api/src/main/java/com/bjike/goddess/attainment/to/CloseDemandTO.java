package com.bjike.goddess.attainment.to;

import com.bjike.goddess.common.api.to.BaseTO;

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
    private String closeReason;

    public String getCloseReason() {
        return closeReason;
    }

    public void setCloseReason(String closeReason) {
        this.closeReason = closeReason;
    }
}
