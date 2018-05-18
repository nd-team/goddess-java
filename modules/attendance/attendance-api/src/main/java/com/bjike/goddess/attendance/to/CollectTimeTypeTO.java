package com.bjike.goddess.attendance.to;

import com.bjike.goddess.attendance.enums.OverTimesType;
import com.bjike.goddess.common.api.bo.BaseBO;
import com.bjike.goddess.taskallotment.enums.TimeStatus;

/**
 * @Author: [tanghaixiang]
 * @Date: [2017-11-10 10:32]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class CollectTimeTypeTO extends BaseBO {

    /**
     * 时间类型
     */
    private OverTimesType overTimesType;

    public OverTimesType getOverTimesType() {
        return overTimesType;
    }

    public void setOverTimesType(OverTimesType overTimesType) {
        this.overTimesType = overTimesType;
    }
}
